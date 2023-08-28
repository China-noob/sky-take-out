package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.result.Result;

public interface CategoryService {
    Result addCategory(CategoryDTO categoryDTO);
}
