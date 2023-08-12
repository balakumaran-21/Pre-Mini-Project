package shipsAndPorts;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerOrder implements GetSet{
	private String name;
	private String startLocation;
	private String endLocation;
	private String status = "No order places as of now";
	private int noOfContainers;
	private int startPortOption;
	private Port sPort;
	private Port ePort;
	private boolean paid = false;
	static Scanner input = new Scanner(System.in);
	ArrayList<Port> portsReg = Main.ports;
	public CustomerOrder(){
		System.out.print("Enter your name: ");
		name = input.nextLine();
		
		System.out.println("Choose any of the below location to load containers: ");
		int count = 0;
		for(Port port:Main.ports) {
			count++;
			System.out.println("--------------------------------------------------------------------------------------------------------------");
			System.out.print(count + ". ");
			System.out.println(port.getLocation());
			System.out.println("--------------------------------------------------------------------------------------------------------------");
		}
		System.out.print("Your choice: ");
		int portChoice1 = input.nextInt();
		startPortOption = portChoice1 - 1;
		Port startPort = portsReg.get(startPortOption);
		startLocation = startPort.getLocation();
		sPort = startPort;
		portsReg.remove(startPort);
		
		
		System.out.println("Choose any of the below location to export containers: ");
		count = 0;
		for(Port port:portsReg) {
				count++;
				System.out.println("--------------------------------------------------------------------------------------------------------------");
				System.out.print(count + ". ");
				System.out.println(port.getLocation());
				System.out.println("--------------------------------------------------------------------------------------------------------------");				
		}
		System.out.print("Your choice: ");
		int portChoice2 = input.nextInt();
		Port endPort = Main.ports.get(portChoice2-1);
		endLocation = endPort.getLocation();
		System.out.print("Enter number of containers to load: ");
		ePort = endPort;
		portsReg.add(startPort);
		noOfContainers = input.nextInt();
		boolean limit = false;
		if(noOfContainers <= startPort.getShipCapacity() && noOfContainers <= endPort.getShipCapacity()) {
			limit = true;
			startPort.addContainers(noOfContainers);			
		}
		else {
			System.out.println("The port cannot contain more capacity.\n"
					+ "Please choose different ports"
					+ "Register a new order.");
			limit = false;
		}
		
		double charges = calculateDistance(sPort, ePort)*portsReg.get(portsReg.size()-1).getTotalCharges();
		while(limit) {			
			getDetails();
			System.out.println("Confirm your order:\n"
					+ "y/Y - yes\n"
					+ "n/N - No\n"
					+ "Press y/Y to proceed to payment\n"
					+ "Press n/N to proceed to quit");
			System.out.print("Your choice: ");
			String payStatus = input.next();
			if(payStatus.charAt(0) == 'y' || payStatus.charAt(0) == 'Y') {
				System.out.println("Total payable amount: "+charges);
				System.out.print("Enter total amount to pay: ");
				double payment = input.nextDouble();
				input.nextLine();
				if(payment ==  charges) {
					paid = true;
				}
				else {
					System.out.println("The amount entered is not equal to the charges. Try registering a new order");
					continue;
				}
				if(paid == true) {
					status = "Order placed";
					System.out.println("Payment Sucessfull");
					break;
				}
			}else {	
				System.out.println("Sorry, for the inconvenience\n"
						+ "Try registering a new order");
				break;
			}
		}
		charges = 0;
	}
	
	
	public String getName() {
		return name;
	}

	public Port getEPort() {
		return ePort;
	}
	
	public Port getSPort() {
		return sPort;
	}
	
	public String getStartLocation() {
		return startLocation;
	}

	public String getEndLocation() {
		return endLocation;
	}
	
	public double calculateDistance(Port sourcePort, Port destPort) {
		return Math.floor(Math.sqrt(Math.pow(sourcePort.getX() - destPort.getX(), 2) + Math.pow(sourcePort.getY() - destPort.getY(), 2)));
	}

	public int getNoOfContainers() {
		return noOfContainers;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
			return status;			
	}
	
	public boolean getPaidStatus() {
		return paid;
	}
	
	@Override
	public void getDetails() {
		System.out.println("Name of customer: "+ name);
		System.out.println("Starting port location: "+ startLocation);
		System.out.println("Destination port location: "+ endLocation);
		System.out.println("Number Of Containers loaded: "+ noOfContainers);
		System.out.println("Distance between ports: "+ calculateDistance(sPort, ePort));
		System.out.println("Details of containers loaded: ");
		portsReg.get(portsReg.size()-1).getAllContainers();
		System.out.print("Total charges"); 
		System.out.print((paid)?" paid":" payable");
		double charges = calculateDistance(sPort, ePort)*portsReg.get(portsReg.size()-1).getTotalCharges();
		System.out.println(": $"+ charges);
	}
	
}
