package others;
import enemies.HappyArrow;
import enemies.HatEnemy;
import enemies.RedSkull;
import framework.PlayerListener;
import objects.EventBlock;
import objects.WallBlock;
import weapons.Spear;
import weapons.Sword2;	

public class LevelGenerator {
	
	// halvd�ligt utkast p� en levelgenerator
	public static void generateLevel(World world, PlayerListener listener, int level) {		
		switch(level) {
			case 1: 
				level1(world,listener);
				break;						
		}		
	}
	
	private static void level1(World world, PlayerListener listener) {
		Player player = new Player(600, 450, listener, world);
		player.setWeapon(new Spear(player, world.getEntityController())); //H�r byter man vapen
		//player.setWeapon(new Sword2(player));
		world.getAnimateEntities().add(player);

		// Enemy 1
		HatEnemy enemy1 = new HatEnemy(600, 300);
		world.getAnimateEntities().add(enemy1);
		

		// RandomMovers
		RedSkull rs = new RedSkull(400, 800);
		HappyArrow happy = new HappyArrow(300,300);
		
		world.getAnimateEntities().add(rs);
		world.getAnimateEntities().add(happy);
		
		// Block, 63 pixels wide, height 59 atm.
		int startX = 63;
		int startY = 59;
		for (int i = 0; i <= 37; i++) {
			world.getObjects().add(new WallBlock(startX*i, 0));
			world.getObjects().add(new WallBlock(0, startY*i));
			world.getObjects().add(new WallBlock(startX*i, 1800-87));
			world.getObjects().add(new WallBlock(2400-69, startY*i));
		}
		world.getObjects().add(new EventBlock (800,800));
		
		
	}
}
