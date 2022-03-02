package com.guestbook.guestbookbackendsample.dto;

import java.awt.image.BufferedImage;

public class GuestEntryDto {
	private long guestEntryId;

	private long userId;

	private String comment;

	private BufferedImage image;

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

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
