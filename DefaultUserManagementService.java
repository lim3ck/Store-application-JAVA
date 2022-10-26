
import java.util.Arrays;

public class DefaultUserManagementService implements UserManagementService {

	private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user. Please, use another email";
	private static final String EMPTY_EMAIL_ERROR_MESSAGE = "You have to input email to register. Please, try one more time";
	private static final String NO_ERROR_MESSAGE = "";
	private static final int DEFAULT_USERS_CAPACITY = 10;

	private static DefaultUserManagementService instance;
	private User[] users;
	private int userIndex;

	
	{
		users= new User[DEFAULT_USERS_CAPACITY];
	}
	
	private DefaultUserManagementService() {
	}

	@Override
	public String registerUser(User user) {

		if (user == null)
			return NO_ERROR_MESSAGE;
		
		String output = checkEmailValidity(user.getEmail());
		if (output != null && !output.isEmpty() )
			return output;
		
		if (users.length <= userIndex) {
			users = Arrays.copyOf(users, users.length + 1);
		}
		
		users[userIndex++] = user;
		return NO_ERROR_MESSAGE;
	}
	

	private String checkEmailValidity(String email) {
		if(email == null || email.isEmpty())
			return EMPTY_EMAIL_ERROR_MESSAGE;
		for(User user : users) {
			if(user!= null && user.getEmail()!= null && user.getEmail().equalsIgnoreCase(email))
				return NOT_UNIQUE_EMAIL_ERROR_MESSAGE;
		}
		return NO_ERROR_MESSAGE;
	}
	
	public static UserManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultUserManagementService();
		}
		return instance;
	}
	@Override
	public User[] getUsers() {
		int userCounter = 0;
		for (User user : users) {
			if (user != null)
				userCounter++;
		}

		User[] nonNullUsers = new User[userCounter];
		int k = -1;
		for (User user : users) {
			if (user != null)
				nonNullUsers[++k] = user;
		}

		return nonNullUsers;
	}
	
	@Override
	public User getUserByEmail(String userEmail) {

		for (User user : users)
			if (user.getEmail().equalsIgnoreCase(userEmail)&& user!=null)
				return user;

		return null;
	}

	void clearServiceState() {
		userIndex =0;
		users = new User[DEFAULT_USERS_CAPACITY];
	}

}
