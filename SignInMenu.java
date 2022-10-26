import java.util.Scanner;

public class SignInMenu implements Menu {

	private ApplicationContext context;
	private UserManagementService userManagementService;

	{
		context = ApplicationContext.getInstance();
		userManagementService = DefaultUserManagementService.getInstance();
	}

	Scanner sc = new Scanner(System.in);

	@Override
	public void start() {

		System.out.print("Please, enter your first email: ");
		String email = sc.next();
		System.out.print("Please, enter your password: ");
		String password = sc.next();

		boolean succesfulAutentification = false;
		for (User user : userManagementService.getUsers()) {
			if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
				context.setLoggedInUser(user);
				System.out.println("Glad to see you back " + user.getFirstName() + " " + user.getLastName() + "!");
				succesfulAutentification = true;
			}
		}

		if (!succesfulAutentification)
			System.out.println("Unfortunately, such login and password doesn’t exist");

		context.getMainMenu().start();

	}

	@Override
	public void printMenuHeader() {
		System.out.println("~ Sing In Menu ~");
	}

}
