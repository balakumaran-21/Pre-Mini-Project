package shipsAndPorts;

public class BasicContainer extends Container{

	public BasicContainer(double weight, String type) {
		super(weight, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calculateCharges(double charges) {
		// TODO Auto-generated method stub
		return 2.50*charges;
	}
	
}
