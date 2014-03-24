import java.util.*;

public class ElevatorManagement {
	
	public ElevatorManagement(){
		
	}

	public Elevator findElevator(ArrayList<Elevator> myElevators, int fromFloor, boolean callUp){
		
		ArrayList<Integer> diff = new ArrayList<Integer>();

		
		int numElevators = myElevators.size();
		int min1 = fromFloor;
		int min2 = fromFloor;
		int index = 0;
		int index2 = 0;
		boolean check = false;
		
		for(int i = 0; i < numElevators; i++){
			diff.add(fromFloor - myElevators.get(i).currentFloor);
		}
		
		// the caller wants to go up
		if(callUp){
			for (int i = 0; i < numElevators; i++){
				Elevator element = myElevators.get(i);
				int dif = fromFloor - element.currentFloor;
				if(element.goUp){
					if(dif > 0){
						check = true;
						if(dif < min1){
							min1 = dif;
							index = i;
						}
					}
				}	
				else{
					if(dif < min2){
						min2 = dif;
						index2 = i;
					}

				}
			}	
		}
		// the caller wants to go down
		else{
			for (int i = 0; i < numElevators; i++){
				Elevator element = myElevators.get(i);
				int dif = fromFloor - element.currentFloor;
				if(!element.goUp){
					if(dif < 0){
						check = true;
						if(dif > min1){
							min1 = dif;
							index = i;
						}
					}
				}	
				else{
					if(dif < min2){
						min2 = dif;
						index2 = i;
					}
				}
			}	
		}
		
		if(check){
			return myElevators.get(index);
		}
		else{
			return myElevators.get(index2);
		}

	}
	

	
}
