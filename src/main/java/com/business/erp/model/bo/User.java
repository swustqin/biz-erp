package com.business.erp.model.bo;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private static final long serialVersionUID = -5825002053818524360L;

    private Long userId;

    private String account;

    private String pwd;

    private String userName;

    private Date invalDate;

    private String authMode;

    private Integer status;

    private Date unlockDate;

    private String menutYpe;

    private Date lastLoginDate;

    private Integer errCount;

    private Date startDate;

    private Date startDateBegin;

    private Date startDateEnd;

    private Date endDate;

    private String email;

    private String createBy;

    private Date createDate;

    private Long staffId;

    private String ext1;

    private String ext2;

    private String ext3;

    private String photo;//The login of user login(用户登陆头像)

    private String roleName;//The role of the user(用户的角色)

}
