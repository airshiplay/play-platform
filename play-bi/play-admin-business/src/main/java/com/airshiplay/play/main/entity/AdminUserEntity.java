package com.airshiplay.play.main.entity;

import com.airlenet.play.repo.jpa.DataEntity;
import com.airlenet.play.core.helper.Patterns;
import com.airlenet.play.repo.domain.Lockedable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 管理员、内部员工
 * 
 * @author lig
 *
 */
@Entity
@Table(name = "sys_user")
public class AdminUserEntity extends DataEntity<AdminUserEntity, Long> implements Lockedable, AreaSupport {

	private static final long serialVersionUID = -3554902892978919213L;

	@NotNull
	@Size(min = 2, max = 50)
	@Column(length = 100)
	private String nickname;

	@Size(min = 4, max = 50)
	@Column(unique = true, length = 100)
	private String username;

	@Column(length = 10)
	private String realname ;

	@Column(length = 225)
	private String password;

	@Pattern(regexp = Patterns.REGEX_MOBILE)
	@Column(unique = true, length = 50)
	private String mobile;

	@Pattern(regexp = Patterns.REGEX_MAIL)
	@Column(unique = true, length = 50)
	private String email;

	@Column(length = 500)
	private String photo;

	@Column(length = 64)
	private String salt;

	private Integer age;

	private String sex;

	@DateTimeFormat(pattern = "yyyy年MM月dd日")
	private Date birthday;

	private Date lastLoginDate;

	@Column(length = 50)
	private String lastLoginIp;

	private boolean enabled = true;

	private boolean accountExpired = false;

	private boolean credentialsExpired = false;

	private boolean locked = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "org_id")
	private OrganizationEntity org;

	@ManyToOne(fetch = FetchType.LAZY)
	private AreaEntity area;

	@Column(length = 200)
	private String areaName;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sys_user_to_role")
	private Set<RoleEntity> roles = new HashSet<>();

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String name) {
		this.nickname = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public OrganizationEntity getOrg() {
		return org;
	}

	public void setOrg(OrganizationEntity org) {
		this.org = org;
	}

	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

	@Override
	public boolean isLocked() {
		return locked;
	}

	@Override
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	@Override
	public void markLocked() {
		this.locked = true;
	}

	@Override
	public AreaEntity getArea() {
		return area;
	}

	@Override
	public void setArea(AreaEntity area) {
		this.area = area;
	}

	@Override
	public String getAreaName() {
		return areaName;
	}

	@Override
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

}
