package com.guestbook.guestbookbackendsample.dto;

public class GuestEntryDto {

	private long guestEntryId;

	private long userId;

	private String userName;

	private String comment;

	private byte[] image;

	private String imageType;

	private String imageName;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "GuestEntryDto [guestEntryId=" + guestEntryId + ", userId=" + userId + ", comment=" + comment
				+ ", image=" + image + ", status=" + status + "]";
	}

}
