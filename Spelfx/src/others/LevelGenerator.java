package others;

import enemies.HappyArrow;
import enemies.HatEnemy;
import enemies.RedSkull;
import framework.PlayerListener;
import objects.DoorBlock;
import objects.WallBlock;
import weapons.Spear;

public class LevelGenerator {
	private static Room room = new Room(null);

	// halvd�ligt utkast p� en levelgenerator
	public static void generateLevel(World world, PlayerListener listener, int level) {
		switch (level) {
		case 1:
			level1(world, listener);
			break;
		}
	}

	private static void level1(World world, PlayerListener listener) {
		Player player = new Player(600, 400, listener, world);
		player.setWeapon(new Spear(player, world.getEntityController()));
		//H�r byter man vapen
		
		world.getAnimateEntities().add(player);

		// Block, 63 pixels wide, height 59 atm.

		char[][] design = room.getRoom();

		for (int i = 0; i < room.getRows(); i++) {
			for (int j = 0; j < room.getCols(); j++) {
				if (design[j][i] == '-') {
					world.getObjects().add(new WallBlock(j * 50, i * 50));
				} else if (design[j][i] == 'x') {
					world.getAnimateEntities().add(new RedSkull(50 * j, 50 * i));
				} else if (design[j][i] == 'h') {
					world.getAnimateEntities().add(new HappyArrow(50 * j, 50 * i));
				} else if (design[j][i] == 'D') {
					if (j+1 < room.getCols() && design[j+1][i] == 'D')
						world.getObjects().add(new DoorBlock(50 * j, 50 * i, 'H'));
					else if (i+1 < room.getRows() && design[j][i+1] == 'D')
						world.getObjects().add(new DoorBlock(50 * j, 50 * i, 'V'));
				} else if (design[j][i] == 'H') {
					world.getAnimateEntities().add(new HatEnemy(50 * j, 50 * i));
				}
			}
		}
	}
}
