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
	}

	public void goToNextFloor() {
		ClosedDoors();
		VisitFloor(requests.poll());
		OpenDoors();
	}
	
	@Override
	public void OpenDoors() {
		// TODO Auto-generated method stub
		doorsClosed = false;
		
		// Notify on the specific barrier
	}

	@Override
	public void ClosedDoors() {
		// TODO Auto-generated method stub
		doorsClosed = true;
		// called by elevator
		// resume movement (close gate)
	}

	@Override
	public void VisitFloor(int floor) {
		// Visits certain floor
		currentFloor = floor;
		LogWriter.log("E"+elevatorId+" moves to floor "+floor);
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
		// TODO Auto-generated method stub
		
		requests.add(floor);
		
		//requests = FloorManager.process(requests, floor);
		
		// called by rider (arrive)
	}

}
