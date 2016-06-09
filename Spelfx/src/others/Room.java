package others;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import entities.Entity;

public class Room {
	private Entity[] enemies;
	private int rows;
	private int cols;
	private String design;
	
	public Room(String link){
		try(BufferedReader br = new BufferedReader(new FileReader("Spelfx/src/Rooms/room1.txt"))) { //Kom ihåg hela adressen
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    String[][] room = new String[line.length()][line.length()];
		    
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    
		    design = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		rows = 10;
		cols = 24;
	}
	
	public String getRoom(){
		return design;
	}
	
	public int getRows(){
		return rows;
	}
	
	public int getCols(){
		return cols;
	}
	
	
	
}
