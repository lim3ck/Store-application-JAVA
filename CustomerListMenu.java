
public class CustomerListMenu implements Menu {
	private ApplicationContext context;
	private UserManagementService userManagementService;
	
	{
		userManagementService = DefaultUserManagementService.getInstance();
		context = ApplicationContext.getInstance();
	}
	
	@Override
	public void start() {
	
		printMenuHeader();
		User[] customers= userManagementService.getUsers();
		for(User user : customers ) {
			System.out.println(user);
		}
		context.getMainMenu().start();
	}


	@Override
	public void printMenuHeader() {
		System.out.println("~ Customer List ~");
	}

}
