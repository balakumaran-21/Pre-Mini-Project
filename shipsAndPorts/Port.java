package shipsAndPorts;

import java.util.*;

public class Port implements GetSet{
	static Scanner input = new Scanner(System.in);
	private String name;
	private double x;
	private double y;
	private ArrayList<Ship> historyOfDockedShips = new ArrayList<Ship>();
	private int maxContainercapacity;
	private int maxShipDockingCapacity;
	private String location;
	private double totalCharges;
	private ArrayList<Ship> dockedShips = new ArrayList<Ship>();
	private ArrayList<Container> orderContainersInPort = new ArrayList<Container>();
	public Queue<CustomerOrder> orders = Main.orders;
	
	public Port(){
		System.out.print("Enter NAME of the Port: ");
		name = input.nextLine();
		
		System.out.print("Enter LOCATION of the Port: ");
		location = input.nextLine();
		
		System.out.println("Enter LATITUDE of the location: ");
		x = input.nextDouble();
		
		System.out.println("Enter LONGITUDE of the location: ");
		y = input.nextDouble();
		
		System.out.print("Enter MAXIMUM CONTAINER HOLDING CAPACITY of the Port: ");
		maxContainercapacity = input.nextInt();
		
		System.out.print("Enter MAXIMUM SHIP DOCKING CAPACITY of the Port: ");
		maxShipDockingCapacity = input.nextInt();
		input.nextLine();
	}
	// Constructor overloading
	public Port(String name, String location, double x, double y, int maxContainercapacity, int maxShipDockingCapacity){
		this.name = name; 
		this.location = location;
		this.x = x;
		this.y = y;
		this.maxContainercapacity = maxContainercapacity;
		this.maxShipDockingCapacity = maxShipDockingCapacity;
	}
	
	public String getName() {
		return name;
	}
	
	public double getContainerCapacity() {
		return maxContainercapacity;
	}
	
	public int getShipCapacity() {
		return maxShipDockingCapacity;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void getdockedShips(){
		if(dockedShips.isEmpty()) {
			System.out.println("No ships are present in the port currently.");
		}else {
			int count = 0;
			for(Ship ship:dockedShips) {
				count++;
				System.out.print(count + ". ");ship.getDetails();			
			}
		}
	}
	
	public void getOrders() {
		for(CustomerOrder order:orders){
			order.getDetails();			
		}
	}
	
	@Override
	public void getDetails() {
		System.out.println("Name: "+name+ " Port");
		System.out.println("Location: "+location);
		System.out.println("Maximum Container loading Capacity: "+maxContainercapacity +" containers");
		System.out.println("Maximum Ship Docking Capacity: "+maxShipDockingCapacity +" Ships");
	}
	
	public void dockShip(Ship ship) {
		dockedShips.add(ship);
		ship.setCurrentPort(this);
		System.out.println("The "+ ship.getName() +" is sucessfully docked to "+name);
	}
	
	public Ship chooseShipToUndock() {
		int count = 0;
		for(Ship ship:dockedShips) {
			System.out.print(++count+". ");ship.getDetails();
		}
		int shipChoice = input.nextInt();
		return dockedShips.get(shipChoice - 1);
	}
	
	public void unDockShip(Ship ship) {
		historyOfDockedShips.add(ship);
		ship.setCurrentPort(null);
		dockedShips.remove(ship);
	}
	
	public void addContainers(int noOfContainers) {
		double weight;
		totalCharges = 0;
		for(int i = 0; i < noOfContainers; i++) {
			System.out.println("Enter weight of the container: ");
			weight = input.nextDouble();
			String type;
			if(weight < 300) {
				type = "Basic Type Container";
				orderContainersInPort.add(new BasicContainer(weight,type));
				totalCharges += orderContainersInPort.get(i).calculateCharges(1);
			}	
			else{
				System.out.println("Choose any of the following:\n"
						+ "1. If container is used to store Liquid content\n"
						+ "2. If container is used to store Refrigirated content\n"
						+ "Press 0 quit if container is not storing any of the above");
				int t = input.nextInt();
				if(t == 1) {
					type = "Liquid Type Container";
					orderContainersInPort.add(new LiquidContainer(weight,type));
					totalCharges += orderContainersInPort.get(i).calculateCharges(1);
				}
				else if(t == 2) {
					type = "Refrigirated Type Container";
					orderContainersInPort.add(new RefrigiratedContainer(weight,type));
					totalCharges += orderContainersInPort.get(i).calculateCharges(1);
				}
				else {
					type = "Heavy Type Container";
					orderContainersInPort.add(new HeavyContainer(weight,type));
					totalCharges += orderContainersInPort.get(i).calculateCharges(1);
				}
			}
		}
	}
	
	public void getAllContainers() {
		if(orderContainersInPort.isEmpty()) {
			System.out.println("No containers in this port");
		}
		else {
			for(int i = 0; i < orderContainersInPort.size(); i++) {
				System.out.println("-------------------------------------------------------------------------------------------");
				System.out.print(i+1 +". "); orderContainersInPort.get(i).getDetails();
				System.out.println("-------------------------------------------------------------------------------------------");
			}			
		}
	}
	
	public double getTotalCharges() {
		return totalCharges;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void loadContainersToShip() {
		int count = 1;
		System.out.println("Choose ship to load the containers: ");
		for(Ship ship:dockedShips) {
			System.out.print(count++ + ". ");ship.getDetails();
		}
		int shipChoice = input.nextInt();
		while(!orderContainersInPort.isEmpty()) {
			Ship shipToLoad = dockedShips.get(shipChoice - 1);
			Container container; 
			double weight = 0; 
			for(int i = 0; i < orderContainersInPort.size(); i++) {
				container = orderContainersInPort.get(i);
				weight = container.getWeight();
				double loadedCapacity = 0;
				if(loadedCapacity <= shipToLoad.getMaxLoadCapacity()) {
					shipToLoad.addContainersToShip(container);
					this.orderContainersInPort.remove(container);
					System.out.println("----------------------------------------------------------------------------------");
					System.out.println("Added container: ");container.getDetails();
					System.out.println("----------------------------------------------------------------------------------");
					loadedCapacity+=weight;
					if(orderContainersInPort.isEmpty())break;
				}
				else {
					System.out.println("The ship's maximum capacity has been reached.");
					this.unDockShip(shipToLoad);
					dockedShips.remove(shipToLoad);
					System.out.println("Choose another ship to load Containers.");
					break;
				}
			}
		}
	}
	
	public void loadContainersToPort(){
		System.out.println("Choose ship to unload to port: ");
		int count = 1;
		for(Ship ship:dockedShips) {
			System.out.println(count++ +". ");ship.getDetails();;		
		}
		int shipChoice =  input.nextInt();
		Ship shipToUnload = dockedShips.get(shipChoice - 1);
		System.out.println("Number of containers: "+shipToUnload.getContainersInShip().size());
		for(Container container: shipToUnload.getContainersInShip()) {
			orderContainersInPort.add(container);
			System.out.println("The "+container.getContainerID()+ " loaded to "+ name);
//			shipToUnload.removeContainersFromShip(container);
		}
	}
	
}
