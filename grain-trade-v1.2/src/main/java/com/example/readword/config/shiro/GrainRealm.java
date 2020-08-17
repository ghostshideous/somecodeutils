package com.example.readword.config.shiro;

import com.example.readword.model.shiro.UsersModel;
import com.example.readword.service.shiro.UsersService;
import com.example.readword.serviceimpl.shiro.UsersServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


/**
 * @author Qin
 * @description shiro框架  自定义Realm
 * @since 2020-7-20 11点14分
 */
public class GrainRealm extends AuthorizingRealm {


    @Autowired
    private UsersService userService;

    /**
     * @param principalCollection
     * @return authorizationInfo
     * @descrption 返回设置了权限集合和角色集合的一个authorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 从登陆中获取用户名
        String userName = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //给该用户设置角色  在t_role 中
        authorizationInfo.setRoles(userService.getRoles(userName));
        //给该用户设置权限  在t_permission 中
        authorizationInfo.addStringPermissions(userService.getPerMissions(userName));
        return authorizationInfo;
    }


    /**
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     * @description 用来验证当前登录的用户，获取认证信息。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //根据传入的token获取用户名
        String userName = (String) authenticationToken.getPrincipal();

        //根据用户名从数据库中查询出该用户
        UsersModel user = userService.getUserByUserName(userName);

        if (user != null) {
            //将当前用户设置道session中
            SecurityUtils.getSubject().getSession().setAttribute("user", user);
            // 传入用户名密码进行身份认证  并返回认证信息
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), "GraimRealm");
            return authenticationInfo;
        } else {
            return null;
        }
    }

}
