import java.util.Scanner;

public class ChangeEmailMenu implements Menu{

	private ApplicationContext context;
	
	
	{
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {

		printMenuHeader();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String newEmail = sc.next();
		context.getLoggedInUser().setEmail(newEmail);
		System.out.println("Your email has been successfully changed. ");
		context.getMainMenu().start();
	
	}

	@Override
	public void printMenuHeader() {
		
		System.out.println("~ Change Email Menu ~");
		System.out.print("Please enter a new email address: ");
	}
	
}
