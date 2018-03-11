package com.business.erp.service.system;

import com.business.erp.model.po.system.SysUser;
import com.business.erp.service.AbstractCommonService;

public interface UserService extends AbstractCommonService {

    SysUser findUser(String username);

    SysUser save(SysUser sysUser);

}
