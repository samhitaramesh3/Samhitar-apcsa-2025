package robot;

import kareltherobot.*;

public class Roomba implements Directions {

	// Main method to make this self-contained
	public static void main(String[] args) {
		// LEAVE THIS ALONE!!!!!!
		String worldName = "robot/finalTestWorld2024.wld";

		Roomba cleaner = new Roomba();
		int totalBeepers = cleaner.cleanRoom(worldName, 7, 6);
		System.out.println("Roomba cleaned up a total of " + totalBeepers + " beepers.");
	}
    
	// declared here so it is visible in all the methods!
	 private Robot Rom = new Robot(26,101,South,50);

	// You will need to add many variables!!


	public int cleanRoom(String worldName, int startX, int startY) {

		// A new Robot should be constructed and assigned to the global (instance) variable named roomba that is declared above.
        // Make sure it starts at startX and startY location.

		World.readWorld(worldName);
		World.setVisible(true);
		World.setDelay(0);
		// Defined variables
		double area = 1;
		int totalBeepers = 0;
		double numberOfPiles = 0;
		int largestPile = 0;
		int largestPileX = 0;
		int largestPileY = 0;
		int avgpileSize =0;
		

		// Robot Direction
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
		// Robot Movements
		while(true){
			while(Rom.frontIsClear()){
				boolean pileFound = false;
				int pileSize = 0;
				while(Rom.nextToABeeper()){
					Rom.pickBeeper();totalBeepers++;
					pileSize++;
					pileFound = true;
				}
				// Figures out what largest pile is
				if (pileFound) numberOfPiles++;
				if (pileSize > largestPile){
					largestPile = pileSize;
					largestPileX = Rom.street();
					largestPileY = Rom.avenue();
				}
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
			int pileSize = 0;
			boolean pileFound = false;
			while(Rom.nextToABeeper()){
				Rom.pickBeeper();
				totalBeepers++;
				pileFound = true;
				pileSize++;
			}
			// Find largest pile coordinate
			if (pileSize > largestPile){
				largestPile = pileSize;
				largestPileX = Rom.street();
				largestPileY = Rom.avenue();
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
		System.out.println("LARGEST PILE SIZE: " + largestPile);
		System.out.println("LARGEST PILE LOCATION: " + largestPileX + "," + largestPileY);
		System.out.println("Percent dirty:" + (numberOfPiles/area));
		System.out.println("Average Pile Size:" + (totalBeepers/numberOfPiles));
		return totalBeepers;
	}
}
