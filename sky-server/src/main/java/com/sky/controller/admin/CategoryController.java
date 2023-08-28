package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import com.sky.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lpl
 * @date 2023-08-26 14:05
 * @description:菜品管理控制层
 * @version: 1.0.0
 */
@RestController
@RequestMapping("/admin/category")
@Slf4j
@Api(tags = "菜品分类相关接口")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping()
    @ApiOperation("菜品分类新增接口")
    public Result addCategory(@RequestBody  CategoryDTO  categoryDTO){
        categoryService.addCategory(categoryDTO);
        return Result.success();
    }
    @GetMapping("/page")
    @ApiOperation("菜品分类查询接口")
    public Result<PageResult> pageQuerycategory(CategoryPageQueryDTO categoryPageQueryDTO){
        PageResult pageResultResult=categoryService.pageQuerycategory(categoryPageQueryDTO);
        return Result.success(pageResultResult);
    }


}
