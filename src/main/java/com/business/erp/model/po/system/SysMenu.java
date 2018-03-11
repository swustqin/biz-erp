package com.business.erp.model.po.system;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * The system management menu model structure
 *
 * @author jadenQin
 */
@Entity
@DynamicUpdate
@Data
public class SysMenu {
    @Id
    private String id;
    private String parentId;
    private String name;
    private String url;
    /**
     * Authorization(Multiple separated by commas，like following：   user:list,user:create)
     */
    private String perms;

    /**
     * Comments:
     * 0：directory   1：menu   2：button
     */
    private Integer type;

    /**
     * sort for menu
     */
    private Integer orderNum;
    private Date entryDatetime;
    private Date updateDateTime;
}
