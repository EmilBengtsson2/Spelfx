package others;
import java.util.Arrays;
import java.util.Scanner;

import enemies.HappyArrow;
import enemies.HatEnemy;
import enemies.RedSkull;
import framework.PlayerListener;
import objects.EventBlock;
import objects.WallBlock;
import weapons.Spear;

public class LevelGenerator {
	private static Room room = new Room(null);
	
	// halvdåligt utkast på en levelgenerator
	public static void generateLevel(World world, PlayerListener listener, int level) {		
		switch(level) {
			case 1: 
				level1(world,listener);
				break;						
		}		
	}
	
	private static void level1(World world, PlayerListener listener) {
		Player player = new Player(600, 400, listener, world);
		player.setWeapon(new Spear(player, world.getEntityController())); //Här byter man vapen
		//player.setWeapon(new Sword2(player));
		world.getAnimateEntities().add(player);

		// Block, 63 pixels wide, height 59 atm.

		String design = room.getRoom();
		
	    Scanner sc = new Scanner(design).useDelimiter("[.]");
	    final int rows = room.getRows();
	    final int cols = room.getCols();
	    String[][] matrix = new String[rows][cols];
	    for (int r = 0; r < rows; r++) {
	        for (int c = 0; c < cols; c++) {
	            matrix[r][c] = sc.next();
	        }
	    }
	   
	    System.out.print(matrix[1][2]);
	    
	    

	    
	    
	    for(int i = 0; i < 10; i++){
	    	for(int j = 0; j < 24; j++){
	    		if(matrix[i][j].equals("-")){
	    			world.getObjects().add(new WallBlock(j*63, i*59));
	    			
	    		}else if(matrix[i][j].equals("x")){
	    			world.getAnimateEntities().add(new RedSkull(63*i,59*j));
	    		}else if(matrix[i][j].equals("D")){
	    			world.getObjects().add(new EventBlock(63*i, 59*j));
	    		}
	    	}
		}
	    sc.close();	
	}
}
