package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;

public interface CategoryService {
    Result addCategory(CategoryDTO categoryDTO);

    PageResult pageQuerycategory(CategoryPageQueryDTO categoryPageQueryDTO);

    void update(long id, int status);

    void alter(CategoryDTO categoryDTO);

    void delete(int id);
}
