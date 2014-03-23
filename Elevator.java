import java.util.Queue;


public class Elevator extends AbstractElevator  {

	Queue<Integer> floorQueue;
	
	public Elevator(int numFloors, int elevatorId, int maxOccupancyThreshold) {
		super(numFloors, elevatorId, maxOccupancyThreshold);
		// TODO Auto-generated constructor stub
	}
	
	private void getNextFloor() {
		VisitFloor(floorQueue.poll());
	}

	@Override
	public void OpenDoors() {
		// called by elevator
		// threads exit, threads enter, stop movement (open gate)
	}

	@Override
	public void ClosedDoors() {
		// called by elevator
		// resume movement (close gate)
	}

	@Override
	public void VisitFloor(int floor) {
		// called by elevator
		
	}

	@Override
	public boolean Enter() {
		// called by rider
		return false;
	}

	@Override
	public void Exit() {
		// called by rider (complete)
	}

	@Override
	public void RequestFloor(int floor) {
		// called by rider (arrive)
	}

}
