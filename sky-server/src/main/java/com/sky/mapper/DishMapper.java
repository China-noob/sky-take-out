package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {
    @AutoFill(value = OperationType.INSERT)

    void insert(Dish dish);

    Page<DishVO> page(DishPageQueryDTO dishPageQueryDTO);

    @Delete("delete  from dish  where  id=#{id} ")
    void delete(int id);
    //起售中的菜品不能删除 - 被套餐关联的菜品不能删除


    @Select("select count(0) from dish where status = 0 and id=#{id}")
    int concate(int id);

    @Select("select  category.name as categoryName,dish.category_id as categoryId,dish.description as description ,dish.id as id,dish.image as image, dish.name as name ,dish.price as price ,dish.status as status,dish.update_time as updateTime from dish,category where dish.id=60 and dish.category_id=category.id")
    DishVO selectById(int id);

    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);

    @Select("select * from dish where category_id=#{id}")
    List<Dish> selectDishByCategoryId(long id);
    @Select("select a.* from dish a left join setmeal_dish b on a.id = b.dish_id where b.setmeal_id = #{setmealId}")
    List<Dish> getBySetmealId(Long id);

    List<Dish> list(Dish dish);
}
