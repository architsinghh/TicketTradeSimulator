package architsi_CSCI201_Assignment2;

public class Task {
	int time;
	String name;
	int ticketQty;
	int price;

	public Task(int time, String name, int ticketQty, int price) {
		this.time = time;
		this.name = name;
		this.ticketQty = ticketQty;
		this.price = price;
	}
	
	int getTime() {
		return time;
	}
	
	String getName() {
		return name;
	}
	
	int getTicketQty() {
		return ticketQty;
	}
	
	int getPrice() {
		return price;
	}
	
	

}
