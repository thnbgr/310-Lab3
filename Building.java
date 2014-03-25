import java.util.ArrayList;

public class Building extends AbstractBuilding {
	protected ArrayList<Elevator> myElevators = new ArrayList<Elevator>();
	protected ArrayList<ElevatorThread> myElevatorThreads = new ArrayList<ElevatorThread>();
	protected EventBarrier[][] getOnBarriers; // Elevator, Floor
	protected EventBarrier[][] getOffBarriers; // Elevator, Floor
	protected int myNumFloors, myNumElevators;
	protected ElevatorManagement myElevatorManagement;
	protected int myNumRidersDone;
	protected int myNumRiders;

	public Building(int numFloors, int numElevators) {
		super(numFloors, numElevators);
	}

	public Building(int numFloors, int numElevators, int numCapacity, int riders) {
		super(numFloors, numElevators);
		myNumRidersDone = 0;
		myNumRiders = riders;
		myNumFloors = numFloors;
		myNumElevators = numElevators;
		getOnBarriers = new EventBarrier[numElevators + 1][numFloors + 1];
		getOffBarriers = new EventBarrier[numElevators + 1][numFloors + 1];
		myElevatorManagement = new ElevatorManagement();

		for (int i = 1; i <= numElevators; i++) {

			for (int j = 1; j <= numFloors; j++) {
				getOnBarriers[i][j] = new EventBarrier();
				getOffBarriers[i][j] = new EventBarrier();
			}

			Elevator newElevator = new Elevator(myNumFloors, i, numCapacity,
					getOnBarriers[i], getOffBarriers[i]);
			ElevatorThread t = new ElevatorThread(newElevator);
			myElevatorThreads.add(t);
			t.start();
			myElevators.add(newElevator);
		}
	}

	public void riderDone() {
		myNumRidersDone++;
		if (myNumRidersDone >= myNumRiders) {
			for (ElevatorThread t : myElevatorThreads) {
				t.stop();
			}
		}
	}

	@Override
	public Elevator CallUp(int fromFloor) {
		Elevator currentElevator = myElevatorManagement.findElevator(
				myElevators, fromFloor, myNumFloors, true);
		currentElevator.RequestFloor(fromFloor);
		return currentElevator;
	}

	@Override
	public Elevator CallDown(int fromFloor) {
		Elevator currentElevator = myElevatorManagement.findElevator(
				myElevators, fromFloor, myNumFloors, false);
		currentElevator.RequestFloor(fromFloor);
		return currentElevator;
	}

}
