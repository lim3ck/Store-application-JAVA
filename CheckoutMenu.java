import java.util.Scanner;

public class CheckoutMenu implements Menu {

	private ApplicationContext context;
	private OrderManagementService orderManagementService;

	{
		context = ApplicationContext.getInstance();
		orderManagementService = DefaultOrderManagementService.getInstance();
	}

	@Override
	public void start() {
		while (true) {
			printMenuHeader();
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			String creditCard = sc.next();
			if (!createOrder(creditCard)) {
				System.out.println("You entered invalid credit card number. Valid credit card should contain 16 digits. Please, try one more time.");
				
				continue;
			}
			context.getSessionCart().clear();
			break;

		}
		System.out.println("Thanks a lot for your purchase. Details about order delivery are sent to your email.");
		new MainMenu().start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println("~ Checkout Menu ~");
		System.out.println("Enter your credit card number without spaces and press enter if you confirm purchase: ");
	}

	private boolean createOrder(String creditCardNumber) {

		Order order = new DefaultOrder();
		if (!order.isCreditCardNumberValid(creditCardNumber)) {
			return false;
		}
		order.setCreditCardNumber(creditCardNumber);
		order.setProducts(context.getSessionCart().getProducts());
		order.setCustomerId(context.getLoggedInUser().getId());
		orderManagementService.addOrder(order);
		return true;
	}

}
