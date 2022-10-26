import java.util.Scanner;

public class SettingsMenu implements Menu {
	private static final String SETTINGS = "1. Change Password" + System.lineSeparator() + "2. Change Email";

	private ApplicationContext context;

	{
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		Menu settingsMenuToNavigate = null;
		
		settingsLoop: while (true) {
			printMenuHeader();
			System.out.print("User input: ");
			String userInput = sc.next();
			if (userInput.equalsIgnoreCase("menu")) {
				context.getMainMenu().start();
			} else {
				int commandNumber = Integer.parseInt(userInput);
				switch (commandNumber) {
				
				case 1:
					settingsMenuToNavigate = new ChangePasswordMenu();
					break settingsLoop;
					
				case 2:
					settingsMenuToNavigate = new ChangeEmailMenu();
					break settingsLoop;
				default:
					System.out.println("Only 1, 2 allowed. Try one more time");
					continue; 
				}
			}
		}
		
		settingsMenuToNavigate.start();
		
	}

	@Override
	public void printMenuHeader() {
		if(context.getLoggedInUser()== null) {
			System.out.println("You have to be logged in to acces the Setting menu. ");
			context.getMainMenu().start();
		}
		else
			System.out.println(SETTINGS);
	}

}
