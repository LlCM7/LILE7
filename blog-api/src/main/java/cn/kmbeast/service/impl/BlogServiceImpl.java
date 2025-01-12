package cn.kmbeast.service.impl;

import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.mapper.BlogMapper;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.PageResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.BlogQueryDto;
import cn.kmbeast.pojo.entity.Blog;
import cn.kmbeast.service.BlogService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogMapper blogMapper;

    /**
     * 博客新增
     *
     * @param blog 博客信息
     * @return Result<Void>
     */
    @Override
    public Result<Void> save(Blog blog) {
        // 1. 时间要设置
        blog.setCreateTime(LocalDateTime.now());
        // 2. 字数要统计 --- 富文本
        String blogContent = blog.getContent();
        Document document = Jsoup.parse(blogContent);
        String content = document.body().text();
        blog.setFrontNum(content.length());
        // 3. 设置用户ID
        blog.setUserId(LocalThreadHolder.getUserId());
        blogMapper.save(blog);
        return ApiResult.success();
    }

    /**
     * 博客修改
     *
     * @param blog 博客信息
     * @return Result<Void>
     */
    @Override
    public Result<Void> update(Blog blog) {
        blogMapper.update(blog);
        return ApiResult.success();
    }

    /**
     * 博客删除
     *
     * @param ids 博客的ID列表
     * @return Result<Void>
     */
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        blogMapper.batchDelete(ids);
        return ApiResult.success();
    }

    /**
     * 博客查询
     *
     * @param blogQueryDto 查询参数
     * @return Result<Void>
     */
    @Override
    public Result<List<Blog>> query(BlogQueryDto blogQueryDto) {
        List<Blog> blogList = blogMapper.query(blogQueryDto);
        int pageCount = blogMapper.queryCount(blogQueryDto);
        return PageResult.success(blogList, pageCount);
    }
}
