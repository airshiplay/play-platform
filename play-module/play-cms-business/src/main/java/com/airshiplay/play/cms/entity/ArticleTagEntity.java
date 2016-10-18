package com.airshiplay.play.cms.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import com.airshiplay.play.main.entity.UserEntity;
import com.airshiplay.play.repo.jpa.SortEntity;

@Entity
@Table(name = "cms_article_tag")
public class ArticleTagEntity extends SortEntity<UserEntity, Long> {

	private static final long serialVersionUID = 825612513500826162L;

	/** 名称 */
	@Column(nullable = false, length = 200)
	private String name;

	/** 图标 */
	@Column(length = 200)
	private String icon;

	/** 备注 */
	@Column(length = 200)
	private String memo;

	/** 文章 */
	@ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
	private Set<ArticleEntity> articles = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Set<ArticleEntity> getArticles() {
		return articles;
	}

	public void setArticles(Set<ArticleEntity> articles) {
		this.articles = articles;
	}

	@PreRemove
	public void preRemove() {
		Set<ArticleEntity> articles = getArticles();
		if (articles != null) {
			for (ArticleEntity article : articles) {
				article.getTags().remove(this);
			}
		}
	}

}
