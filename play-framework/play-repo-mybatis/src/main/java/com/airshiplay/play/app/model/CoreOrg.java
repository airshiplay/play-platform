package com.airshiplay.play.app.model;

import java.io.Serializable;

public class CoreOrg implements Serializable {
    /**
     * database column <code>core_org.id</code>
     */
    private Long id;

    /**
     * 上级组织名称
     * <p> 
     * database column <code>core_org.up_id</code>
     */
    private Long upId;

    /**
     * database column <code>core_org.org_name</code>
     */
    private String orgName;

    /**
     * database column <code>core_org.org_desc</code>
     */
    private String orgDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table core_org
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
     * 获取上级组织名称
     *
     * @return up_id - 上级组织名称
     */
    public Long getUpId() {
        return upId;
    }

    /**
     * 设置上级组织名称
     *
     * @param upId 上级组织名称
     */
    public void setUpId(Long upId) {
        this.upId = upId;
    }

    /**
     * @return org_name
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * @param orgName
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    /**
     * @return org_desc
     */
    public String getOrgDesc() {
        return orgDesc;
    }

    /**
     * @param orgDesc
     */
    public void setOrgDesc(String orgDesc) {
        this.orgDesc = orgDesc == null ? null : orgDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_org
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
        CoreOrg other = (CoreOrg) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUpId() == null ? other.getUpId() == null : this.getUpId().equals(other.getUpId()))
            && (this.getOrgName() == null ? other.getOrgName() == null : this.getOrgName().equals(other.getOrgName()))
            && (this.getOrgDesc() == null ? other.getOrgDesc() == null : this.getOrgDesc().equals(other.getOrgDesc()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_org
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUpId() == null) ? 0 : getUpId().hashCode());
        result = prime * result + ((getOrgName() == null) ? 0 : getOrgName().hashCode());
        result = prime * result + ((getOrgDesc() == null) ? 0 : getOrgDesc().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table core_org
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", upId=").append(upId);
        sb.append(", orgName=").append(orgName);
        sb.append(", orgDesc=").append(orgDesc);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}