package com.dev.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.dev.beans.Admin;
import com.dev.beans.Bus;
import com.dev.beans.Suggestion;
import com.dev.beans.Ticket;
import com.dev.beans.User;

public class BusBookingJDBCImpl implements BusBookingDAO{

	public BusBookingJDBCImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	String url = "jdbc:mysql://localhost:3306/busbookingsystem_db"+"?user=root&password=root";
	@Override
	public Boolean createUser(User user) {

		String query = "INSERT INTO users_info values(?,?,?,?,?) ";
		try(Connection conn = DriverManager.getConnection(url);
				PreparedStatement pstmt = conn.prepareStatement(query)){

			pstmt.setInt(1,user.getUserId());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getUserPassword());
			pstmt.setLong(5, user.getContact());
			int i = pstmt.executeUpdate();
			if(i>0)
			{
				return true;
			}
			else
			{
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}


		return false;
	}


	@Override
	public Boolean updateUser(User user) {
		String query = "UPDATE users_info SET username = ?,email = ?,contact = ?"
				+" WHERE user_id = ? AND password = ?";
		try(Connection conn = DriverManager.getConnection(url);
				PreparedStatement pstmt = conn.prepareStatement(query)){ 

			pstmt.setInt(4, user.getUserId());
			pstmt.setLong(3, user.getContact());
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(5, user.getUserPassword());

			int i = pstmt.executeUpdate();
			if(i>=1){
				return true;
			}
			else{
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean deleteUser(int user_id , String password) {
		User user=searchUser(user_id);
		String query = "DELETE FROM users_info WHERE user_id=? AND password=?";

		try(Connection conn = DriverManager.getConnection(url);
				PreparedStatement pstmt = conn.prepareStatement(query)){ 

			pstmt.setInt(1, user.getUserId());
			pstmt.setString(2, user.getUserPassword());
			int i = pstmt.executeUpdate();
			if(i>0)
			{
				return true;
			}
			else
			{
				return false; 
			}

		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}


	@Override
	public User loginUser(int user_id, String password) {
		ResultSet rs = null;
		User user = null;
		String query = "select * from users_info "
				+ "	where user_id=? and password = ?";
		try(Connection conn = DriverManager.getConnection(url);
				PreparedStatement pstmt = conn.prepareStatement(query)){

			pstmt.setInt(1, user_id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()){
				user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserPassword(rs.getString("password"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User searchUser(int user_id) {
		String query = "SELECT * FROM users_info where user_id=?";
		User user=null;
		ResultSet rs = null;


		try(Connection conn = DriverManager.getConnection(url);
				PreparedStatement pstmt = conn.prepareStatement(query)){


			pstmt.setInt(1, user_id);
			rs = pstmt.executeQuery();
			while(rs.next())
			{

				user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setUserPassword(rs.getString("password"));
				user.setContact(rs.getLong("contact"));

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}


	@Override
	public Boolean createBus(Bus bus) {

		String query = "INSERT INTO bus_info values(?,?,?,?,?,?,?) ";
		try(Connection conn = DriverManager.getConnection(url);
				PreparedStatement pstmt = conn.prepareStatement(query)){

			pstmt.setInt(1,bus.getBusId());
			pstmt.setString(2, bus.getBusName());
			pstmt.setString(3, bus.getSource());
			pstmt.setString(4, bus.getDestination());
			pstmt.setString(5, bus.getBusType());
			pstmt.setInt(6, bus.getTotalSeats());
			pstmt.setDouble(7, bus.getPrice());
			int i = pstmt.executeUpdate();
			if(i>0)
			{
				return true;
			}
			else
			{
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}


		return false;
	}

	@Override
	public Boolean updateBus(Bus bus) {
		String query = "UPDATE bus_info SET busname = ?,source = ?,destination = ?,bus_type = ?"
				+ "total_seats = ? , price = ? WHERE bus_id = ?";
		try(Connection conn = DriverManager.getConnection(url);
				PreparedStatement pstmt = conn.prepareStatement(query)){ 
			pstmt.setInt(7, bus.getBusId());
			pstmt.setString(1, bus.getBusName());
			pstmt.setString(2, bus.getSource());
			pstmt.setString(3, bus.getDestination());
			pstmt.setString(4, bus.getBusType());
			pstmt.setInt(5, bus.getTotalSeats());
			pstmt.setDouble(6, bus.getPrice());
			int i = pstmt.executeUpdate();
			if(i>=1){
				return true;
			}
			else{
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Bus searchBus(int bus_id) {
		String query = "SELECT * FROM bus_info where bus_id="+bus_id;
		Bus bus=null;
		try(Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)){

			while(rs.next())
			{

				bus = new Bus();
				bus.setBusId(rs.getInt("bus_id"));
				bus.setBusName(rs.getString("busname"));
				bus.setSource(rs.getString("source"));
				bus.setDestination(rs.getString("destination"));
				bus.setBusType(rs.getString("bus_type"));
				bus.setTotalSeats(rs.getInt("total_seats"));
				bus.setPrice(rs.getDouble("price"));
				return bus;

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bus;
	}

	@Override
	public Boolean deletebus(int bus_id) {
		Bus bus = new Bus(); 
		String query = "DELETE FROM bus_info WHERE bus_id =?";

		try(Connection conn = DriverManager.getConnection(url);
				PreparedStatement pstmt = conn.prepareStatement(query)){ 

			pstmt.setInt(1, bus.getBusId());
			int i = pstmt.executeUpdate();
			if(i>0)
			{
				return true;
			}
			else
			{
				return false;
			}

		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}



	@Override
	public Boolean adminLogin(int admin_id, String password) {
		String query = "SELECT * FROM admin_info where admin_id="+admin_id+" and password='"+password+"'";
		try(Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement();
						ResultSet rs=stmt.executeQuery(query)){

	
			if(rs.next()){
				return true;
						}
			else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
	@Override
	public Ticket bookTicket(Ticket ticket) {
		String query = " INSERT INTO booking_info (bus_id,user_id,journey_date,numofseats,booking_datetime)"
				+ " VALUES(?,?,?,?,?)";
		String passquery = "UPDATE availability SET avail_seats=? WHERE avail_date=? AND bus_id=?";
		int totalseats = checkAvailability(ticket.getBusId(),(java.sql.Date)ticket.getJourneyDate());
		try(Connection conn = DriverManager.getConnection(url);
				PreparedStatement pstmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
				PreparedStatement bookpstmt = conn.prepareStatement(passquery)){
			if(ticket.getNoofSeats()<=totalseats) {
			pstmt.setInt(1,ticket.getBusId());
			pstmt.setInt(2, ticket.getUserId());
			pstmt.setDate(3,(java.sql.Date) ticket.getJourneyDate() );
			pstmt.setInt(4, ticket.getNoofSeats());
			pstmt.setTimestamp(5, new java.sql.Timestamp(new java.util.Date().getTime()));
			
			
			int tick = pstmt.executeUpdate();
			 
			if(tick > -1){
				bookpstmt.setInt(1, totalseats - ticket.getNoofSeats());
				bookpstmt.setDate(2, (java.sql.Date)ticket.getJourneyDate());
				bookpstmt.setInt(3, ticket.getBusId());
				bookpstmt.executeUpdate();
				
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next()) {
				ticket.setBookingId(rs.getInt(1));}
				return ticket;
				}
		
			}
			else
			{
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean cancelTicket(int booking_id) {
		String query="DELETE FROM booking_info WHERE booking_id=? and user_id=?";
		String availQuery="UPDATE  availability SET avail_seats=? WHERE avail_date=? and bus_id=?";
		Ticket ticket=getTicket(booking_id);
		System.out.println(ticket);

		int totalAvailableSeats=checkAvailability(ticket.getBusId(), (java.sql.Date)ticket.getJourneyDate());

		try(Connection conn=DriverManager.getConnection(url);
				PreparedStatement pstmt=conn.prepareStatement(query);
				PreparedStatement availpstmt=conn.prepareStatement(availQuery);
				){
			if(ticket!=null) {
				pstmt.setInt(1, booking_id);
				pstmt.setInt(2, ticket.getUserId());
				pstmt.executeUpdate();
				
				availpstmt.setInt(1, ticket.getNoofSeats()+totalAvailableSeats);
				availpstmt.setDate(2, (java.sql.Date) ticket.getJourneyDate());
				availpstmt.setInt(3, ticket.getBusId());
				availpstmt.executeUpdate();
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Ticket getTicket(int booking_id) {
		String query="SELECT * FROM booking_info where booking_id="+booking_id;

	try(Connection conn=DriverManager.getConnection(url);
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(query)){


		if(rs.next()){

			Ticket ticket=new Ticket();
			ticket.setBookingId(rs.getInt("booking_id"));
			ticket.setBusId(rs.getInt("bus_id"));
			ticket.setUserId(rs.getInt("user_id"));
			ticket.setJourneyDate(rs.getDate("journey_date"));
			ticket.setNoofSeats(rs.getInt("numofseats"));
			ticket.setDateTime(rs.getTimestamp("booking_datetime"));
			return ticket;						
		}

	}catch(Exception e) {
		e.printStackTrace();
	}
	return null;	
}



	@Override
	public List<Bus>checkAvailability(String source,String destination, java.sql.Date date) {
		List <Bus> list = new ArrayList<Bus>();
		ResultSet rs = null;
		Bus bus = null;
		String query = "select * from bus_info INNER JOIN availability" + 
				"  ON bus_info.bus_id=availability.bus_id where source=? AND destination=?" + 
				" AND avail_date=?";
		try(Connection conn = DriverManager.getConnection(url);
				PreparedStatement pstmt = conn.prepareStatement(query)){
			pstmt.setString(1, source);
			pstmt.setString(2, destination);
			pstmt.setDate(3, date);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				bus = new Bus();
				bus.setBusId(rs.getInt("bus_id"));
				bus.setBusName(rs.getString("busname"));
				bus.setSource(rs.getString("source"));
				bus.setDestination(rs.getString("destination"));
				bus.setBusType(rs.getString("bus_type"));
				bus.setTotalSeats(rs.getInt("total_seats"));
				bus.setPrice(rs.getDouble("price"));
				
				
				list.add(bus);
				

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	@Override
	public Integer checkAvailability(int bus_id, java.sql.Date date) {
		String query ="SELECT avail_seats  FROM availability where bus_id="+bus_id+" AND avail_date='"+date+"'";
		String creAvail ="Insert into availability (avail_seats,avail_date,bus_id)"
				+ "values(?,?,?)";
		int seats=0;
		Bus bus=searchBus(bus_id);
		if(bus!=null){
			seats=bus.getTotalSeats();
			
		}
		try(Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement();
				PreparedStatement pstmt = conn.prepareStatement(creAvail);
				ResultSet rs = stmt.executeQuery(query)){

			if(rs.next()){
				seats = rs.getInt("avail_seats");
				return seats;
			}	else {

				pstmt.setInt(1,bus.getTotalSeats());
				pstmt.setDate(2, date);
				pstmt.setInt(3, bus_id);
				pstmt.executeUpdate();
				return seats;
			}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return seats;

	}


	@Override
	public Boolean giveFeedback(int userId, String feedback) {
		String query="INSERT INTO suggestion (user_id,feedback) VALUES (?,?)";
		try(Connection conn=DriverManager.getConnection(url);
			PreparedStatement pstmt=conn.prepareStatement(query);){
			pstmt.setInt(1, userId);
			pstmt.setString(2, feedback);
			pstmt.executeUpdate();
			return true;
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	return false;
}


	@Override
	public List<Suggestion> getAllSuggestions(Suggestion sugg) {
		List<Suggestion> list = new ArrayList<Suggestion>();
		Suggestion suggest = null;
		String query="SELECT * from suggestion";
		try(Connection conn=DriverManager.getConnection(url);
			Statement stmt=conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)){
			
			while(rs.next())
			{
				suggest=new Suggestion();
				suggest.setSuggId(rs.getInt("sugg_id"));
			 suggest.setUserid(rs.getInt("user_id"));
			suggest.setSuggest(rs.getString("feedback"));
				list.add(suggest);
				
			}
			
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	return list;
	}

}
