
public class Building extends AbstractBuilding {

	public Building(int numFloors, int numElevators) {
		super(numFloors, numElevators);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AbstractElevator CallUp(int fromFloor) {
		// call by rider
		return null;
	}

	@Override
	public AbstractElevator CallDown(int fromFloor) {
		// call by rider
		return null;
	}

}
