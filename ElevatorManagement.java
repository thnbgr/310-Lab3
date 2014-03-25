import java.util.*;

public class ElevatorManagement {
	
	public ElevatorManagement() {}

	public Elevator findElevator(ArrayList<Elevator> myElevators, int fromFloor, int numFloors, boolean callUp){
		ArrayList<Elevator> availableElevators = new ArrayList<Elevator>();
		
		int minGoingUpDistance = -numFloors;
		int minGoingDownDistance = numFloors;
		int indexGoingUpDistance = 0;
		int indexGoingDownDistance = 0;
		
		int minNumRequests = 0;
		int indexNumRequests = 0;
		
		// Choose elevators that are not full
		for (int i = 0; i < myElevators.size(); i++) {
			Elevator elevator = myElevators.get(i);
			if (elevator.myNumRiders < elevator.maxOccupancyThreshold) {
				availableElevators.add(elevator);
			}
		}
		
		// If all are full, just choose from all elevators
		if (availableElevators.size()==0) {
			availableElevators = myElevators;
		}
		
		// Compute all distances between requested floor and elevators
		// Compute requests size for all elevators
		int numElevators = availableElevators.size();
		int[] elevatorDistances = new int[numElevators];
		int[] elevatorNumRequests = new int[numElevators];
		for (int i=0; i<availableElevators.size(); i++) {
			elevatorDistances[i] = availableElevators.get(i).currentFloor-fromFloor;
			elevatorNumRequests[i] = availableElevators.get(i).requests.size();
		}
		minNumRequests = availableElevators.get(0).requests.size();
		
		// Rider wants to go up
		if (callUp) {
			for (int i = 0; i < numElevators; i++) {
				Elevator currentElevator = availableElevators.get(i);
				int currentDistance = elevatorDistances[i];
				int currentRequestSize = elevatorNumRequests[i];
				// Update shortest distance only if the closest elevator below rider and is going up
				if(currentElevator.goUp && currentDistance<=0) {
					if (currentDistance>minGoingUpDistance) {
						minGoingUpDistance = currentDistance;
						indexGoingUpDistance = i;
					}
				}
				// Update smallest request size
				if (currentRequestSize<minNumRequests) {
					minNumRequests = currentRequestSize;
					indexNumRequests = i;
				}
			}
			// If none are available, return the elevator with the least requests
			if (minGoingUpDistance==numFloors) {
				return availableElevators.get(indexNumRequests);
			}
			return availableElevators.get(indexGoingUpDistance);
		}
		
		// Rider wants to go down
		else {
			for (int i = 0; i < numElevators; i++) {
				Elevator currentElevator = availableElevators.get(i);
				int currentDistance = elevatorDistances[i];
				int currentRequestSize = elevatorNumRequests[i];
				// Update shortest distance only if the closest elevator above rider and is going down
				if(!currentElevator.goUp && currentDistance>=0) {
					if (currentDistance<minGoingDownDistance) {
						minGoingDownDistance = currentDistance;
						indexGoingDownDistance = i;
					}
				}
				// Update smallest request size
				if (currentRequestSize<minNumRequests) {
					minNumRequests = currentRequestSize;
					indexNumRequests = i;
				}
			}
			// If none are available, return the elevator with the least requests
			if (minGoingDownDistance==numFloors) {
				return availableElevators.get(indexNumRequests);
			}
			return availableElevators.get(indexGoingDownDistance);
		}
	}
}
