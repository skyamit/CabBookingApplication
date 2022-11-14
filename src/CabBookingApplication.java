/*
Assumption : 
1. All users and drivers names are different
2. Driver need to call add_driver method, to make himself visible
3. Once a valid ride is found, then we we are updating the driver to new location
*/
import java.util.*;
import java.lang.Math.*;

public class CabBookingApplication {

	private static Driver[][] driversLocation;
	private static HashSet<User> users;
	private static HashSet<String> usersNames;
	private static HashMap<String, Position> source;
	private static HashMap<String, Position> destination;
	private static int[][] directions =  {{0,1}, {1,0}, {0,-1}, {-1,0}, {1,-1}, {-1,1}, {1,1}, {-1,-1}};

	CabBookingApplication(){
		driversLocation = new Driver[2000][2000];
		users = new HashSet<>();
		usersNames = new HashSet<>();
		source = new HashMap<>();
		destination = new HashMap<>();
	}

	// add the user to users list
	public static void add_user(String details) {
		String[] detail = details.split(", ");

		if(detail.length != 3) {
			System.out.println("Incorrect details!!");
			return;
		}

		User user = new User(detail[0],detail[1].charAt(0), Integer.parseInt(detail[2]));
		users.add(user);
		usersNames.add(detail[0]);
	}

	// add the driver to matrix
	public static void add_driver(String userDetails, String carDetails, int xCoordinate, int yCoordinate) {
		String[] userDetail = userDetails.split(", ");
		String[] carDetail = carDetails.split(", ");

		if(userDetail.length != 3 || carDetail.length != 2){
			System.out.println("Incorrect Details!!");
			return;
		}

		User user = new User(userDetail[0], userDetail[1].charAt(0), Integer.parseInt(userDetail[2]));
		Car car = new Car(carDetail[0], carDetail[1]);

		driversLocation[xCoordinate][yCoordinate] = new Driver(user, car);
	}

	public static Set<String> find_ride(String name, int fromXCoordinate, int fromYCoordinate, int toXCoordinate, int toYCoordinate) {

		if(!usersNames.contains(name)) {
			System.out.println("User not found");
			return new HashSet<>();
		}

		source.put(name, new Position(fromXCoordinate,fromYCoordinate));
		destination.put(name, new Position(toXCoordinate, toYCoordinate));

		Set<String> availableDriverNames = checkForDriver(fromXCoordinate, fromYCoordinate, toXCoordinate, toYCoordinate);

		if(availableDriverNames.size()==0){
			System.out.println("No ride found");
		}
		else{
			System.out.println("Available drivers are : ");
			for(String availableDriverName : availableDriverNames) {
				System.out.println(availableDriverName);
			}
		}

		return availableDriverNames;
	}

	public static Set<String> checkForDriver(int xCoordinate, int yCoordinate, int toXCoordinate, int toYCoordinate) {

		Set<String> availableDriverNames = new HashSet<>();

		Queue<Position> queue = new LinkedList<>();
		queue.offer(new Position(xCoordinate, yCoordinate));

		boolean[][] visited = new boolean[2000][2000];
		visited[xCoordinate][yCoordinate] = true;

		while(!queue.isEmpty()) {

			int size = queue.size();
			while(size-->0) {
				Position position = queue.poll();

				int x = position.xCoordinate;
				int y = position.yCoordinate;

				for(int[] direction : directions) {
					int newX = x + direction[0];
					int newY = y + direction[1];

					if(newX<0 || newY<0 || newX>=driversLocation.length || newY>=driversLocation[0].length || visited[newX][newY]) {
						continue;
					}

					if(driversLocation[newX][newY] != null &&  
						Math.sqrt((newX-xCoordinate)*(newX-xCoordinate) + (newY-yCoordinate)*(newY-yCoordinate)) <= 5) {
						String username = driversLocation[newX][newY].getUser().getName();
						
						availableDriverNames.add(username);
						queue.offer(new Position(newX, newY));
					}
					visited[newX][newY] = true;
				}
			}

		}

		return availableDriverNames;
	}

	public void choose_ride(String username, String driverName) {

		if(!usersNames.contains(username)){
			System.out.println("User not found.");
			return;
		}

		if(!source.containsKey(username) || !destination.containsKey(username)){
			System.out.println(username+", Please Find the ride first.");
			return;
		}

		Position sourcePoint = source.get(username);
		Position destinationPoint = destination.get(username);
		Set<String> nearDriverNames = find_ride(username ,sourcePoint.xCoordinate,sourcePoint.yCoordinate,
									  destinationPoint.xCoordinate,destinationPoint.yCoordinate);

		if(!nearDriverNames.contains(driverName)) {
			System.out.println("Driver not found.");
		}

		int xCoordinate = sourcePoint.xCoordinate;
		int yCoordinate = sourcePoint.yCoordinate;

		Queue<Position> queue = new LinkedList<>();
		queue.offer(new Position(xCoordinate, yCoordinate));

		boolean[][] visited = new boolean[2000][2000];
		visited[xCoordinate][yCoordinate] = true;

		while(!queue.isEmpty()) {

			int size = queue.size();
			while(size-->0) {
				Position position = queue.poll();

				int x = position.xCoordinate;
				int y = position.yCoordinate;

				for(int[] direction : directions) {
					int newX = x + direction[0];
					int newY = y + direction[1];

					if(newX<0 || newY<0 || newX>=driversLocation.length || newY>=driversLocation[0].length || visited[newX][newY]) {
						continue;
					}

					if(driversLocation[newX][newY] != null && 
						Math.sqrt((newX-xCoordinate)*(newX-xCoordinate) + (newY-yCoordinate)*(newY-yCoordinate)) <= 5) {

						queue.offer(new Position(newX, newY));		
						
						if(driversLocation[newX][newY].getUser().getName().equals(driverName)) {
							driversLocation[newX][newY] = null;
							source.put(username, new Position(newX, newY));
							return;
						}
					}
					visited[newX][newY] = true;
				}
			}

		}
	}
}


class Position {
	int xCoordinate;
	int yCoordinate;

	public Position(int x, int y) {
		this.xCoordinate = x;
		this.yCoordinate = y;
	}
}
