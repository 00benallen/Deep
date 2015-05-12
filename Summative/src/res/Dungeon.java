package res;

import java.io.FileNotFoundException;
import java.util.Random;

public class Dungeon {
	private Room startRoom;
	private Room bossRoom;
	private int curRoom;
	Random r;
	
	public Dungeon() throws FileNotFoundException {
		startRoom = new Room(Room.START, 0);
		
		
		r = new Random();
		int l = r.nextInt(10) + 5 ;
		bossRoom = new Room(Room.BOSS, l);
		genDungeonRooms(startRoom, l);
		setCurRoom(0);
	}
	
	private void genDungeonRooms(Room root, int length) throws FileNotFoundException {
		int gType = r.nextInt(6);
		root.left = new Room(gType, root.roomNum+1);
		root.right = new Room(gType, root.roomNum+2);
		if(length > 0) {
			genDungeonRooms(root.left, length-1);
			genDungeonRooms(root.right, length-1);
		}
		else {
			root.left.left = bossRoom;
			root.left.right = bossRoom;
			root.right.left = bossRoom;
			root.right.right = bossRoom;
		}
	}
	
	public Room getRoom(int index) {
		return findRoom(startRoom, index);
	}
	
	private Room findRoom(Room root, int roomNum) {
		if(root.roomNum == roomNum) {
			return root;
		}
		if(roomNum < bossRoom.roomNum) {
			return null;
		}
		
		Room resultLeft = findRoom(root.left, roomNum);
		Room resultRight = findRoom(root.right, roomNum);
		
		if(resultLeft != null) {
			return resultLeft;
		}
		if(resultRight != null) {
			return resultRight;
		}
		else {
			return null;
		}
		
		
	}

	public int getCurRoom() {
		return curRoom;
	}

	public void setCurRoom(int curRoom) {
		this.curRoom = curRoom;
	}
}
