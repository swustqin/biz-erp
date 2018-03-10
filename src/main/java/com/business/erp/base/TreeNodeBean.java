package com.business.erp.base;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tuchuntong
 */
@Data
public abstract class TreeNodeBean implements Serializable {
    private String id;
    private String text;
    private String parentId;
    private List<TreeNodeBean> children = new ArrayList<>();

}
