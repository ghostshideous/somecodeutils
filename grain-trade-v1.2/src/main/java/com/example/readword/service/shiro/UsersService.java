package com.example.readword.service.shiro;

import com.example.readword.model.shiro.UsersModel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Qin
 * @since 2020-08-15
 */
public interface UsersService extends IService<UsersModel> {
    Set<String> getRoles(String userName);

    Collection<String> getPerMissions(String userName);

    UsersModel getUserByUserName(String userName);
}
