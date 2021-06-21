package com.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "vocab")
public class Vocabulary implements Serializable {

	private static final long serialVersionUID = 5357304115478804852L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Integer id;

	private String word;
	private String pronounce;
	private String translate;
	private int count = 0;

	@CreatedDate
	@Column(name = "created_date", updatable = false)
	private Date created_date;

	@LastModifiedDate
	private Date lastModified;

	public Vocabulary(String word, String pronounce, String translate) {
		this.word = word;
		this.pronounce = pronounce;
		this.translate = translate;
	}

	private boolean equal(String s1, String s2) {
		return s1.trim().equalsIgnoreCase(s2.trim());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vocabulary) {
			Vocabulary vcb = (Vocabulary) obj;
			if (equal(this.word, vcb.word) && equal(this.pronounce, vcb.pronounce)
					&& equal(this.translate, vcb.translate)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.word.hashCode();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
