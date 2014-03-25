import java.util.*;

public class ElevatorAlgorithm {
	
	public ElevatorAlgorithm() {
	}
	
	public Queue<Integer> sortRequests(Queue<Integer> myQueue, int currentFloor, boolean goUp) {
		ArrayList<Integer> higherRequests = new ArrayList<Integer>();
		ArrayList<Integer> lowerRequests = new ArrayList<Integer>();
		Queue<Integer> returnQueue = new LinkedList<Integer>();
		Set<Integer> ridDuplicates = new HashSet<Integer>();
		int queueSize = myQueue.size();
		
		for (int i=0; i<queueSize; i++) {
			int polledFloor = myQueue.poll();
			if (polledFloor>currentFloor && !ridDuplicates.contains(polledFloor)) {
				higherRequests.add(polledFloor);
			}
			else if(polledFloor<currentFloor && !ridDuplicates.contains(polledFloor)) {
				lowerRequests.add(-polledFloor);
			}/*
			else if (!ridDuplicates.contains(polledFloor)){
				if (goUp) {
					higherRequests.add(polledFloor);
				}
				else {
					lowerRequests.add(-polledFloor);
				}
			}*/
			ridDuplicates.add(polledFloor);	
		}
		
		Collections.sort(higherRequests);
		Collections.sort(lowerRequests);
		
		if (goUp){
			for (int i=0; i<higherRequests.size(); i++) {
				returnQueue.add(higherRequests.get(i));
			}
			for (int i=0; i<lowerRequests.size(); i++) {
				returnQueue.add(-lowerRequests.get(i));
			}
		}
		
		else {
			for (int i=0; i<lowerRequests.size(); i++) {
				returnQueue.add(-lowerRequests.get(i));
			}
			for (int i=0; i<higherRequests.size(); i++) {
				returnQueue.add(higherRequests.get(i));
			}
		}
		
		return returnQueue;
	}
	
	public boolean checkGoUp(Queue<Integer> myQueue, int currentFloor, boolean goUp) {
		if (myQueue.peek()<currentFloor && goUp) {
			goUp = false;
		}
		if (myQueue.peek()>currentFloor && !goUp) {
			goUp = true;
		}
		return goUp;
	}

}
