package com.business.erp.service.system;

import com.business.erp.model.bo.SysBaseInfo;
import com.business.erp.model.po.system.SysUser;
import com.business.erp.service.AbstractCommonService;

public interface UserService extends AbstractCommonService {

    SysUser findByAccount(String username);

    SysUser save(SysUser sysUser);

    SysBaseInfo queryBaseInfo(String userName);

}
