package com.business.erp.model.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * The common base information of ERP system
 * (系统公用基础信息)
 */
@Data
public class SysBaseInfo implements Serializable {

    private long userId;

    private String userName;

    private long empId;

    private String phone;

    private String empCode;

    private String empName;

    private String realName;

    private long orgId;

    private String orgCode;

    private String orgName;
}
