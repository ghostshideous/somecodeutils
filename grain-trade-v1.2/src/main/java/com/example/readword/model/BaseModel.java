package com.example.readword.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;


    //逻辑删除  0不删除，1删除
//    @TableField("is_delete")
//    @TableLogic
//    private Integer isDelete;

}
