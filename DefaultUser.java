
public class DefaultUser implements User {
	
	private String firstName;
	private String lastName;
	private int id;
	private String email;
	private String password;
	private static int userCnt;
	
	{
		
		id = ++userCnt;
		
	}
	
	
	public DefaultUser() {
	}
	
	public DefaultUser(String firstName, String lastName, String password, String email) {
		this.email=email;
		this.firstName=firstName;
		this.lastName = lastName;
		this.password = password;
	}

	@Override
	public String getFirstName() {
		return this.firstName;
	}

	@Override
	public String getLastName() {
		return this.lastName;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getEmail() {
		return this.email;
	}
	
	

	@Override
	public String toString() {
		return "First Name: " + this.getFirstName() + "\t\t" +
				"Last Name: " + this.getLastName() + "\t\t" +
				"Email: " + this.getEmail();
	}

	@Override
	public void setPassword(String password) {
		if(password== null)
			return;
		
		this.password = password;
	}

	@Override
	public void setEmail(String newEmail) {
		if(newEmail == null)
			return;
		this.email = newEmail;
	}

	@Override
	public int getId() {
		return id;
	}
	
	void clearState() {
		userCnt=0;
	}

}
