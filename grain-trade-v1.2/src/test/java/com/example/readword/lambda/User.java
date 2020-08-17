package com.example.readword.lambda;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Qin
 * @version 1.0
 * @description
 * @since 2020/8/16
 */
@Data
@NoArgsConstructor
public class User {


    private String userId;
    private String username;
    private String nickName;
    private String password;
    private String picPath;
    private String status;
    private String sessionId;
    private Date createTime;
    private Date updateTime;
    private int age;
    private BigDecimal memberNum;

    public User(String userId, String username, String nickName, String password, String picPath, String status, String sessionId, Date createTime, Date updateTime, int age, BigDecimal memberNum) {
        this.userId = userId;
        this.username = username;
        this.nickName = nickName;
        this.password = password;
        this.picPath = picPath;
        this.status = status;
        this.sessionId = sessionId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.age = age;
        this.memberNum = memberNum;
    }
}
