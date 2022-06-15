package com.cqu.mybatis.dao;

import com.cqu.mybatis.pojo.Category;

public interface CategoryDao {
    Category getCategoryById(long categoryId);
}
