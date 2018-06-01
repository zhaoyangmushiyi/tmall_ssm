package com.monochrome.tmall.service;

import com.monochrome.tmall.pojo.Category;

import java.util.List;

public interface CategoryService {
    List<Category> list();
    int total();
    void add(Category category);
}
