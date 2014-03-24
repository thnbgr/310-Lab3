import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Building extends AbstractBuilding {
	protected ArrayList<Elevator> myElevators = new ArrayList<Elevator>();
	protected EventBarrier[][] getOnBarriers; // Elevator, Floor
	protected EventBarrier[][] getOffBarriers; // Elevator, Floor
	protected int myNumFloors, myNumElevators;

	public Building(int numFloors, int numElevators) {
		super(numFloors, numElevators);
	}
	
	public Building(int numFloors, int numElevators, int numCapacity) {
		super(numFloors, numElevators);
		// TODO Auto-generated constructor stub

		myNumFloors = numFloors;
		myNumElevators = numElevators;
		getOnBarriers = new EventBarrier[numElevators + 1][numFloors + 1];

		for (int i = 1; i <= numElevators; i++) {

			for (int j = 1; j <= numFloors; j++) {
				getOnBarriers[i][j] = new EventBarrier();
				getOffBarriers[i][j] = new EventBarrier();
			}

			Elevator newElevator = new Elevator(myNumFloors, i, numCapacity,
					getOnBarriers[i], getOffBarriers[i]);
			myElevators.add(newElevator);
		}
	}
	
	private Elevator getElevator(int fromFloor) {
		return myElevators.get(0); // change this
	}

	@Override
	public Elevator CallUp(int fromFloor) {
		Elevator currentElevator = getElevator(fromFloor);
		currentElevator.RequestFloor(fromFloor);
		return currentElevator;
	}

	@Override
	public Elevator CallDown(int fromFloor) {
		Elevator currentElevator = getElevator(fromFloor);
		currentElevator.RequestFloor(fromFloor);
		return currentElevator;
	}

}
