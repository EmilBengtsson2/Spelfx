package others;
import enemies.HappyArrow;
import enemies.HatEnemy;
import enemies.RedSkull;
import framework.PlayerListener;
import weapons.Sword;

public class LevelGenerator {
	
	// halvdåligt utkast på en levelgenerator
	public static void generateLevel(World world, PlayerListener listener, int level) {		
		switch(level) {
			case 1: 
				level1(world,listener);
				break;						
		}		
	}
	
	private static void level1(World world, PlayerListener listener) {
		Player player = new Player(1.5, 600, 450, listener, world);
		player.setWeapon(new Sword(player, world.getEntityController())); //Här byter man vapen
		world.getAnimateEntities().add(player);

		// Enemy 1
		HatEnemy enemy1 = new HatEnemy(600, 300, player, world);
		world.getAnimateEntities().add(enemy1);
		

		// RandomMovers
		RedSkull rs = new RedSkull(400, 800, world);
		HappyArrow happy = new HappyArrow(300,300, world);
		
		world.getAnimateEntities().add(rs);
		world.getAnimateEntities().add(happy);
		
		// Block, 63 pixels wide, height 59 atm.
		int startX = 63;		
		int startY = 59;
		for (int i = 1; i < 30; i++) {
			world.getObjects().add(new Block(startX*i, 0, world));		
			world.getObjects().add(new Block(0, startY*i, world));
			world.getObjects().add(new Block(startX*i, 1000, world));
			world.getObjects().add(new Block(1500, startY*i, world));
		}
		
		
	}
}
