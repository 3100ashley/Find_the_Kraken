package manese.ashley;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.shape.Rectangle;

public class MythicalCreature extends Item{
	private Location krakenLocation;
	private boolean isAwake;

	public MythicalCreature() {
		super.setName("");
		this.isAwake = false;
	}

	public boolean isAwake() {
		return isAwake;
	}

	public void setAwake(boolean isAwake) {
		this.isAwake = isAwake;
	}

	public Location getKrakenLocation() {
		return krakenLocation;
	}

	public void setKrakenLocation(Location krakenLocation) {
		this.krakenLocation = krakenLocation;
	}

	public void moveKraken() {
		Location krakenLocation = GlobalFields.krakenLocation;
		int currRow = krakenLocation.getPos()[0];
		int currColumn = krakenLocation.getPos()[1];
		Location currLoc = GlobalFields.grid[currRow][currColumn];
		MythicalCreature kraken  = currLoc.getMythicalCreature();

		Location newKrakenLocation = null;

		Location north = checkBoundary(currRow + 1, currColumn);
		ArrayList<Location> rndLoc = new ArrayList<>();
		rndLoc.add(north);
		rndLoc.add(checkBoundary(currRow - 1, currColumn));
		rndLoc.add(checkBoundary(currRow , currColumn - 1));
		rndLoc.add(checkBoundary(currRow , currColumn + 1));
		
		newKrakenLocation = getRandom(rndLoc);
		
		currLoc.setMythicalCreature(null);
		newKrakenLocation.setMythicalCreature(kraken);
		GlobalFields.krakenLocation = newKrakenLocation;
		kraken.setKrakenLocation(newKrakenLocation);
		
			
		if(kraken != null && newKrakenLocation.getPlayer() != null) {
			GlobalFields.lb.setText("AHH the kraken!! \n GAME OVER!");
			Player.disableButtonsForAttack();
			Player.disableButtonsForPlayer();
		}

	}
	
	public Location checkBoundary(int row, int column) {
		try {
			Location loc = GlobalFields.grid[row][column];
			return loc;
		}catch(Exception e) {
			return null;
		}	
	}
	
	public Location getRandom(ArrayList<Location> array) {
		Random r = new Random();
		
		for(int i =0; i < array.size(); i++) {
			if(array.get(i) == null) {
				array.remove(i);
			}
		}
		int rnd = r.nextInt(array.size());
		return  array.get(rnd);
	}
	
	public String toString() {
		return super.getName();

	}



}
