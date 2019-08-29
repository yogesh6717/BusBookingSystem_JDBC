package com.dev.dao;

import java.util.List;

import com.dev.beans.Available;
import com.dev.beans.Bus;
import com.dev.beans.Suggestion;
import com.dev.beans.Ticket;
import com.dev.beans.User;

public interface BusBookingDAO {

	// user manipulation
	public Boolean createUser(User user);

	public Boolean updateUser(User user);

	public Boolean deleteUser(int user_id, String password);

	public User loginUser(int user_id, String password);

	public User searchUser(int user_id);

	// bus manipulations
	public Boolean createBus(Bus bus);

	public Boolean updateBus(Bus bus);

	public Bus searchBus(int bus_id);

	public Boolean deletebus(int bus_id);

	// admin
	public Boolean adminLogin(int admin_id, String password);

	// ticket booking
	public Ticket bookTicket(Ticket ticket);

	public Boolean cancelTicket(int booking_id);

	public Ticket getTicket(int booking_id);

	public List<Bus> checkAvailability(String source, String destination, java.sql.Date date);

	public Integer checkAvailability(int bus_id, java.sql.Date date);

	public Boolean setAvailability(Available available);

	// suggestions
	public Boolean giveFeedback(int userId, String feedback);

	public List<Suggestion> getAllSuggestions(Suggestion sugg);

}
