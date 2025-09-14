package robot;

import kareltherobot.*;

public class Roomba implements Directions {

	// Main method to make this self-contained
	public static void main(String[] args) {
		// LEAVE THIS ALONE!!!!!!
		String worldName = "robot/basicRoom.wld";

		Roomba cleaner = new Roomba();
		int totalBeepers = cleaner.cleanRoom(worldName, 7, 6);
		System.out.println("Roomba cleaned up a total of " + totalBeepers + " beepers.");
	}
    
	// declared here so it is visible in all the methods!
	 private Robot Rom = new Robot(7,6,South,50);

	// You will need to add many variables!!


	public int cleanRoom(String worldName, int startX, int startY) {

		// A new Robot should be constructed and assigned to the global (instance) variable named roomba that is declared above.
        // Make sure it starts at startX and startY location.

		World.readWorld(worldName);
		World.setVisible(true);
		World.setDelay(1);

		int area = 1;
		int totalBeepers = 0;
		int numberOfPiles = 0;
		int largestPile = 0;

		while(!Rom.facingWest()){
			Rom.turnLeft();
		}

		while(Rom.frontIsClear()){
			Rom.move();
		}

		for(int x = 0; x < 3; x++){
			Rom.turnLeft();
		}

		while(Rom.frontIsClear()){
			Rom.move();
		}

		for(int x = 0; x < 3; x++){
			Rom.turnLeft();
		}

		while(true){
			while(Rom.frontIsClear()){
				boolean pileFound = false;
				while(Rom.nextToABeeper()){
					Rom.pickBeeper();totalBeepers++;
					pileFound = true;
				}
				if (pileFound) numberOfPiles++;
				Rom.move();
				area++;
			}
			if(Rom.facingEast()){
				for(int x = 0; x < 3; x++){
					Rom.turnLeft();
				}
			}
			else if(Rom.facingWest()){
				Rom.turnLeft();
			}
			boolean pileFound = false;
			while(Rom.nextToABeeper()){
				Rom.pickBeeper();
				totalBeepers++;
				pileFound = true;
			}
			if (pileFound) numberOfPiles++;
			if(Rom.frontIsClear()){ 
				Rom.move();
				area++;
			} else {
				break;
			}
			Rom.turnLeft();
			if(!Rom.frontIsClear()){
				Rom.turnLeft();
				Rom.turnLeft();
			}
		}

		/** This section will have all the logic that takes the Robot to every location
		 * and cleans up all piles of beepers. Think about ways you can break this
		 * large, complex task into smaller, easier to solve problems.
		 */

		// the line below causes a null pointer exception
		// what is that and why are we getting it?
		

		 // Need to move this somewhere else.
        // This method should return the total number of beepers cleaned up.
		System.out.println("AREA: " + area);
		System.out.println("NUMBER OF PILES: " + numberOfPiles);
		return totalBeepers;
	}
}
