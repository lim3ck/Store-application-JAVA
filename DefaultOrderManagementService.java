import java.util.Arrays;

public class DefaultOrderManagementService implements OrderManagementService {

	private static final int DEFAULT_ORDER_CAPACITY = 10;

	private static DefaultOrderManagementService instance;
	private int orderIndex;

	private Order[] orders;

	{
		orders = new Order[DEFAULT_ORDER_CAPACITY];
	}

	public static OrderManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultOrderManagementService();
		}
		return instance;
	}

	@Override
	public void addOrder(Order order) {
		if (order == null)
			return;
		if (orders.length <= orderIndex) {
			orders = Arrays.copyOf(orders, orders.length + 1);
		}
		orders[orderIndex++] = order;
	}

	@Override
	public Order[] getOrdersByUserId(int userId) {

		int orderCounter = 0;

		for (Order order : orders) {
			if (order != null && order.getCustomerId() == userId)
				orderCounter++;
		}

		Order[] userOrders = new Order[orderCounter];
		int k = -1;
		for (Order order : orders) {
			if (order != null && order.getCustomerId() == userId)
				userOrders[++k] = order;
		}

		return userOrders;
	}

	@Override
	public Order[] getOrders() {
		int nonNullOrdersAmount = 0;
		for (Order order : orders) {
			if (order != null) {
				nonNullOrdersAmount++;
			}
		}

		Order[] nonNullOrders = new Order[nonNullOrdersAmount];

		int k = -1;
		for (Order order : orders) {
			if (order != null) {
				nonNullOrders[++k] = order;
			}
		}

		return nonNullOrders;
	}

	void clearServiceState() {
		orderIndex = 0;
		orders = new Order[DEFAULT_ORDER_CAPACITY];
	}

}
