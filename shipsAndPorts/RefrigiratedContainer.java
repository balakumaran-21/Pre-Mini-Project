package shipsAndPorts;

public class RefrigiratedContainer extends Container{

	public RefrigiratedContainer(double weight, String type) {
		super(weight, type);
	}


	@Override
	public double calculateCharges(double charges) {
		// TODO Auto-generated method stub
		return 5.00*charges;
	}
}
