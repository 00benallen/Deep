package res;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

public class Room {
	public Room left, right;
	public int type, roomNum;
	private BufferedImage picture;
	private String desc;
	private String[] decisionsText;
	private int[] decisions;
	public final static int CHEST = 0, COMBAT = 1, ENCOUNTER = 2, EMPTY = 3, START = 4, BOSS = 5;
	
	public Room(int type, int roomNum) throws FileNotFoundException {
		File file = new File(this.getClass().getClassLoader().getResource("dungeon/RoomConfig").toString());
		Scanner s = new Scanner(file);
		LinkedList<String> lines = new LinkedList<String>();
		while(s.hasNextLine()) {
			lines.add(s.nextLine());
		}
		
		this.setType(type);
		decisions = new int[6];
		decisionsText = new String[6];
		if(type == CHEST) {
			setPicture(null);
			for(int i = 0; i < lines.size(); i++) {
				if(lines.get(i).equals("CHEST")) {
					decisions[0] = Integer.parseInt(lines.get(i+2));
					decisionsText[0] = lines.get(i+3);
					setDesc(lines.get(i+1));
					break;
				}
			}
		}
		if(type == COMBAT) {
			setPicture(null);
			setDesc("An enemy appears!");
		}
		if(type == ENCOUNTER) {
			setPicture(null);
			setDesc("An old man is in the room");
		}
		if(type == EMPTY) {
			setPicture(null);
			setDesc("There is nothing here");
		}
		if(type == START) {
			setPicture(null);
			setDesc("You enter a dark dungeon");
		}
		if(type == BOSS) {
			setPicture(null);
			setDesc("You have found the dragon!");
		}
		this.roomNum = roomNum;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public BufferedImage getPicture() {
		return picture;
	}

	public void setPicture(BufferedImage picture) {
		this.picture = picture;
	}
}
