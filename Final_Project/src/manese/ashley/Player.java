package manese.ashley;


public class Player extends Item{

	private  int magicWand;
	private Location playerLocation;
	private boolean isAlive;
	private String message = "";

	public Player() {
		super.setName("");
		this.magicWand = 3;
		this.isAlive = true;
	}

	public Location getPlayerLocation() {
		return playerLocation;
	}

	public void setPlayerLocation(Location playerLocation) {
		this.playerLocation = playerLocation;
	}

	public int getMagicWand() {
		return magicWand;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setMagicWand(int magicWand) {
		this.magicWand = magicWand;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public String toString() {
		return super.getName();
	}

	public void concatMessage(String message) {
		this.message += message + "\n";

	}
	
	public boolean validate(Location l) {
		MythicalCreature creature = l.getMythicalCreature(); 
		Trap trap = l.getTrap();
		SmallCreature smallCreature = l.getSmallCreature();
		PowerUp powerUp = l.getPowerUp();

		if(creature == null && trap == null  && smallCreature == null &&  powerUp == null) {
			return true;
		}
		return false;

	}

	public void checkAdjacent(Location[][] grid) {
		int xPos = this.playerLocation.getPos()[0];
		int yPos = this.playerLocation.getPos()[1];

		if(this.playerLocation.getPlayer() == null) {
			//System.out.println("invalid player location"); 
		}


		String north = getAdjacentMessage(grid, xPos, yPos - 1);
		String south = getAdjacentMessage(grid, xPos, yPos + 1);
		String east = getAdjacentMessage(grid, xPos + 1, yPos);
		String west = getAdjacentMessage(grid, xPos - 1, yPos);
		//System.out.println(north +  south  + east + west);
		concatMessage(north +  south  + east + west);

	}

	public void interact() {
		SmallCreature smallCreature = this.playerLocation.getSmallCreature();
		PowerUp powerUp = this.playerLocation.getPowerUp();
		MythicalCreature mythicalCreature = this.playerLocation.getMythicalCreature();
		Trap trap = this.playerLocation.getTrap();

		if(smallCreature != null ) {
			if(this.magicWand > 0) {
				this.magicWand -= 1;
			}
			concatMessage("A snake took one power!\n Power:" + magicWand);
			//System.out.println("Power lost:" + magicWand);
		}
		if(powerUp != null) {
			magicWand += 1;
			concatMessage("Power Found! \nPower:" + magicWand);
			//System.out.println("Power added:" + magicWand);
		}
		//mythical creature
		if(mythicalCreature != null || trap != null) {
			this.message = "GAME OVER! \n Lost by death.";
			disableButtonsForAttack();
			disableButtonsForPlayer();
			
			
			
			
		}
	}
	
	public static void disableButtonsForAttack() {
		GlobalFields.attack.getBtDown().setDisable(true);
		GlobalFields.attack.getBtUp().setDisable(true);
		GlobalFields.attack.getBtLeft().setDisable(true);
		GlobalFields.attack.getBtRight().setDisable(true);
		
	}
	
	public static void disableButtonsForPlayer() {
		GlobalFields.btForPlayer.getBtDown().setDisable(true);
		GlobalFields.btForPlayer.getBtUp().setDisable(true);
		GlobalFields.btForPlayer.getBtLeft().setDisable(true);
		GlobalFields.btForPlayer.getBtRight().setDisable(true);
		
	}
	
	


	private String getAdjacentMessage(Location[][] grid, int xPos, int yPos) {
		try {
			Location l = grid[xPos][yPos];
			return l.getMessage();
		}catch(Exception e) {
			return "";
		}

	}

	public static void move(String direction, Location playerLocation) {
		int currRow = playerLocation.getPos()[0];
		int currColumn = playerLocation.getPos()[1];
		Location currLoc = GlobalFields.grid[currRow][currColumn];
		Player player = currLoc.getPlayer();

		Location newLoc = null;

		if(direction.equals("down") && currRow < GlobalFields.row - 1) {
			newLoc = GlobalFields.grid[currRow + 1][currColumn];
		}
		else if(direction.equals("up") && currRow > 0) {
			newLoc = GlobalFields.grid[currRow - 1][currColumn];
		}
		else if(direction.equals("right") && currColumn < GlobalFields.column - 1) {
			newLoc = GlobalFields.grid[currRow][currColumn + 1];
		}
		else if(direction.equals("left") && currColumn > 0) {
			newLoc = GlobalFields.grid[currRow][currColumn - 1];
		}else {
			return;
		}


		currLoc.setPlayer(null);
		newLoc.setPlayer(player);
		//System.out.println(newLoc.toString());
		GlobalFields.playerLocation = newLoc;
		player.setPlayerLocation(newLoc);	
		//System.out.println("--------------------------------------------------------------------------------------------------");

		player.setMessage("");
		player.checkAdjacent(GlobalFields.grid);
		player.interact();
		GlobalFields.lb.setText(player.getMessage());
	}

	public void checkMonster(Location targetKrakenLocation ) {
		Location actualKrakenLoc = GlobalFields.krakenLocation;

		if(targetKrakenLocation.getMythicalCreature() != null) {
			GlobalFields.lb.setText("YOU WIN! GAME OVER!");
			disableButtonsForAttack();
			disableButtonsForPlayer();
			//System.out.println("YOU WIN! GAME OVER");
		}else {
			this.magicWand -= 1;
			GlobalFields.lb.setText("No monster there.... Let's keep looking.\n Power: " + this.magicWand);
			if(this.magicWand == 0) {
				GlobalFields.lb.setText("No more power! Go find more!");
			}
//			System.out.println("Power: " + magicWand);
//			System.out.println("no monster here");

			actualKrakenLoc.getMythicalCreature().moveKraken();

		}

	}


	public void shoot(String direction, Location playerLocation, Location possibleKrakenLoc) {
		int currRow = playerLocation.getPos()[0];
		int currColumn = playerLocation.getPos()[1];

		if(direction.equals("down") && currRow < GlobalFields.row - 1 && this.magicWand > 0) {
			possibleKrakenLoc = GlobalFields.grid[currRow + 1][currColumn];

			checkMonster(possibleKrakenLoc);

		}else if(direction.equals("up") && currRow > 0 && this.magicWand > 0){
			possibleKrakenLoc = GlobalFields.grid[currRow - 1][currColumn];
			checkMonster(possibleKrakenLoc);	
		}
		else if(direction.equals("left") && currColumn > 0 && this.magicWand > 0) {
			possibleKrakenLoc = GlobalFields.grid[currRow][currColumn - 1];
			checkMonster(possibleKrakenLoc);
		}
		else if(direction.equals("right") && currColumn <  GlobalFields.row - 1 && this.magicWand > 0) {
			possibleKrakenLoc = GlobalFields.grid[currRow][currColumn + 1];
			checkMonster(possibleKrakenLoc);
		}

		else {
			return;
		}

	}



}
