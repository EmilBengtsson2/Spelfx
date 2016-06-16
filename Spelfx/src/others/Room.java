package others;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Room {
	private int rows;
	private int cols;
	private char[][] room;

	public Room(String link) {
		try (BufferedReader br = new BufferedReader(new FileReader("Spelfx/src/Rooms/room1.txt"))) {
			//Kom ihåg hela adressen
			String line = br.readLine();
			rows = cols = 0;
			
			ArrayList<String> temp = new ArrayList<String>();

			while (line != null) {
				rows++;
				line = removeDots(line);
				temp.add(line);
				System.out.println(line);
				line = br.readLine();
			}
			
			cols = temp.get(0).length();
			room = new char[cols][rows];
			
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					room[j][i] = temp.get(i).charAt(j);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String removeDots(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char temp = s.charAt(i);
			if(temp != '.')
				sb.append(temp);
		}
		return sb.toString();
	}

	public char[][] getRoom() {
		return room;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}
}
