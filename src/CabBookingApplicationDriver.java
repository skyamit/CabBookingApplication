import java.util.*;

public class CabBookingApplicationDriver {

	static CabBookingApplication cabBookingApp;
	static Scanner sc;

	public static void main(String[] args) {

		cabBookingApp = new CabBookingApplication();
		sc = new Scanner(System.in);

		while(true) {
			System.out.println("--------------------------------------------");
			System.out.println("Options for input : \n" + 
				"1 to Add User \n" +
				"2 to Add Driver \n" + 
				"3 to Find Ride \n" + 
				"4 to Choose Ride \n" + 
				"5 to Exit"); 
			String input = sc.next();
			sc.nextLine();
			switch(input.charAt(0)) {
			case '1' : 
				addUser();
				break;
			case '2' :
				addDriver();
				break;
			case '3' :
				findRide();
				break;
			case '4' :
				chooseRide();
				break;
			case '5' :
				return;
			default : 
				continue;
			}
		}
	}
	
	static void addUser() {
		System.out.println("Enter user details \n" + 
			               "Give input in One Line in format of (userName, gender, age). Ex : Abhishek, M, 23 ");
		String userDetails = sc.nextLine();
		cabBookingApp.add_user(userDetails);
		System.out.println("user added..");
	}

	static void addDriver() {
		System.out.println("Enter driver details \n" + 
			               "Give input in One Line in format of (userName, gender, age). Ex : Driver1, M, 22 ");
		String userDetails = sc.nextLine();

		System.out.println("Enter car details \n" + 
						   "Give input in one Line in format of (carName, carNumber). Ex : Swift, KA-01-12345");
		String carDetails = sc.nextLine();

		System.out.println("Enter current X Co-ordinate of driver :");
		String xCoordinate = sc.nextLine();
		System.out.println("Enter current Y Co-ordinate of driver : ");
		String yCoordinate = sc.nextLine();

		cabBookingApp.add_driver(userDetails, carDetails, Integer.parseInt(xCoordinate), Integer.parseInt(yCoordinate));
		System.out.println("driver added..");
	}

	static void findRide() {
		System.out.println("Enter user name \n");
		String username = sc.nextLine();
		System.out.println("Enter source X Co-ordinate of user :");
		String xCoordinate1 = sc.nextLine();
		System.out.println("Enter source Y Co-ordinate of user : ");
		String yCoordinate1 = sc.nextLine();
		System.out.println("Enter destination X Co-ordinate of user :");
		String xCoordinate2 = sc.nextLine();
		System.out.println("Enter destination Y Co-ordinate of user : ");
		String yCoordinate2 = sc.nextLine();
		cabBookingApp.find_ride(username, Integer.parseInt(xCoordinate1), Integer.parseInt(yCoordinate1), 
			Integer.parseInt(xCoordinate2), Integer.parseInt(yCoordinate2));
	}

	static void chooseRide() {
		System.out.println("Enter username :");
		String username = sc.nextLine();
		System.out.println("Enter driver name :");
		String drivername = sc.nextLine();

		cabBookingApp.choose_ride(username, drivername);

		System.out.println("Ride completed!! Thanks for using us.");
	}
}