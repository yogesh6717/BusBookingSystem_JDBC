package com.dev.beans;

import java.sql.Date;
import java.sql.Timestamp;

public class Ticket {
	java.sql.Date sd1=new java.sql.Date(System.currentTimeMillis());
	java.util.Date ud1=new java.util.Date(sd1.getTime());
	
	private int bookingId;
	private Date journeyDate;
	private int noofSeats;
	private Timestamp dateTime;
	private int busId;
	private int userId;
	
	public int getBusId() {
		return busId;
	}
	public void setBusId(int busId) {
		this.busId = busId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public Date getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(Date  journeyDate) {
		this.journeyDate = journeyDate;
	}
	public int getNoofSeats() {
		return noofSeats;
	}
	public void setNoofSeats(int noofSeats) {
		this.noofSeats = noofSeats;
	}
	public Timestamp getDateTime() {
		return dateTime;
	}
	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}
	@Override
	public String toString() {
		return "Ticket [bookingId=" + bookingId + ", journeyDate=" + journeyDate + ", noofSeats=" + noofSeats
				+ ", dateTime=" + dateTime + ", busId=" + busId + ", userId=" + userId + "]";
	}
	
	
	
	

}
