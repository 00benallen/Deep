package res;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;

import main.Main;

/**
 * Object defining a dungeon, contains both the beginning and end root (the dungeon is a tree of nodes), and algorithm for generation
 * @author Ben Pinhorn
 *
 */
public class Dungeon {
	private Room startRoom;
	private Room bossRoom;
	private int curRoom;
	private int rooms;
	Random r;
	
	/**
	 * Constructor for dungeon object, generates the tree of rooms
	 * @throws FileNotFoundException
	 */
	public Dungeon() throws FileNotFoundException {
		startRoom = new Room(Room.START, 0);
		
		r = new Random();
		int l = r.nextInt(5) + 5;
		Main.log.log(Level.INFO, "Running dungeon generator");
		genDungeonRooms(startRoom, l);
		setCurRoomNum (0);
	}
	
	private void genDungeonRooms(Room root, int length) throws FileNotFoundException {
		int gType = r.nextInt(3) + 2;
		root.left = new Room(gType, (root.roomNum*2)+1);
		root.right = new Room(gType, (root.roomNum*2)+2);
		rooms += 2;
		if(length > 0) {
			genDungeonRooms(root.left, length-1);
			genDungeonRooms(root.right, length-1);
		}
		else {
			bossRoom = new Room(Room.BOSS, rooms+1);
			root.left.left = bossRoom;
			root.left.right = bossRoom;
			root.right.left = bossRoom;
			root.right.right = bossRoom;
		}
	}
	
	/**
	 * Returns the room with specified room number
	 * @param index
	 * @return
	 */
	public Room getRoom(int index) {
		return findRoom(startRoom, index);
	}
	
	private Room findRoom(Room root, int roomNum) {
		if(root.roomNum == roomNum) {
			return root;
		}
		if(root.roomNum == bossRoom.roomNum && roomNum != bossRoom.roomNum) {
			return null;
		}
		
		Room resultLeft = null;
		if(root.left != null) {
			resultLeft = findRoom(root.left, roomNum);
		}
		Room resultRight = null;
		if(root.right != null) {
			resultRight = findRoom(root.right, roomNum);
		}
		
		
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

	/**
	 * Returns the number of the current room
	 * @return
	 */
	public int getCurRoomNum() {
		return curRoom;
	}
	
	/**
	 * Sets the number of the current room
	 * @param curRoom
	 */
	public void setCurRoomNum(int curRoom) {
		this.curRoom = curRoom;
	}
	
	/**
	 * Returns the current room as an object
	 * @return
	 */
	public Room getCurRoom() {
		return getRoom(curRoom);
	}
	
	/**
	 * Generates the item from a chest
	 * @return chest item
	 * @throws IOException
	 */
	public Item genChestItem() throws IOException {
		Random rand = new Random();
		int itemType = rand.nextInt(6);
		Item genItem = null;
		
		if(itemType == Item.HELMET) {
			genItem = new Item(Item.HELMET);
		}
		if(itemType == Item.CHESTPLATE) {
			genItem = new Item(Item.CHESTPLATE);
		}
		if(itemType == Item.LEGGINGS) {
			genItem = new Item(Item.LEGGINGS);
		}
		if(itemType == Item.BOOTS) {
			genItem = new Item(Item.BOOTS);
		}
		if(itemType == Item.AMULET) {
			genItem = new Item(Item.AMULET);
		}
		if(itemType == Item.RING) {
			genItem = new Item(Item.RING);
		}
		
		return genItem;
	}
}
