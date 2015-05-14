package res;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Room {
	public Room left, right;
	public int type, roomNum;
	private BufferedImage picture;
	private String desc;
	private String[] decisionsText;
	private int[] decisions;
	public final static int START = 0, BOSS = 1, CHEST = 2, COMBAT = 3, ENCOUNTER = 4, EMPTY = 5;
	
	public Room(int type, int roomNum) throws FileNotFoundException {
		
		this.setType(type);
		decisions = new int[6];
		decisionsText = new String[6];
		if(type == CHEST) {
			getInfo("CHEST");
		}
		
		if(type == COMBAT) {
			getInfo("COMBAT");
		}
		if(type == ENCOUNTER) {
			getInfo("ENCOUNTER");
		}
		if(type == EMPTY) {
			getInfo("EMPTY");
		}
		if(type == START) {
			getInfo("START");
		}
		if(type == BOSS) {
			getInfo("BOSS");
		}
		this.roomNum = roomNum;
	}
	
	public void getInfo(String type) {
		Scanner s = new Scanner(this.getClass().getClassLoader().getResourceAsStream("dungeon/RoomConfig"));
		LinkedList<String> lines = new LinkedList<String>();
		while(s.hasNextLine()) {
			lines.add(s.nextLine());
		}
		s.close();
		setPicture(null);
		int line = 0;
		for(int i = 0; i < lines.size(); i++) {
			if(lines.get(i).equals(type)) {
				line = i;
				break;
			}
		}
		setDesc(lines.get(line+1));
		line++;
		int cnt = 0;
		while(!lines.get(line+1).equals("")) {
			decisions[cnt] = Integer.parseInt(lines.get(line+1));
			decisionsText[cnt] = lines.get(line+2);
			line += 2;
			cnt++;
			if(line > lines.size()-2) {
				break;
			}
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
	
	public int getDecision(int index) {return decisions[index];}
	public String getDecisionText() {
		String decisionText = "";
		for(int i = 0; i < decisionsText.length; i++) {
			if(decisionsText[i] == null) {
				break;
			}
			decisionText =  decisionText + decisionsText[i] + "\n";
		}
		return decisionText;
	}
}
