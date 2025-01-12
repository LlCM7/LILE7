package cn.kmbeast.pojo.dto.query.extend;

import cn.kmbeast.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BlogQueryDto extends QueryDto {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 标题
     */
    private String title;

}
