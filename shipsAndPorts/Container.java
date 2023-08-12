package shipsAndPorts;

import java.util.Random;
import java.util.Scanner;
public abstract class Container implements GetSet{
	static Scanner input =  new Scanner(System.in);

	protected String containerId;
	protected double weight;
	protected double charges;
	protected String type;
	private int generateRandomID() {
		Random random = new Random();
		int id = random.nextInt(10000,99999);
		String type;
		return id;
	}
	public Container(double weight, String type) {
		int randomID = generateRandomID();
		this.containerId = String.valueOf(randomID);
		System.out.println("The ID generated for the container: "+ containerId);
		this.weight = weight;
		this.type = type;
	}
	
	public void getDetails() {
		System.out.println("Container ID: "+containerId);
		System.out.println("Weight: "+weight);
		System.out.println("Type: "+type);
		System.out.println("Charge: "+calculateCharges(1));
	}
	
	public String getContainerID() {
		return containerId;
	}
	
	public double getWeight() {
		return weight;
	}
	
	
	public abstract double calculateCharges(double charges);
	
}
