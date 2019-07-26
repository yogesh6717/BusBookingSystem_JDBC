package com.dev.service;

import java.sql.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dev.beans.Available;
import com.dev.beans.Bus;
import com.dev.beans.Suggestion;
import com.dev.beans.Ticket;
import com.dev.beans.User;
import com.dev.dao.BusBookingDAO;
import com.dev.dao.BusBookingJDBCImpl;

public class ServiceImpl implements Service {

	java.sql.Date sd1=new java.sql.Date(System.currentTimeMillis());
	java.util.Date utilDate=new java.util.Date(sd1.getTime());


	BusBookingDAO db = new BusBookingJDBCImpl();
	@Override
	public Boolean createUser(User user) {
		return db.createUser(user);
	}

	@Override
	public Boolean updateUser(User user) {
		return db.updateUser(user);
	}
	@Override
	public Boolean deleteUser(int user_id , String password) {
		return db.deleteUser(user_id , password);
	}


	@Override
	public User loginUser(int user_id, String password) {
		return db.loginUser(user_id, password);
	}

	@Override
	public User searchUser(int user_id) {
		return db.searchUser(user_id);
	}

	@Override
	public Boolean createBus(Bus bus) {
		return db.createBus(bus);
	}

	@Override
	public Boolean updateBus(Bus bus) {
		return db.updateBus(bus);
	}

	@Override
	public Bus searchBus(int bus_id) {
		return db.searchBus(bus_id);
	}

	@Override
	public Boolean deletebus(int bus_id) {
		return db.deletebus(bus_id);
	}

	@Override
	public Boolean adminLogin(int admin_id, String password) {
		return db.adminLogin(admin_id, password);
	}

	@Override
	public Ticket bookTicket(Ticket ticket) {
		return db.bookTicket(ticket);
	}

	@Override
	public Boolean cancelTicket(int booking_id) {
		return db.cancelTicket(booking_id);
	}
	
	@Override
	public Boolean setAvailability(Available available) {
		return db.setAvailability(available);
	}
	@Override
	public Ticket getTicket(int booking_id) {
		return db.getTicket(booking_id);
	}

	@Override
	public List<Bus> checkAvailability(String source,String destination,  Date date) {
		return db.checkAvailability(source, destination, date);
	}

	@Override
	public Integer checkAvailability( int bus_id, Date date){
		return db.checkAvailability(bus_id, date);
	}

	@Override
	public Boolean giveFeedback(int userId, String feedback) {
		return db.giveFeedback(userId, feedback);
	}

	@Override
	public List<Suggestion> getAllSuggestions(Suggestion sugg) {
		return db.getAllSuggestions(sugg);
	}

	@Override
	public Integer regex(String id) {
		Pattern pat = Pattern.compile("\\d+");
		Matcher mat = pat.matcher(id);
		if(mat.matches()) {
			return Integer.parseInt(id);
		}else {
			return null;
		}

	}

	@Override
	public String regexemail(String email) {
		Pattern pat = Pattern.compile("\\w+\\@\\w+\\.\\w+");
		Matcher mat = pat.matcher(email);
		if(mat.matches()) {
			return email;
		}else {
			return null;
		}

	}

	@Override
	public Long regexcontact(String contact) {
		Pattern pat = Pattern.compile("\\d{10}");
		Matcher mat = pat.matcher(contact);
		if(mat.matches()) {
			return Long.parseLong(contact);
		}else {
			return null;
		}

	}

	

}


