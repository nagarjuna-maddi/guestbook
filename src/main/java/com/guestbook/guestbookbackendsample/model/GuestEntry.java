package com.guestbook.guestbookbackendsample.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblGuestEntry")
public class GuestEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long guestEntryId;
	
	@Column(name = "userId")
	private long userId;

	@Column(name = "comment")
	private String comment;

	@Column(name = "image")
	private Blob image;

	public long getGuestEntryId() {
		return guestEntryId;
	}
	
	public void setGuestEntryId(long guestEntryId) {
		this.guestEntryId = guestEntryId;
	}
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}


	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	

}
