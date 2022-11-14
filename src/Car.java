class Car {

	private String carName;
	private String carNumber;

	public Car(){
		carName = "";
		carNumber = "";
	}

	public Car(String carName, String carNumber) {
		this.carName = carName;
		this.carNumber = carNumber;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getCarName() {
		return carName;
	}

	public String getCarNumber() {
		return carNumber;
	}

}
