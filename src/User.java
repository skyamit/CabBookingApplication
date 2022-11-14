class User {
	
	private String name;
	private Character gender;
	private Integer age;

	public User() {
		this.name = "";
		this.gender = ' ';
		this.age = 0;
	}

	public User(String name, Character gender, Integer age) {
		this.name = name;
		this.gender = gender;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public Character getGender() {
		return gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public void setName(String name) {
		this.name = name;
	}

}