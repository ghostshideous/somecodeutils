package com.example.readword.serviceimpl.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.readword.mapper.shiro.*;
import com.example.readword.model.shiro.*;
import com.example.readword.service.shiro.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Qin
 * @since 2020-08-15
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, UsersModel> implements UsersService {

    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private RolesMapper rolesMapper;
    @Autowired
    private PermissionsMapper permissionsMapper;
    @Autowired
    private UsersRolesMapper usersRolesMapper;
    @Autowired
    private RolesPermissionsMapper rolesPermissionsMapper;

    /**
     * 根据username 得到具体对应的角色集合
     *
     * @param userName
     * @return
     */
    @Override
    public Set<String> getRoles(String userName) {
        List<RolesModel> rolesModels = getRolesPrivate(userName);
        Set<String> roles = rolesModels.stream().map(p -> p.getRole()).collect(Collectors.toSet());
        return roles;
    }

    /**
     * 根据username 得到具体对应的persion权限集合
     *
     * @param userName
     * @return
     */
    @Override
    public Collection<String> getPerMissions(String userName) {
        List<RolesModel> roles = getRolesPrivate(userName);
        List<Long> roleId = roles.stream().map(p -> p.getId()).collect(Collectors.toList());
        List<RolesPermissionsModel> rolesPermissionsModels = rolesPermissionsMapper.selectList(new QueryWrapper<RolesPermissionsModel>().in("rold_id", roleId));
        List<Long> permissionId = rolesPermissionsModels.stream().map(p -> p.getPermissionId()).collect(Collectors.toList());
        List<PermissionsModel> permissionsModels = permissionsMapper.selectList(new QueryWrapper<PermissionsModel>().in("id", permissionId));
        List<String> permission = permissionsModels.stream().map(p -> p.getPermission()).collect(Collectors.toList());
        return permission;
    }

    /**
     * 根据username得到 user对象
     *
     * @param userName
     * @return
     */
    @Override
    public UsersModel getUserByUserName(String userName) {
        UsersModel username = usersMapper.selectOne(new QueryWrapper<UsersModel>().eq("username", userName));
        return username;
    }

    private List<RolesModel> getRolesPrivate(String userName) {
        UsersModel usersModel = usersMapper.selectOne(new QueryWrapper<UsersModel>().eq("username", userName));
        List<UsersRolesModel> usersRolesModels = usersRolesMapper.selectList(new QueryWrapper<UsersRolesModel>().eq("user_id", usersModel.getId()));
        List<Long> collect = usersRolesModels.stream().map(p -> p.getRoleId()).collect(Collectors.toList());
        List<RolesModel> olesModels = rolesMapper.selectList(new QueryWrapper<RolesModel>().in("id", collect));
        return olesModels;
    }


}
