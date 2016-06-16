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

	// halvdåligt utkast på en levelgenerator
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
		//Här byter man vapen
		
		world.getAnimateEntities().add(player);

		// Block, 63 pixels wide, height 59 atm.

		char[][] design = room.getRoom();

		for (int i = 0; i < room.getRows(); i++) {
			for (int j = 0; j < room.getCols(); j++) {
				if (design[j][i] == '-') {
					world.getObjects().add(new WallBlock(j * 63, i * 59));
				} else if (design[j][i] == 'x') {
					world.getAnimateEntities().add(new RedSkull(63 * j, 59 * i));
				} else if (design[j][i] == 'h') {
					world.getAnimateEntities().add(new HappyArrow(63 * j, 59 * i));
				} else if (design[j][i] == 'D') {
					world.getObjects().add(new DoorBlock(63 * j, 59 * i, 'V'));
				} else if (design[j][i] == 'H') {
					world.getAnimateEntities().add(new HatEnemy(63 * j, 59 * i));
				}
			}
		}
	}
}
