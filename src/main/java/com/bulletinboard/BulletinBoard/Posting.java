package com.bulletinboard.BulletinBoard;

import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Posting {
	@Id
	@GeneratedValue
	private long pid;
	
	@Column
	private String poster;
	
	@Column(columnDefinition="TEXT")
	private String content;
	
	@CreationTimestamp
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dateCreated;
	
	@UpdateTimestamp
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dateChanged;

	public Posting() {}
	public Posting(long pid, String poster, String content, java.util.Date dateCreated, java.util.Date dateChanged) {
		this.pid = pid;
		this.poster = poster;
		this.content = content;
		this.dateCreated = dateCreated;
		this.dateChanged = dateChanged;
	}
	
	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public java.util.Date getDateCreated() {
		return dateCreated;
	}
	
	public java.util.Date getDateChanged() {
		return dateChanged;
	}

	public void setDateCreated(java.util.Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public void setDateChanged(java.util.Date dateChanged) {
		this.dateChanged = dateChanged;
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, dateCreated, pid, poster, dateChanged);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posting other = (Posting) obj;
		return Objects.equals(content, other.content) && Objects.equals(dateCreated, other.dateCreated)
				&& pid == other.pid && Objects.equals(poster, other.poster) && Objects.equals(dateChanged, other.dateChanged);
	}

	@Override
	public String toString() {
		return "Posting [pid=" + pid + ", poster=" + poster + ", content=" + content + ", dateCreated=" + dateCreated
				+ "]";
	}
}
