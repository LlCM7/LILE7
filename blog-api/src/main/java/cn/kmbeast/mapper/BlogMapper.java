package cn.kmbeast.mapper;

import cn.kmbeast.pojo.dto.query.extend.BlogQueryDto;
import cn.kmbeast.pojo.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 博客持久化接口
 */
@Mapper
public interface BlogMapper {


    /**
     * 博客信息新增
     *
     * @param blog 博客信息
     * @return int 受影响行数
     */
    int save(Blog blog);

    /**
     * 分页查询博客信息
     *
     * @param blogQueryDto 分页查询参数
     * @return List<Blog>
     */
    List<Blog> query(BlogQueryDto blogQueryDto);

    /**
     * 查询满足分页查询的记录总数
     *
     * @param blogQueryDto 分页查询参数
     * @return int 数据总数
     */
    int queryCount(BlogQueryDto blogQueryDto);

    /**
     * 更新博客信息
     *
     * @param blog 博客信息
     * @return int 受影响行数
     */
    int update(Blog blog);

    /**
     * 批量删除博客信息
     *
     * @param ids 博客ID集合
     */
    void batchDelete(@Param(value = "ids") List<Integer> ids);

}
