package com.business.erp.service.system.impl;

import com.business.erp.model.po.system.SysUser;
import com.business.erp.persistence.system.jpa.SysUserRepository;
import com.business.erp.persistence.system.mapper.UserMapper;
import com.business.erp.service.system.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * @author tuchuntong
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private SysUserRepository userRepository;

    @Resource
    private UserMapper userMapper;

    @Override
    public SysUser findUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public SysUser save(SysUser sysUser) {
        return userRepository.save(sysUser);
    }

}
