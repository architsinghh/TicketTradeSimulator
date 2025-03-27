package architsi_CSCI201_Assignment2;

public class Exhibit {
	String name;
	String exhibit;
	String localDate;
	String museum;
	String agents;

	public Exhibit(String name, String exhibit, String localDate, String museum, String agents) {
		this.name = name;
		this.exhibit = exhibit;
		this.localDate = localDate;
		this.museum = museum;
		this.agents = agents;
	}
	
	String getName() {
		return name;
	}
	
	String getExhibit() {
		return exhibit;
	}
	
	String getLocalDate() {
		return localDate;
	}
	
	String getMuseum() {
		return museum;
	}
	
	String getAgents() {
		return agents;
	}
	
	void printExhibit() {
		System.out.println(name + ", " + exhibit + ", on " + localDate + ", held at " + museum);
	}
	

	
	

}
