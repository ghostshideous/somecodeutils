package com.example.readword.model.shiro;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Qin
 * @since 2020-08-15
 */
@TableName("users")
@Data
public class UsersModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 角色列表
     */
    private String roleId;

    /**
     * 是否锁定 0有效   1无效
     */
    private Integer locked;


    @Override
    public String toString() {
        return "UsersModel{" +
        "id=" + id +
        ", username=" + username +
        ", password=" + password +
        ", salt=" + salt +
        ", roleId=" + roleId +
        ", locked=" + locked +
        "}";
    }
}
