package manese.ashley;

public class SmallCreature extends Item {
	
	public SmallCreature() {
		super.setName("Snake ");
	}
	public boolean validate(Location l) {
		MythicalCreature creature = l.getMythicalCreature(); 
		Trap trap = l.getTrap();
		Player player = l.getPlayer();
		PowerUp powerUp = l.getPowerUp();
		
		if(creature == null && trap == null  && player == null &&  powerUp == null) {
			return true;
		}
		return false;


	}
	public String toString() {
		return super.getName();
	}
	

}
