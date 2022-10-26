public class MyOrdersMenu implements Menu {

	private ApplicationContext context;
	private OrderManagementService orderManagementService;

	{
		context = ApplicationContext.getInstance();
		orderManagementService = DefaultOrderManagementService.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		if (context.getLoggedInUser() == null) {
			System.out.println("Please, log in or create new account to see list of your orders.");
			context.getMainMenu().start();
		}
		
		Order[] userOrders = orderManagementService.getOrdersByUserId(context.getLoggedInUser().getId());

		if (userOrders == null || userOrders.length == 0) {
			System.out.println(
					"Unfortunately, you don’t have any orders yet. Navigate back to main menu to place a new order. ");
			context.getMainMenu().start();
		} else {
			for (Order order : userOrders) {
				if (order != null)
					System.out.println(order);
			}
			context.getMainMenu().start();
		}

	}

	@Override
	public void printMenuHeader() {
		System.out.println("~ My Orders ~");
	}

}
