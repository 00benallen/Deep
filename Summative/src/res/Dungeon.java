package res;

import java.util.Random;

public class Dungeon {
	private Room startRoom;
	private Room bossRoom;
	Random r;
	
	public Dungeon() {
		startRoom = new Room(Room.START);
		bossRoom = new Room(Room.BOSS);
		
		r = new Random();
		int l = r.nextInt(60) + 20;
		genDungeonRooms(startRoom, l);
		
	}
	
	public void genDungeonRooms(Room root, int length) {
		int gType = r.nextInt(6);
		root.left = new Room(gType);
		root.right = new Room(gType);
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
}
