package manese.ashley;

public class Trap extends Item{
	
	public Trap() {
		super.setName("Ocean Trap ");
	}
	
	public boolean validate(Location l) {
		MythicalCreature creature = l.getMythicalCreature(); 
		SmallCreature sc = l.getSmallCreature();
		Player player = l.getPlayer();
		PowerUp powerUp = l.getPowerUp();
		
		
		if(creature == null && sc == null  && player == null &&  powerUp == null) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return super.getName();
	}

}
