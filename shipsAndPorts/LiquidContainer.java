package shipsAndPorts;

public class LiquidContainer extends Container{

	

	public LiquidContainer(double weight, String type) {
		super(weight, type);
	}


	@Override
	public double calculateCharges(double charges) {
		// TODO Auto-generated method stub
		return 4.00*charges;
	}
}
