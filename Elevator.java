import java.util.LinkedList;
import java.util.Queue;


public class Elevator extends AbstractElevator {

	protected int currentFloor;
	protected Queue<Integer> requests = new LinkedList<Integer>();
	protected EventBarrier[] myOnBarriers;	
	protected EventBarrier[] myOffBarriers;
	protected boolean doorsClosed;
	protected EventBarrier myEventBarrier;
	
	public Elevator(int numFloors, int elevatorId, int maxOccupancyThreshold) {
		super(numFloors, elevatorId, maxOccupancyThreshold);
		// TODO Auto-generated constructor stub
		currentFloor = 1;
	}
	
	public Elevator(int numFloors, int elevatorId, int maxOccupancyThreshold, EventBarrier[] floorOnBarriers, EventBarrier[] floorOffBarriers) {
		super(numFloors, elevatorId, maxOccupancyThreshold);
		// TODO Auto-generated constructor stub
		currentFloor = 1;
		myOnBarriers = floorOnBarriers;
		myOffBarriers = floorOffBarriers;
		myEventBarrier = new EventBarrier();
		doorsClosed = true;
	}

	public void goToNextFloor() {
		if (doorsClosed = false) {
		ClosedDoors();
		long start = System.nanoTime();
		LogWriter.log("E"+elevatorId+" on F"+currentFloor+" closes", start);
		}
		int nextFloor = requests.poll();
		VisitFloor(nextFloor);
		OpenDoors();
		long start = System.nanoTime();

		LogWriter.log("E"+elevatorId+" on F"+nextFloor+" opens", start);
	}
	
	@Override
	public void OpenDoors() {
		doorsClosed = false;
	}

	@Override
	public void ClosedDoors() {
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
		// TODO Auto-generated method stub
		if(doorsClosed == false)
			return true;
		
		// called by rider
		return false;
	}

	@Override
	public void Exit() {
		// TODO Auto-generated method stub

		
		// called by rider (complete)
	}

	@Override
	public synchronized void RequestFloor(int floor) { // called by building
		requests.add(floor);
		//requests = FloorManager.process(requests, floor);
		
	}

}
