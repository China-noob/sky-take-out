package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lpl
 * @date 2023-08-30 10:12
 * @description:菜品控制层
 * @version:1.0.0
 */

@RestController
@RequestMapping("/admin/dish")
@Slf4j
@Api("菜品相关接口")
public class DishController {
    @Autowired
    private DishService dishService;

    @PostMapping
    @ApiOperation("新增菜品")
    public Result save(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品：{}", dishDTO);
        dishService.saveWithFlavor(dishDTO);//后绪步骤开发
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("菜品分页查询接口")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        PageResult page = dishService.page(dishPageQueryDTO);
        return Result.success(page);
    }

    @DeleteMapping
    @ApiOperation("删除或批量删除")
    public Result delete(String ids) {
        dishService.delete(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询信息")
    public Result<DishVO> selectById(@PathVariable int id) {
        DishVO dishVO = dishService.selectById(id);
        return Result.success(dishVO);
    }

    @PutMapping
    @ApiOperation("修改菜品")
    public Result update(@RequestBody DishDTO dishDTO) {
        log.info("修改菜品：{}", dishDTO);
        dishService.updateWithFlavor(dishDTO);
        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<Dish>> selectDishByCategoryId(@RequestParam long categoryId){
        List<Dish> dishList=dishService.selectDishByCategoryId(categoryId);
        return Result.success(dishList);
    }
}
