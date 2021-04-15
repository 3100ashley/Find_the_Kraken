package manese.ashley;

public class Location{
	
	private int[] pos = new int[2];
	private MythicalCreature mythicalCreature; 
	private Trap trap;
	private SmallCreature smallCreature;
	private PowerUp powerUp;
	private Player player;

	public Location(int xPos, int yPos) {
		this.pos[0] = xPos;
		this.pos[1] = yPos;
		
	}

	public MythicalCreature getMythicalCreature() {
		return mythicalCreature;
	}

	public Trap getTrap() {
		return trap;
	}

	public void setMythicalCreature(MythicalCreature creature) {
		this.mythicalCreature = creature;
	}

	public void setTrap(Trap trap) {
		this.trap = trap;
	}

	public SmallCreature getSmallCreature() {
		return smallCreature;
	}

	public PowerUp getPowerUp() {
		return powerUp;
	}

	public Player getPlayer() {
		return player;
	}

	public void setSmallCreature(SmallCreature smallCreature) {
		this.smallCreature = smallCreature;
	}

	public void setPowerUp(PowerUp powerUp) {
		this.powerUp = powerUp;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int[] getPos() {
		return pos;
	}

	public void setPos(int[] pos) {
		this.pos = pos;
	}

	public String getMessage() {
		String output = " ";

		if(this.mythicalCreature != null) {
			output += "\nI hear the sound of tentacles";
		}

		if(this.trap != null) {
			output += "\nSuddenly I feel like I'm about to drown...";
		}

		if(this.smallCreature != null) {
			output += "\nDo you hear slithering?";
		}

		if(this.powerUp != null) {
			output += "\nI see a light";
		}

		return output; 
	}

	public String toString() {
		String output = "";

		if(this.mythicalCreature != null) {
			output += this.mythicalCreature.getName() + "\n";
		}

		if(this.trap != null) {
			output += this.trap.getName() + "\n";
		}

		if(this.smallCreature != null) {
			output += this.smallCreature.getName() + "\n";
		}

		if(this.player != null) {
			output += this.player.getName() + "\n" ;
		}

		if(this.powerUp != null) {
			output += this.powerUp.getName() + "\n" ;
		}

		return output;
	}
}
