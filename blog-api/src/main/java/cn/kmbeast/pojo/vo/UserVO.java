package cn.kmbeast.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 用户VO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {


    /**
     * 用户编号
     */
    private Integer id;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 用户角色
     */
    private Integer userRole;

    /**
     * 可登录状态(0:可用；1：不可用)
     */
    private Boolean isLogin;

    /**
     * 禁言状态(0:可用；1：不可用)
     */
    private Boolean isWord;

    /**
     * 用户联系电话
     */
    private String userPhone;

    /**
     * 用户性别 (0:男；1：女)
     */
    private Boolean userGender;

    /**
     * 上次登录时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;


    /**
     * 用户注册时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
