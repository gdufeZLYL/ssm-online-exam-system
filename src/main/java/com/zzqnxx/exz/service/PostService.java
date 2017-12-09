package com.zzqnxx.exz.service;

import com.zzqnxx.exz.entity.Post;

import java.util.List;

public interface PostService {

    /**
     * 获取公告列表
     * @return
     */
    public List<Post> getPostList();
}
