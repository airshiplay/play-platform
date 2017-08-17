package com.airshiplay.play.cms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.repo.jpa.SortEntity;

@Entity
@Table(name = "cms_question")
public class QuestionEntity extends SortEntity<AdminUserEntity, Long> {

	private static final long serialVersionUID = -6205570576129073122L;

	@NotNull
	@Size(min = 1, max = 200)
	@Column(nullable = false, length = 200)
	private String title;

	@Lob
	private String content;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
