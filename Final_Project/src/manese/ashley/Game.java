package manese.ashley;
/*
 * Testing game on console
 */
import java.util.Random;

public class Game{  
	static int n;
	public static void main(String[] args) {
		n = 5;
		GlobalFields.grid = new Location[n][n];

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				GlobalFields.grid[i][j] = new Location(i,j);

			}
		}

		for(int i = 0; i < 3; i++) {
			placeItemOnGrid(new SmallCreature());
			placeItemOnGrid(new Trap());
			placeItemOnGrid(new PowerUp());
		}

		placeItemOnGrid(new MythicalCreature());

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				//System.out.print(GlobalFields.grid[i][j] + "\t\t |" );	
			}

			//System.out.println("\n");
		}

		GlobalFields.playerLocation.getPlayer().checkAdjacent(GlobalFields.grid);
		GlobalFields.playerLocation.getPlayer().interact();

	}

	public static Location retrieveLocation() {
		Random r = new Random();
		int x = r.nextInt(n);
		int y = r.nextInt(n);
		return GlobalFields.grid[x][y];

	}
	public static void placeItemOnGrid(Item item) {

		boolean isResolved = false;

		//		while(!isResolved) {
		Location l = retrieveLocation();
		if(item instanceof MythicalCreature) {
			l.setMythicalCreature((MythicalCreature)item);
			isResolved = true;
		}
		else if(item instanceof Trap) {
			Trap trap = (Trap)item;

			if(trap.validate(l)) {
				l.setTrap((Trap)item);
				isResolved = true;
			}	
		}
		else if(item instanceof SmallCreature) {
			SmallCreature creature = (SmallCreature)item;

			if(creature.validate(l)) {
				l.setSmallCreature((SmallCreature)item);
				isResolved = true;
			}

		}
		else if(item instanceof PowerUp) {
			PowerUp powerUp = (PowerUp)item;

			if(powerUp.validate(l)) {
				l.setPowerUp((PowerUp)item);
				isResolved = true;
			}
		}
		else if(item instanceof Player) {
			Player p = (Player)item;

			if(p.validate(l)){
				l.setPlayer((Player)item);
				GlobalFields.playerLocation = l;
				p.setPlayerLocation(GlobalFields.playerLocation);
				isResolved = true;

			}	
		}	
	}



}






