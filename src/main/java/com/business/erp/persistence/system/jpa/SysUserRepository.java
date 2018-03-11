package com.business.erp.persistence.system.jpa;

import com.business.erp.model.po.system.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tuchuntong
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUser, Long> {

    SysUser findByName(String name);

}
