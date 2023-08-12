package shipsAndPorts;

import java.util.ArrayList;
import java.util.Scanner;

public class Ship implements GetSet{
	private String name;
	private Port currentPort;
	private double maxLoadCapacity;
	private int totalContainerCapacity;
	private ArrayList<Port> previousPorts = new ArrayList<Port>();
	private double loadedCapacity; 
	private ArrayList<Container> containersInShip = new ArrayList<>();
	Scanner input = new Scanner(System.in); 
	
	public Ship() {
		System.out.print("Enter NAME of the ship: ");
		this.name = input.nextLine();
	
		
		System.out.print("Enter the MAXIMUM LOAD CAPACITY of the Ship: ");
		this.maxLoadCapacity = input.nextDouble();
		
		System.out.print("Enter the TOTAL CONTAINER CAPACITY of the Ship: ");
		this.totalContainerCapacity = input.nextInt();
	}
	
	public Ship(String name, double maxLoadCapacity, int totalContainerCapacty ) {
		this.name = name;
		this.maxLoadCapacity = maxLoadCapacity;
		this.totalContainerCapacity = totalContainerCapacty;
	}
	
	public String getName() {
		return name;
	}
	
	public void getCurrentPort() {
		currentPort.getDetails();
	}
	
	public ArrayList<Container> getContainersInShip() {
		return containersInShip;
	}
	
	public double getLoadedCapacity() {
		return loadedCapacity;
	}
	
	public void setLoadedCapacity(double loadedCapacity) {
		this.loadedCapacity = loadedCapacity;
	}
	
	public void setCurrentPort(Port currentPort) {
		this.currentPort = currentPort;
	}
	
	public void addContainersToShip(Container container) {
		containersInShip.add(container);
	}
	
	public void removeContainersFromShip(Container container) {
		containersInShip.remove(container);
	}
	
	
	public void setSail(Port sPort, Port ePort) {
		currentPort.unDockShip(this);
		ePort.dockShip(this);
		System.out.println(this.name +" sailed and is docked to: " + ePort.getName());
	}
	
	public double getMaxLoadCapacity(){
		return maxLoadCapacity;
	}
	
	
	public void getDetails() {
		System.out.println("Name: "+name);
		System.out.println("Maximum  Load Capacity: "+maxLoadCapacity + " Tonnes.");
		System.out.println("Maximum Container Capacity: "+totalContainerCapacity+ " containers");
	}
	
	
	
}
	
	