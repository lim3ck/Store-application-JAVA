import java.util.Scanner;

public class ProductCatalogMenu implements Menu {

	private static final String CHECKOUT_COMMAND = "checkout";
	private static final String MENU_COMMAND = "menu";

	private ApplicationContext context;
	private ProductManagementService productManagementService;

	{
		context = ApplicationContext.getInstance();
		productManagementService = DefaultProductManagementService.getInstance();
	}

	@Override
	public void start() {

		Menu productCatalogMenuToNavigate;
		while (true) {
			printMenuHeader();
			printProductsToConsole();

			String userResponse = readUserInput();
			if (context.getLoggedInUser() == null) {
				System.out.println("You are not logged in. Please, sign in or create new account");
			}
			
			if (userResponse.equalsIgnoreCase(MENU_COMMAND)) {

				productCatalogMenuToNavigate = new MainMenu();
				break;
			}

			if (userResponse.equalsIgnoreCase(CHECKOUT_COMMAND)) {
				Cart sessionCart = context.getSessionCart();
				if (sessionCart == null || sessionCart.isEmpty())
					System.out.println(
							"Your cart is empty. Please, add product to cart first and then proceed with checkout");
				else {
					productCatalogMenuToNavigate = new CheckoutMenu();
					break;
				}
			} else {

				Product choosenProduct = chooseProduct(userResponse);

				if (choosenProduct == null) {
					System.out.println(
							"Please, enter product ID if you want to add product to cart. Or enter ‘checkout’ if you want to proceed with checkout. Or enter ‘menu’ if you want to navigate back to the main menu.");
					continue;
				}
				
				addToCart(choosenProduct);
				
			}
		}
		
		productCatalogMenuToNavigate.start();

	}

	private void addToCart(Product choosenProduct) {

		context.getSessionCart().addProduct(choosenProduct);
		System.out.println("Product " + choosenProduct.getProductName() + " has been added to your cart. "
				+ System.lineSeparator() + "If you want to add a new product - enter the product id."
				+ System.lineSeparator() + "If you want to proceed with checkout - enter word \"checkout\" to console ");

	}

	private Product chooseProduct(String userInput) {
		int productIdToAddToCart = Integer.parseInt(userInput);
		Product productToAddToCart = productManagementService.getProductById(productIdToAddToCart);
		return productToAddToCart;
	}

	@Override
	public void printMenuHeader() {
		System.out.println("~ Product Catalog Menu ~");
		System.out.println("Enter product id to add it to the cart or ‘menu’ if you want to navigate back to the main menu");	
	}

	private void printProductsToConsole() {
		Product[] products = productManagementService.getProducts();
		for (Product product : products) {
			System.out.println(product);
		}
	}

	private String readUserInput() {
		System.out.print("Product ID to add to cart or enter 'checkout' to proceed with checkout: ");

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		String userInput = sc.next();
		return userInput;
	}

}
