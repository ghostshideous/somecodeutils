package com.example.readword.mapper.shiro;

import com.example.readword.model.shiro.UsersModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Qin
 * @since 2020-08-15
 */
@Repository
@Mapper
public interface UsersMapper extends BaseMapper<UsersModel> {

}
