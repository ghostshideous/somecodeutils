package com.example.readword.config.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Qin
 * @version 1.0
 * @description shiro的配置文件
 * @since 2020/7/20
 */
@Configuration
@Slf4j
public class ShiroConfig {

    //配置核心安全事务管理器
    @Bean(name="securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("myShiroRealm") GrainRealm myShiroRealm) {
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        manager.setRealm(myShiroRealm);
        return manager;
    }
    //配置自定义的权限登录器
    /**
     * 注入自定义的Realm
     *
     * @return
     */
    @Bean(name="myShiroRealm")
    public GrainRealm getSecurityManager() {
        //将自定义的realm交给SecurityManager管理
        GrainRealm grainRealm = new GrainRealm();
        log.info("=====> GraimRealm 注册成功");
        return grainRealm;
    }


    /**
     * 配置Shiro的Web过滤器，拦截浏览器请求并交给SecurityManager处理
     *
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean webFilter() {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager(getSecurityManager()));

        //配置拦截链 使用LinkedHashMap,因为LinkedHashMap是有序的，shiro会根据添加的顺序进行拦截
        // Map<K,V> K指的是拦截的url V值的是该url是否拦截
        Map<String,String> filterChainMap = new LinkedHashMap<String,String>(16);
        //配置退出过滤器logout，由shiro实现
//        filterChainMap.put("/logout","logout");
//        //authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问,先配置anon再配置authc。
//        filterChainMap.put("/login","anon");
//        filterChainMap.put("/**", "authc");
//        filterChainMap.put("/doLogin", "anon");
//        filterChainMap.put("/upload/**","auth");//需要验证
//      //后台路径
//       filterChainMap.put("/system/**", "kick,auth,perms[\"system\"]");
//        //设置默认登录的URL.
//        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilterFactoryBean;
    }
}
