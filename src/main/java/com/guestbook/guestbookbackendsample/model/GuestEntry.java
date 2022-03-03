package com.guestbook.guestbookbackendsample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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

//	@Column(name = "image")
//	private Blob image;

	@Lob
	@Column(name = "image")
	private byte[] image;

	@Column(name = "image_type")
	private String imageType;

	@Column(name = "image_name")
	private String imageName;

	@Column(name = "status")
	private String status;

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

//	public Blob getImage() {
//		return image;
//	}
//
//	public void setImage(Blob image) {
//		this.image = image;
//	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getStatus() {
		return status;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
