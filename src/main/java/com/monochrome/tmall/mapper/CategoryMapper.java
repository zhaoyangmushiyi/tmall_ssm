package com.monochrome.tmall.mapper;

import com.monochrome.tmall.pojo.Category;

import java.util.List;

public interface CategoryMapper {

    List<Category> list();

    int total();

    void add(Category category);

    void delete(int id);
}
