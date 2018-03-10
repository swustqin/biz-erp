package com.business.erp.model.po.system;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * The system login account
 *
 * @author jadenQin
 */
@Entity
@DynamicUpdate
@Data
public class SysUser implements Serializable {
    @Id
    private Long id;
    private String username;
    private String password;
    private String email;
    private String mobile;
    /**
     * Comments:
     * 0: normal    1: error
     */
    private String userStatus;
    private Integer entryId;
    private Date entryDatetime;
    private Integer updateId;
    private Date updateDatetime;
    /**
     * Comment:
     * 0: supermanager  1: others
     */
    private Integer superManager;
    private String name;
    private String workshopId;
    private Date lastLoginDate;
}
