
import java.util.Scanner;

public class SignUpMenu implements Menu {

	private UserManagementService userManagementService;

	private ApplicationContext context;

	{
		userManagementService = DefaultUserManagementService.getInstance();
		context = ApplicationContext.getInstance();
	}

	Scanner sc = new Scanner(System.in);

	@Override
	public void start() {

		printMenuHeader();
		
		System.out.print("Please, enter your first name: ");
		String firstName = sc.next();
		System.out.print("Please, enter your last name: ");
		String lastName = sc.next();
		System.out.print("Please, provide a password for your account: ");
		String password = sc.next();
		System.out.print("Please, enter your email address: ");
		String email = sc.next();
		User newUser = new DefaultUser(firstName, lastName, password, email);
		String output = userManagementService.registerUser(newUser);
		if(output == null || output.isEmpty()) {
			context.setLoggedInUser(newUser);
			System.out.println("New user is created!");
		}
		else
			System.out.println(output);
		
		context.getMainMenu().start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println("~ Sing Up Menu ~");
	}

}
