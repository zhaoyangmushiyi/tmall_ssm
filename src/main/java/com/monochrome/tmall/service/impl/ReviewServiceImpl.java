package com.monochrome.tmall.service.impl;

import com.monochrome.tmall.mapper.ReviewMapper;
import com.monochrome.tmall.pojo.Review;
import com.monochrome.tmall.pojo.ReviewExample;
import com.monochrome.tmall.pojo.User;
import com.monochrome.tmall.service.ReviewService;
import com.monochrome.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewMapper reviewMapper;

    @Autowired
    UserService userService;

    @Override
    public void add(Review review) {
        reviewMapper.insert(review);
    }

    @Override
    public void delete(int id) {
        reviewMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Review review) {
        reviewMapper.updateByPrimaryKeySelective(review);
    }

    @Override
    public Review get(int id) {
        return reviewMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Review> list(int pid) {
        ReviewExample reviewExample = new ReviewExample();
        reviewExample.createCriteria().andPidEqualTo(pid);
        reviewExample.setOrderByClause("id desc");
        List<Review> reviews = reviewMapper.selectByExample(reviewExample);
        setUser(reviews);
        return reviews;
    }

    public void setUser(List<Review> reviews) {
        for (Review review :
                reviews) {
            setUser(review);
        }
    }

    private void setUser(Review review) {
        User user = userService.get(review.getUid());
        review.setUser(user);
    }

    @Override
    public int getCount(int pid) {
        return list(pid).size();
    }
}
