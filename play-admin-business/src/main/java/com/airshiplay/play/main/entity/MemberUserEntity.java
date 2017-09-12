package com.airshiplay.play.main.entity;

import com.airlenet.core.PlayPatterns;
import com.airlenet.repo.jpa.DataEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 业务用户、会员等
 * 
 * @author lig
 *
 */
@Table(name = "business_member")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class MemberUserEntity extends DataEntity<AdminUserEntity, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 2, max = 50)
	@Column(length = 100)
	private String nickname;

	@Size(min = 4, max = 50)
	@Column(unique = true, length = 100)
	private String username;

	@Column(length = 10)
	private String realname = "";

	@Column(length = 225)
	private String password;

	@Pattern(regexp = PlayPatterns.REGEX_MOBILE)
	@Column(unique = true, length = 50)
	private String mobile;

	@Pattern(regexp = PlayPatterns.REGEX_MAIL)
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
	private AreaEntity area;

	@Column(length = 200)
	private String areaName;

	@ManyToOne()
	@JoinColumn(name = "rank_id")
	private MemberRankEntity rank;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
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

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public AreaEntity getArea() {
		return area;
	}

	public void setArea(AreaEntity area) {
		this.area = area;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public MemberRankEntity getRank() {
		return rank;
	}

	public void setRank(MemberRankEntity rank) {
		this.rank = rank;
	}

}
