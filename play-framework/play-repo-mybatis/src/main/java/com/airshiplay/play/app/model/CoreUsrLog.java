package com.airshiplay.play.app.model;

import java.io.Serializable;
import java.util.Date;

public class CoreUsrLog implements Serializable {
    /**
     * database column <code>core_usr_log.id</code>
     */
    private Long id;

    /**
     * database column <code>core_usr_log.user_id</code>
     */
    private Long userId;

    /**
     * 登录时间
     * <p> 
     * database column <code>core_usr_log.login_time</code>
     */
    private Date loginTime;

    /**
     * 登录IP
     * <p> 
     * database column <code>core_usr_log.login_ip</code>
     */
    private Long loginIp;

    /**
     * 登录地址
     * <p> 
     * database column <code>core_usr_log.login_address</code>
     */
    private String loginAddress;

    /**
     * 登录操作系统
     * <p> 
     * database column <code>core_usr_log.login_os</code>
     */
    private String loginOs;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table core_usr_log
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取登录时间
     *
     * @return login_time - 登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置登录时间
     *
     * @param loginTime 登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 获取登录IP
     *
     * @return login_ip - 登录IP
     */
    public Long getLoginIp() {
        return loginIp;
    }

    /**
     * 设置登录IP
     *
     * @param loginIp 登录IP
     */
    public void setLoginIp(Long loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * 获取登录地址
     *
     * @return login_address - 登录地址
     */
    public String getLoginAddress() {
        return loginAddress;
    }

    /**
     * 设置登录地址
     *
     * @param loginAddress 登录地址
     */
    public void setLoginAddress(String loginAddress) {
        this.loginAddress = loginAddress == null ? null : loginAddress.trim();
    }

    /**
     * 获取登录操作系统
     *
     * @return login_os - 登录操作系统
     */
    public String getLoginOs() {
        return loginOs;
    }

    /**
     * 设置登录操作系统
     *
     * @param loginOs 登录操作系统
     */
    public void setLoginOs(String loginOs) {
        this.loginOs = loginOs == null ? null : loginOs.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_usr_log
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CoreUsrLog other = (CoreUsrLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getLoginTime() == null ? other.getLoginTime() == null : this.getLoginTime().equals(other.getLoginTime()))
            && (this.getLoginIp() == null ? other.getLoginIp() == null : this.getLoginIp().equals(other.getLoginIp()))
            && (this.getLoginAddress() == null ? other.getLoginAddress() == null : this.getLoginAddress().equals(other.getLoginAddress()))
            && (this.getLoginOs() == null ? other.getLoginOs() == null : this.getLoginOs().equals(other.getLoginOs()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_usr_log
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getLoginTime() == null) ? 0 : getLoginTime().hashCode());
        result = prime * result + ((getLoginIp() == null) ? 0 : getLoginIp().hashCode());
        result = prime * result + ((getLoginAddress() == null) ? 0 : getLoginAddress().hashCode());
        result = prime * result + ((getLoginOs() == null) ? 0 : getLoginOs().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_usr_log
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", loginIp=").append(loginIp);
        sb.append(", loginAddress=").append(loginAddress);
        sb.append(", loginOs=").append(loginOs);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}