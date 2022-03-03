package com.guestbook.guestbookbackendsample.dto;

public class GuestEntryDto {

	private long guestEntryId;
	
	private long userId;

	private String comment;

	private String image;
	
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "GuestEntryDto [userId=" + userId + ", comment=" + comment + ", image=" + image + "]";
	}

}
