import java.util.LinkedList;
import java.util.Queue;


public class Elevator extends AbstractElevator {

	protected int currentFloor;
	protected Queue<Integer> requests = new LinkedList<Integer>();
	protected EventBarrier currentBarrier;
	protected boolean doorsClosed;
	
	public Elevator(int numFloors, int elevatorId, int maxOccupancyThreshold) {
		super(numFloors, elevatorId, maxOccupancyThreshold);
		// TODO Auto-generated constructor stub
		currentFloor = 1;
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
		// TODO Auto-generated method stub
		
		// Visits certain floor
		currentFloor = floor;
		
		// Takes care of notifying so that it can unload, then load riders
		
		// Check if queue is empty. If not, pop the next request and VisitFloor
		
		// If queue is empty, then wait until new requests come in
		// called by elevator
		
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
		if(doorsClosed == false)
			return;
		
		// called by rider (complete)
	}

	@Override
	public void RequestFloor(int floor) {
		// TODO Auto-generated method stub
		
		requests.add(floor);
		
		// called by rider (arrive)
	}

}
