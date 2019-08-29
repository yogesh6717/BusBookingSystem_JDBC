package com.dev.controller;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.dev.beans.Available;
import com.dev.beans.Bus;
import com.dev.beans.Suggestion;
import com.dev.beans.User;
import com.dev.exception.BusCreateFailException;
import com.dev.exception.BusDeleteFailException;
import com.dev.exception.BusNotFoundException;
import com.dev.exception.LoginException;
import com.dev.exception.UpdateException;
import com.dev.service.Service;
import com.dev.service.ServiceImpl;

public class AdminApp {
	static Service service = new ServiceImpl();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		// admin login

		Boolean admin = false;
		try {
			admin = adminLogin();
		} catch (LoginException e) {
			System.out.println(e.getMessage());
		}
		if (admin) {
			System.out.println("Login Succesful");
			boolean bo = true;
			while (bo) {
				System.out.println("*****************************" + "\n" + "1.Search User" + "\n" + "2.Add Bus" + "\n"
						+ "3.Update Bus" + "\n" + "4.Delete Bus" + "\n" + "5.Set Bus Availability" + "\n"
						+ "6.Search Bus" + "\n" + "7.View Feedback" + "\n" + "8.Exit" + "\n"
						+ "*****************************");

				int ad = Integer.parseInt(sc.next());

				switch (ad) {
				case 1:
					searchUser(); //

					break;
				case 2:
					try {
						createBus();
					} catch (BusCreateFailException e) {
						System.out.println(e.getMessage());
					}
					break;

				case 3:
					try {
						updateBus();
					} catch (UpdateException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 4:
					try {
						deleteBus();
					} catch (BusDeleteFailException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 5:
					setAvailability();
					break;
				case 6:
					try {
						searchBus();
					} catch (BusNotFoundException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 7:
					getSuggestion();
					break;
				case 8:
					bo = false;
					System.out.println("*****************************");

					break;
				default:
					System.out.println("Incorrect Option");
					break;
				}
			}
		}

		else {
			System.out.println("Login Fail");
		}
	}

	// admin login

	private static Boolean adminLogin() throws LoginException {

		System.out.println("Enter Admin id:");
		int adminid = Integer.parseInt(sc.next());
		System.out.println("Enter Admin password:");
		String password = sc.next();
		if (service.adminLogin(adminid, password)) {
			return true;
		} else {
			throw new LoginException("Admin Login Fail Exception");
		}
	}

	// search user

	private static void searchUser() {
		System.out.println("Enter UserID");
		int userid = Integer.parseInt(sc.next());
		User user = service.searchUser(userid);
		if (user != null) {
			System.out.println(user);
		} else {
			System.out.println("Fail to Search");
		}
	}

	// create bus

	private static void createBus() throws BusCreateFailException {
		Bus bus = new Bus();
		System.out.println("Enter Bus Id");
		bus.setBusId(Integer.parseInt(sc.next()));
		System.out.println("Enter BusName");
		bus.setBusName(sc.next());
		System.out.println("Enter Bus type");
		bus.setBusType(sc.next());
		System.out.println("Enter Source");
		bus.setSource(sc.next());
		System.out.println("Enter Destination");
		bus.setDestination(sc.next());
		System.out.println("Enter Total Seats");
		bus.setTotalSeats(Integer.parseInt(sc.next()));
		System.out.println("Enter Price");
		bus.setPrice(Double.parseDouble(sc.next()));

		boolean creb = service.createBus(bus);
		if (creb) {
			System.out.println("Bus created");
		} else {
			throw new BusCreateFailException("Fail to Create Bus Exception");
		}

	}

	// update bus

	private static void updateBus() throws UpdateException {
		Bus bus = new Bus();
		System.out.println("Enter Bus Id");
		bus.setBusId(Integer.parseInt(sc.next()));
		System.out.println("Enter New BusName");
		bus.setBusName(sc.next());
		System.out.println("Enter New Source");
		bus.setSource(sc.next());
		System.out.println("Enter New Destination");
		bus.setDestination(sc.next());
		System.out.println("Enter New Bus type");
		bus.setBusType(sc.next());
		System.out.println("Enter New Total Seats");
		bus.setTotalSeats(Integer.parseInt(sc.next()));
		System.out.println("Enter New Price");
		bus.setPrice(Double.parseDouble(sc.next()));

		boolean upbus = service.updateBus(bus);
		if (upbus) {
			System.out.println("Bus Successfully Updated");
		} else {
			throw new UpdateException("Fail to Update Bus Exception");
		}
	}

	// delete bus

	private static void deleteBus() throws BusDeleteFailException {
		System.out.println("Enter Bus Id");
		int busid = Integer.parseInt(sc.next());
		boolean delbus = service.deletebus(busid);
		if (delbus) {
			System.out.println("Bus Successfully Deleted");
		} else {
			throw new BusDeleteFailException("Fail to Delete Bus Exception");
		}
	}

	// search bus

	private static void searchBus() throws BusNotFoundException {

		boolean busCheck = true;
		Integer busId = 0;
		while (busCheck) {
			System.out.println("Enter BusId");
			Integer tempId = service.regex(sc.next());
			if (tempId != null) {
				busId = tempId;
				busCheck = false;
			} else {
				System.out.println("User id should be number !");

			}
		}

		Bus bus = service.searchBus(busId);
		if (bus != null) {
			System.out.println(bus);
		} else {
			throw new BusNotFoundException("Bus Not Found Exception");
		}

	}

	// set availability

	private static void setAvailability() {
		Available available = new Available();
		System.out.println("Enter the busId");
		int busId = Integer.parseInt(sc.next());
		Bus bus = service.searchBus(busId);
		if (bus != null) {
			System.out.println(bus);
			available.setBusId(busId);
			System.out.println("Enter the Available seats");
			available.setAvailableSeats(Integer.parseInt(sc.next()));
			System.out.println("Enter the date(yyyy-mm-dd)");
			String tempDate = sc.next();
			Date date = Date.valueOf(tempDate);
			available.setAvailableDate(date);

			if (service.setAvailability(available)) {
				System.out.println("Successfully Set the availability");
			}
		} else {
			System.out.println("Failed to Set the availability");
		}

	}

	// get all suggestions

	private static void getSuggestion() {
		Suggestion sugg = new Suggestion();
		List<Suggestion> li = service.getAllSuggestions(sugg);
		for (Suggestion s : li) {
			System.out.println(s);
		}

	}

}
