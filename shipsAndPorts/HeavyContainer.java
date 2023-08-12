package shipsAndPorts;

public class HeavyContainer extends Container{

	public HeavyContainer(double weight, String type) {
		super(weight, type);
	}

	@Override
	public double calculateCharges(double charges) {
		// TODO Auto-generated method stub
		return 3.00*charges;
	}
	
}
