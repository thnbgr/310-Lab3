import java.util.LinkedList;
import java.util.Queue;


public class Elevator extends AbstractElevator {

	protected int currentFloor;
	protected Queue<Integer> requests = new LinkedList<Integer>();
	protected EventBarrier[] myOnBarriers;	
	protected EventBarrier[] myOffBarriers;
	protected boolean doorsClosed;
	protected EventBarrier myEventBarrier;
	protected ElevatorAlgorithm elevatorAlgorithm;
	protected boolean goingUp;
	protected int myNumRiders;
	
	public Elevator(int numFloors, int elevatorId, int maxOccupancyThreshold) {
		super(numFloors, elevatorId, maxOccupancyThreshold);
		// TODO Auto-generated constructor stub
		currentFloor = 1;
	}
	
	public Elevator(int numFloors, int elevatorId, int maxOccupancyThreshold, EventBarrier[] floorOnBarriers, EventBarrier[] floorOffBarriers) {
		super(numFloors, elevatorId, maxOccupancyThreshold);
		// TODO Auto-generated constructor stub
		myNumRiders = 0;
		currentFloor = 1;
		myOnBarriers = floorOnBarriers;
		myOffBarriers = floorOffBarriers;
		myEventBarrier = new EventBarrier();
		doorsClosed = true;
		elevatorAlgorithm = new ElevatorAlgorithm();
		goingUp = true;
	}

	public void goToNextFloor() {
		ClosedDoors();
		long start = System.nanoTime();
		LogWriter.log("E"+elevatorId+" on F"+currentFloor+" closes", start);
		int nextFloor = requests.poll();
		VisitFloor(nextFloor);
		
		OpenDoors();
		start = System.nanoTime();
		LogWriter.log("E"+elevatorId+" on F"+nextFloor+" opens", start);
	}
	
	@Override
	public void OpenDoors() {
		doorsClosed = false;
	}

	@Override
	public void ClosedDoors() {
		requests = elevatorAlgorithm.sortRequests(requests, currentFloor, goingUp);
		goingUp = elevatorAlgorithm.checkGoUp(requests, currentFloor, goingUp);
		doorsClosed = true;
	}

	@Override
	public void VisitFloor(int floor) {
		// Visits certain floor
		if (currentFloor < floor) {
			long start = System.nanoTime();
			LogWriter.log("E"+elevatorId+" moves up to floor "+floor, start);
		} else {
			long start = System.nanoTime();
			LogWriter.log("E"+elevatorId+" moves down to floor "+floor, start);
		}
		currentFloor = floor;

	}

	@Override
	public boolean Enter() {
		
		if (myNumRiders >= maxOccupancyThreshold) {
			return false;
		}
		myNumRiders++;
		
		return !doorsClosed;
	}

	@Override
	public void Exit() {
		myNumRiders--;
	}

	@Override
	public synchronized void RequestFloor(int floor) { // called by building
		
		requests.add(floor);
		//requests = elevatorAlgorithm.sortRequests(requests, currentFloor, goingUp);
		
	}

}
