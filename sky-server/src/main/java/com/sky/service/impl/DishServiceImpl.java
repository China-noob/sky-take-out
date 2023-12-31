package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lpl
 * @date 2023-08-30 10:19
 * @description:菜品service
 * @version: 1.0.0
 */
@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    @Override
    public void saveWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        //向菜品表插入1条数据
        dishMapper.insert(dish);//后绪步骤实现

        //获取insert语句生成的主键值
        Long dishId = dish.getId();

        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && flavors.size() > 0) {
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishId);
            });
            //向口味表插入n条数据
            dishFlavorMapper.insertBatch(flavors);//后绪步骤实现
        }
    }

    @Override
    public PageResult page(DishPageQueryDTO dishPageQueryDTO) {
        //先写 主要改变一下category_id为连表查询category——name
        PageHelper.startPage(dishPageQueryDTO.getPage(),dishPageQueryDTO.getPageSize());

        Page<DishVO> dishPage=dishMapper.page(dishPageQueryDTO);

        long total = dishPage.getTotal();
        List<DishVO> result = dishPage.getResult();
        return new PageResult(total,result);
    }

    @Override
    public void delete(String ids) {
        //- 可以一次删除一个菜品，也可以批量删除菜品 - 起售中的菜品不能删除 - 被套餐关联的菜品不能删除 - 删除菜品后，关联的口味数据也需要删除掉
        //获取id
        String[] split = ids.split(",");
        for (String i:split){
            //找到不是在起售中，不关联套餐
            //不是在起售中
            if(dishMapper.concate(Integer.parseInt(i))!=0){
                dishMapper.delete(Integer.parseInt(i));
                dishFlavorMapper.delete(Integer.parseInt(i));
            }


        }
    }

    @Override
    public DishVO selectById(int id) {
        //关键-查询category——name 和 flvors
        DishVO dishVO=dishMapper.selectById(id);
        //写入flavors
        List<DishFlavor> flavors=dishFlavorMapper.selectById(id);
        dishVO.setFlavors(flavors);
        return dishVO;
    }

    @Override
    public void updateWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        //修改菜品表基本信息
        dishMapper.update(dish);

        //删除原有的口味数据
        dishFlavorMapper.deleteByDishId(dishDTO.getId());

        //重新插入口味数据
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && flavors.size() > 0) {
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishDTO.getId());
            });
            //向口味表插入n条数据
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    @Override
    public List<Dish> selectDishByCategoryId(long id) {

        List<Dish> list =dishMapper.selectDishByCategoryId(id);
        return list;
    }
    @Override
    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    public List<DishVO> listWithFlavor(Dish dish) {
        List<Dish> dishList = dishMapper.list(dish);

        List<DishVO> dishVOList = new ArrayList<>();

        for (Dish d : dishList) {
            DishVO dishVO = new DishVO();
            BeanUtils.copyProperties(d,dishVO);

            //根据菜品id查询对应的口味
            List<DishFlavor> flavors = dishFlavorMapper.getByDishId(d.getId());

            dishVO.setFlavors(flavors);
            dishVOList.add(dishVO);
        }

        return dishVOList;
    }


}
