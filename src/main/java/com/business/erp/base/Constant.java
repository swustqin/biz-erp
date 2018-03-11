package com.business.erp.base;

/**
 * Configure parameter manager
 * Default empty object
 * (配置参数管理者)
 *
 * @author tuchuntong
 */
public class Constant {

    /**
     * Menu type
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        private MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * -----------用户状态-----------
     **/
    public static final Integer USER_STATUS_DISABLE = 1;// 用户状态-禁用
    public static final Integer USER_STATUS_ENABLE = 0; // 用户状态-启用
    public static final Integer SUPER_ADMIN = 1;// 超级管理员
}
