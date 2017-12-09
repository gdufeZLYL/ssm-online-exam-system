package com.zzqnxx.exz.service.impl;

import com.zzqnxx.exz.dao.PostDao;
import com.zzqnxx.exz.entity.Post;
import com.zzqnxx.exz.service.PostService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("postService")
public class PostServiceImpl implements PostService {

    private static Log LOG = LogFactory.getLog(PostServiceImpl.class);
    @Autowired
    private PostDao postDao;

    @Override
    public List<Post> getPostList() {
        return postDao.queryAllPost();
    }
}
