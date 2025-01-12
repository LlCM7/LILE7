package cn.kmbeast.service;

import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.BlogQueryDto;
import cn.kmbeast.pojo.entity.Blog;

import java.util.List;

public interface BlogService {

    Result<Void> save(Blog blog);

    Result<Void> update(Blog blog);

    Result<Void> batchDelete(List<Integer> ids);

    Result<List<Blog>> query(BlogQueryDto blogQueryDto);

}
