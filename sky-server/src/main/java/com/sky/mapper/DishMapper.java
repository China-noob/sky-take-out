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
}
