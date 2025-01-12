package cn.kmbeast.controller;

import cn.kmbeast.aop.Pager;
import cn.kmbeast.aop.Protector;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.BlogQueryDto;
import cn.kmbeast.pojo.entity.Blog;
import cn.kmbeast.service.BlogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private BlogService blogService;

    /**
     * 博客新增
     *
     * @param blog 新增入参
     * @return Result<Void> 响应结果
     */
    @PostMapping(value = "/save")
    @ResponseBody
    public Result<Void> save(@RequestBody Blog blog) {
        return blogService.save(blog);
    }

    /**
     * 博客信息修改
     *
     * @param blog 修改信息入参
     * @return Result<Void> 响应结果
     */
    @Protector
    @PostMapping(value = "/update")
    @ResponseBody
    public Result<Void> update(@RequestBody Blog blog) {
        return blogService.update(blog);
    }

    /**
     * 删除博客信息
     */
    @PostMapping(value = "/batchDelete")
    @ResponseBody
    public Result<Void> batchDelete(@RequestBody List<Integer> ids) {
        return blogService.batchDelete(ids);
    }

    /**
     * 查询博客数据
     *
     * @param blogQueryDto 查询参数
     * @return Result<List < Blog>> 响应结果
     */
    @Pager
    @PostMapping(value = "/query")
    @ResponseBody
    public Result<List<Blog>> query(@RequestBody BlogQueryDto blogQueryDto) {
        return blogService.query(blogQueryDto);
    }
}

