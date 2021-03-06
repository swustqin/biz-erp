package com.business.erp.base;

import com.business.erp.model.po.system.SysUser;
import com.business.erp.service.system.UserService;
import com.business.erp.support.utils.NetworkUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;


public class ErpShiroRealm extends AuthorizingRealm {

    private Logger log = LoggerFactory.getLogger(ErpShiroRealm.class);

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("authorization management information");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        SysUser user = (SysUser) subject.getPrincipal();
        //查询该用户拥有的所有角色
        //simpleAuthorizationInfo addRole and addStringPermission
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("authentication mangement information");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());
        SysUser user = userService.findUser(username);
        if (user == null) {
            throw new UnknownAccountException();
        } else if (!user.getPassword().equals(password)) {
            throw new IncorrectCredentialsException();
        }
        if (username.equals("admin")) {
            log.info("master account has been certified");
        } else {
            // Others call other authentication methods
        }
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("user", user);
        Date date = new Date();
        user.setLastLoginDate(date);
        userService.save(user);

//        SysBaseInfo baseInfo = userService.queryBaseInfo(user.getUsername());
//        session.setAttribute("baseInfo", baseInfo);

        session.setAttribute("baseInfo", user);

        String ipInfo = NetworkUtil.getConfig();
        session.setAttribute("ip", ipInfo);

        return new SimpleAuthenticationInfo(user, user.getPassword(), user.getName());
    }
}
