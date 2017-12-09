package com.zzqnxx.exz.test;

import com.zzqnxx.exz.dao.PostDao;
import com.zzqnxx.exz.entity.Post;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostDaoTest extends BaseTest {

    private static Log LOG = LogFactory.getLog(PostDaoTest.class);
    @Autowired
    private PostDao postDao;

    @Test
    public void testInsertPost() throws Exception {
        Post post = new Post();
        post.setTitle("同学们注意了");
        post.setContent("10月21号（星期二）中午12:20~13：50在一教314进行全校14级新生的素拓考试，考试范围：学生手册218~228，开卷考。大家记得先吃饱饭，到时记得准时到达考场~");
        post.setAuthor("沈永珞");
        int result = postDao.insertPost(post);
        LOG.info("result = " + result);
    }

    @Test
    public void testUpdatePost() throws Exception {
        Post post = new Post();
        post.setId(5);
        post.setTitle("同学们注意了2");
        post.setContent("10月21号（星期二）中午12:20~13：50在一教314进行全校14级新生的素拓考试，考试范围：学生手册218~228，开卷考。大家记得先吃饱饭，到时记得准时到达考场~");
        post.setAuthor("沈永珞");
        int result = postDao.updatePost(post);
        LOG.info("result = " + result);
    }

    @Test
    public void testQueryAllPost() throws Exception {
        List<Post> posts = postDao.queryAllPost();
        for (Post post : posts) {
            LOG.info("id = " + post.getId() + ", title = " + post.getTitle()
                    + ", content = " + post.getContent() + ", author = " + post.getAuthor()
                    + ", createTime = " + post.getCreateTime());
        }
    }
}
