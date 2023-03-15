package com.atguigu.service.impl;

import com.atguigu.entity.SecurityUser;
import com.atguigu.entity.User;
import com.atguigu.service.PermissionService;
import com.atguigu.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userdetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.selectByUsername(s);
        if(user==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        User cruUser=new User();
        BeanUtils.copyProperties(user,cruUser);
        List<String> permissionValueList=permissionService.selectPermissionValueByUserId(user.getId());
        SecurityUser securityUser=new SecurityUser();
        securityUser.setPermissionValueList(permissionValueList);
        return securityUser;
    }
}
