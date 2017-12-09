package com.zzqnxx.exz.dao;

import com.zzqnxx.exz.entity.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostDao {

    /**
     * 添加公告
     * @param post
     * @return
     */
    int insertPost(@Param("post") Post post);

    /**
     * 更新公告
     * @param post
     * @return
     */
    int updatePost(@Param("post") Post post);

    /**
     * 获取所有公告列表
     * @return
     */
    List<Post> queryAllPost();
}
