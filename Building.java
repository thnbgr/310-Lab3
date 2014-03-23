import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Building extends AbstractBuilding {
	protected ArrayList<Elevator> myElevators = new ArrayList<Elevator>();
	protected EventBarrier[][] getOnBarriers; // Floor, Elevator
	protected EventBarrier[][] getOffBarriers; // Floor, Elevator
	protected int myNumFloors, myNumElevators;
	
	public Building(int numFloors, int numElevators) {
		super(numFloors, numElevators);
		// TODO Auto-generated constructor stub
		
		myNumFloors = numFloors;
		myNumElevators = numElevators;
		getOnBarriers = new EventBarrier[numFloors+1][numElevators+1];
		
		for(int i = 1; i <= numElevators; i++)
		{
			Elevator newElevator = new Elevator(myNumFloors, i, 10000);
			myElevators.add(newElevator);
			
			for(int j = 1; j <= numFloors; j++)
			{
				getOnBarriers[i][j] = new EventBarrier();
				getOffBarriers[i][j] = new EventBarrier();
			}
		}
	}

	@Override
	public Elevator CallUp(int fromFloor) {
		// TODO Auto-generated method stub
		
		Elevator currentElevator = myElevators.get(0);
		currentElevator.RequestFloor(fromFloor);
		return currentElevator;
	}

	@Override
	public Elevator CallDown(int fromFloor) {
		// TODO Auto-generated method stub
		
		Elevator currentElevator = myElevators.get(0);
		currentElevator.RequestFloor(fromFloor);
		return myElevators.get(0);
	}
	


}
