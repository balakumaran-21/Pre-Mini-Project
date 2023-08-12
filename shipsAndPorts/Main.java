package shipsAndPorts;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

interface GetSet{
	public void getDetails();
}

public class Main {
	static Scanner input = new Scanner(System.in);
	static ArrayList<Port> ports = new ArrayList<>();
	static ArrayList<Ship> ships = new ArrayList<>();
	static Queue<CustomerOrder> orders = new LinkedList<>();
	static ArrayList<CustomerOrder> orderHistory = new ArrayList<>();
	static boolean orderCompleted = false;
	
	static int mainMenu() {
		System.out.println("============================================================================================\n"
				+ "Choose your Designation:\n"
				+ "1. Port Officer\n"
				+ "2. Customer Service\n"
				+ "========================================Press 0 to exit===============================================");
		System.out.print("Your choice: ");
		int option = input.nextInt();		
		return option;
	}
	
	static int backOptions() {
		System.out.println("===============================================================================================================\n"
						+ "Choose any option from below:\n"
						+ "1. Go Back\n"
						+ "2. mainMenu\n"
						+ "==============================================================================================================");
		System.out.print("Your choice: ");
		int option = input.nextInt();
		return option;
	}
	
	static int portOptions() {
		System.out.println("==============****Welcome Port Captain****==================");
		System.out.println("============================================================");
		System.out.println("Choose any option from below:\n"
				+ "1.  Register new Port\n"
				+ "2.  Register new Ship\n"
				+ "3.  Display details of all registered ships\n"
				+ "4.  View Customer orders\n"
				+ "5.  Dock new Ship to the Port\n"
				+ "6.  Check for ships docked in the Port\n"
				+ "7.  Check for available containers in the Port\n"
				+ "8.  Load Containers to the Ship from port\n"
				+ "9.  set ship to sail from port\n"
				+ "10. Unload Containers to the port from Ship");
		System.out.println("============================================================");
		System.out.print("Your choice: ");
		int portOfficerOption = input.nextInt();
		return portOfficerOption;
	}
	
	
	static void registerPort() {
		System.out.print("How many Ports would you like to register: ");
		int noOfPorts = input.nextInt();
		int count = 0;
		for(int i = 0; i < noOfPorts; i++) {
			System.out.println("==================**Enter details of the Port**==========================");
			ports.add(new Port());
			System.out.println("The " + ports.get(i).getName() + " port has been registered sucessfully");
			count++;
		}
		System.out.println("No of Ports registered: "+ count);
		count = 0;
	}
	
	static void registerShip() {
		System.out.print("How many Ships would you like to register: ");
		int noOfShips = input.nextInt();
		int count = 0;
		for(int i = 0; i < noOfShips; i++) {
			System.out.println("==================**Enter details of the ship**==========================");
			ships.add(new Ship());
			System.out.println("The " + ships.get(i).getName() + " ship has been registered sucessfully");
			count++;
		}
		System.out.println("No of Ships registered: "+ count);
	}
	
	static void displayShips() {
		int count = 0;
		for(Ship ship:ships) {
			count++;
			System.out.println("--------------------------------------------------------------------------------------------------------------");
			System.out.print(count + ". ");
			ship.getDetails();
			System.out.println("--------------------------------------------------------------------------------------------------------------");
		}
	}
	
	static void displayPorts() {
		int count = 0;
		for(Port port:ports) {
			count++;
			System.out.println("--------------------------------------------------------------------------------------------------------------");
			System.out.print(count + ". ");
			port.getDetails();
			System.out.println("--------------------------------------------------------------------------------------------------------------");
		}
	}
	public static void main(String[] args) {
		ports.add(new Port("Chennai","Chennai",20,30,25,20));
		ports.add(new Port("Banglore","Banglore",60,50,30,30));
		ports.add(new Port("Mumbai","Mumbai",40,20,30,30));
		ports.add(new Port("Vizagh","Vizagh",50,80,50,30));
		ships.add(new Ship("Titanic",30,15));
		ships.add(new Ship("Boeing",40,18));
		
		while(true) {
			System.out.println("====================================*****Welcome*****==============================================================");
			int option = mainMenu();
			if(option == 0) {
				System.out.println("Thank you Have a great day!");
				break;
			}
			while(!(option == 1 || option == 2)) {
				System.out.println("=================================******You Choose a wrong option*****=============================================");
				System.out.println("Press 1 to Continue\nPress any other key to Quit");
				int decision = input.nextInt();
				if(decision == 1) {
					System.out.println("Choose any option from below options:");
					option = mainMenu();
					if(option == 1 || option == 2 || option == 3)break;
					else continue;
				}
				else {
					System.out.println("==================================***********************====================================");
					System.out.println("								Thank you have a great day");
					System.out.println("==================================***********************====================================");
					break;
				}
			}
			
			// Port Officer Option
			while(option == 1) {
				if(orderCompleted == true) {
					orderHistory.add(orders.remove());
				}
				int portOfficeOption = portOptions();
				if(portOfficeOption == 1) {
					// Register a new port
					registerPort();
				}
				else if(portOfficeOption == 2) {
					//Register new Ship
					registerShip();
				}
				else if(portOfficeOption == 3) {
					// Checks for registered ships
					displayShips();
				}
				//View order details
				else if(portOfficeOption == 4) {
					if(orders.isEmpty()) {
						System.out.println("No orders currently");
					}else {
						orders.peek().getDetails();						
					}
				}
				// dock a ship which is already registered
				else if(portOfficeOption == 5){
					boolean found = false;
					System.out.println("Choose a ship to dock:");
					displayShips();
					int shipChoice = input.nextInt();
					Ship shipToBeDocked = ships.get(shipChoice - 1);
					if(ships.contains(shipToBeDocked)) {
						found = true;
					}
					if(found) {
						System.out.println("Choose port to dock the ship:");
						displayPorts();
						int portChoice = input.nextInt();
						Port portDockingTheShip = ports.get(portChoice - 1);
						portDockingTheShip.dockShip(shipToBeDocked);
						if(ships.contains(shipToBeDocked)) {
							ships.remove(shipToBeDocked);
						}
					}
					else {
						System.out.println("The provided choice was not registered");
						System.out.println("Register the ship and check for availabily of docking");
					}
				}
				else if(portOfficeOption == 6) {
					System.out.println("Choose port to display all the ships docked:");
					displayPorts();
					int portChoice = input.nextInt();
					Port portDockedShip = ports.get(portChoice - 1);
					portDockedShip.getdockedShips();
				}
				//Check for available containers in the Port
				else if(portOfficeOption == 7) {
					System.out.println("Choose port to view containers: ");
					displayPorts();
					int portOption = input.nextInt();
					Port portToView = ports.get(portOption - 1);
					portToView.getAllContainers();
				}
				// Load Containers to the Ship from port
				else if(portOfficeOption == 8) {
					System.out.println("Choose port to load containers to ship: ");
					displayPorts();
					int portChoice = input.nextInt();
					Port portToLoadToShip = ports.get(portChoice-1);
					portToLoadToShip.loadContainersToShip();
					orders.peek().setStatus("Containers Loaded ready for shipment");
				}
				//set ship to sail from port
				else if(portOfficeOption == 9) {
					displayPorts();
					int portOption = input.nextInt();
					Port portToUndock = ports.get(portOption - 1);
					Ship shipToSetSail = portToUndock.chooseShipToUndock();
					Port destPort = orders.peek().getEPort();
					Port sourcePort = orders.peek().getSPort();
					shipToSetSail.setSail(sourcePort,destPort);
					orders.peek().setStatus("Shipment reached destination port.");
				}
				// Unload to port from ship
				else if(portOfficeOption == 10) {
					System.out.println("Choose port: ");
					displayPorts();
					int portChoice = input.nextInt();
					Port destPort = ports.get(portChoice-1);
					destPort.loadContainersToPort();
					orders.peek().setStatus("Ready to import, Come anytime to collect.");
					orderCompleted = true;
				}
				int chooseOption = backOptions();
				if(chooseOption == 1) {
					continue;
				}
				else if(chooseOption == 2) {
					break;
				}
			}
			//Customer Order
			while(option == 2) {
				System.out.println("Choose Below options: \n"
						+ "1. Place Order\n"
						+ "2. Check status");
				int custOption = input.nextInt();
				if(custOption == 1) {					
					System.out.println("Create order:");
					orders.add(new CustomerOrder());
					System.out.println("--------------------------------------------------------------------------------------------");
					if(orders.peek().getPaidStatus() == false) {
						orders.remove();
						System.out.println("Order Cancelled");
					}
					else{
						orders.peek().getDetails();						
					}
					System.out.println("--------------------------------------------------------------------------------------------");
				}
				else if(custOption == 2){
					if(orders.isEmpty()) {
						System.out.println("Place an order to view details");
					}else {
						System.out.println(orders.peek().getStatus());						
					}
				}
				int chooseOption = backOptions();
				if(chooseOption == 1) {
					continue;
				}
				else if(chooseOption == 2) {
					break;
				}
			}
			
			
		}
								
	}
}

