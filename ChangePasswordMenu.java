import java.util.Scanner;

public class ChangePasswordMenu implements Menu {
	private ApplicationContext context;

	{

		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		printMenuHeader();
		String newPassword = sc.next();
		context.getLoggedInUser().setPassword(newPassword);
		System.out.println("Your password has been successfully changed. ");
		context.getMainMenu().start();
			

	}

	@Override
	public void printMenuHeader() {
		System.out.println("~ Change Password Menu ~");
		System.out.print("Please enter new password: ");
	}

}
