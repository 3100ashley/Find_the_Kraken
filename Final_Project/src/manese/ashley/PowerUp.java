package manese.ashley;

public class PowerUp extends Item{
	
	public PowerUp() {
		super.setName("Power Up ");
	}
	
	public boolean validate(Location l) {
		MythicalCreature creature = l.getMythicalCreature(); 
		SmallCreature sc = l.getSmallCreature();
		Player player = l.getPlayer();
		Trap trap = l.getTrap();
		
		if(creature == null && sc == null  && player == null &&  trap == null) {
			return true;
		}
		return false;
	}
	
	
	public String toString() {
		return super.getName();
	}
}
