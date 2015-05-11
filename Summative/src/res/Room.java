package res;

import java.awt.image.BufferedImage;

public class Room {
	public Room left, right;
	public int type;
	private BufferedImage picture;
	private String desc;
	public final static int CHEST = 0, COMBAT = 1, ENCOUNTER = 2, EMPTY = 3, START = 4, BOSS = 5;
	
	public Room(int type) {
		this.setType(type);
		if(type == CHEST) {
			setPicture(null);
			setDesc("A chest is in the centre of the room");
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
