import java.util.*;

public class ElevatorAlgorithm {

	public ElevatorAlgorithm() {
	}

	public Queue<Integer> sortRequests(Queue<Integer> myQueue,
			int currentFloor, boolean goUp) {
		ArrayList<Integer> higherRequests = new ArrayList<Integer>();
		ArrayList<Integer> lowerRequests = new ArrayList<Integer>();
		Queue<Integer> returnQueue = new LinkedList<Integer>();
		Set<Integer> ridDuplicates = new HashSet<Integer>();
		int queueSize = myQueue.size();
		boolean isSame = false;

		for (int i = 0; i < queueSize; i++) {
			int polledFloor = myQueue.poll();
			if (!ridDuplicates.contains(polledFloor)) {
				if (polledFloor > currentFloor) {
					higherRequests.add(polledFloor);
				} else if (polledFloor < currentFloor) {
					lowerRequests.add(-polledFloor);
				} else {
					isSame = true;
					/*
					 * if (goUp) { lowerRequests.add(-polledFloor); } else {
					 * higherRequests.add(polledFloor); }
					 */

				}
				ridDuplicates.add(polledFloor);
			}
		}

		Collections.sort(higherRequests);
		Collections.sort(lowerRequests);

		if (goUp) {
			for (int i = 0; i < higherRequests.size(); i++) {
				returnQueue.add(higherRequests.get(i));
			}
			for (int i = 0; i < lowerRequests.size(); i++) {
				returnQueue.add(-lowerRequests.get(i));
			}
		}

		else {
			for (int i = 0; i < lowerRequests.size(); i++) {
				returnQueue.add(-lowerRequests.get(i));
			}
			for (int i = 0; i < higherRequests.size(); i++) {
				returnQueue.add(higherRequests.get(i));
			}
		}

		if (isSame) {
			returnQueue.add(currentFloor);
		}

		return returnQueue;
	}

	public boolean checkGoUp(Queue<Integer> myQueue, int currentFloor,
			boolean goUp) {
		if (myQueue.peek() < currentFloor && goUp) {
			goUp = false;
		}
		if (myQueue.peek() > currentFloor && !goUp) {
			goUp = true;
		}
		return goUp;
	}

}
