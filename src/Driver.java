class Driver {

	User user;
	Car car;

	public Driver(){
		user = new User();
		car = new Car();
	}

	public Driver(User user, Car car) {
		this.user = user;
		this.car = car;
	}

	public Car getCar() {
		return car;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setCar(Car car) {
		this.car = car;
	}

}
