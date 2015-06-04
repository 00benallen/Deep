package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
 /**
  * Object stores battle scores from each battle simulation, sorts them when new ones are added, serialized at game close
  * @author Ben Pinhorn
  *
  */
public class Scoreboard implements Serializable{
	private static final long serialVersionUID = 4426303114495020688L; //change if class changes significantly
	private LinkedList<Integer> scores;
	private static int eleme;
	
	/**
	 * Default scoreboard constructor
	 */
	public Scoreboard() {
		scores = new LinkedList<Integer>();
	}
	
	 /**
	  * Adds a score to the score list, sorts list
	  * @param score
	  */
	public void addScore(int score) {
		scores.add(score);
		sort(); 
	}
	
	 /**
	  * Saves the scoreboard to the hard drive
	  * @throws IOException
	  */
	public void save() throws IOException {
		FileOutputStream fileOut = new FileOutputStream("res/scores.dp");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(this);
		out.close();
		fileOut.close();
	}
	
	 /**
	  * Loads the scoreboard from the hard drive
	  * @return newScoreboard
	  * @throws IOException
	  * @throws ClassNotFoundException
	  */
	public static Scoreboard load() throws IOException, ClassNotFoundException {
		FileInputStream fileIn = new FileInputStream("res/scores.dp");
		ObjectInputStream in = new ObjectInputStream(fileIn);
		Scoreboard newScoreboard = (Scoreboard) in.readObject();
		
		fileIn.close();
		in.close();
		
		return newScoreboard;
	}
	
	private void sort() {
		quickSort(0, eleme-1);
	}
	
	private void quickSort(int left, int right) {
		if(left < right) {
			int p = partition(left, right); //moves all items higher than pivot to one side, and lower to the other
			quickSort(left, p-1);
			quickSort(p+1, right);
		}
	}
	
	private int partition(int left, int right) {
		int pivotIndex = findPivot(scores, left, right); 
		int pivot = scores.get(pivotIndex);
		swap(scores, pivotIndex, right); //moves pivot to end
		int storeIndex = left;
		for(int i = left; i < right; i++) { //moves all higher than pivot to one side, all lower to other, in place
			if(scores.get(i) <= pivot) {
				swap(scores, i, storeIndex);
				storeIndex++;
			}
		}
		swap(scores, storeIndex, right);
		return storeIndex;
	}
	
	private static int findPivot(LinkedList<Integer> scores, int left, int right) { //finds the pivot using a median of 3 strategy
		if(scores.get(left) > scores.get(right)) {
			if(scores.get(left) <= scores.get((left + right)/2)) {
				return left;
			}
			else if(scores.get(left) > scores.get((left + right/2))) {
				return (left + right)/2;
			}
			else {
				return right;
			}
		}
		else {
			if(scores.get(right) < scores.get((left + right)/2)) {
				return right;
			}
			else if(scores.get(right) > scores.get((left + right)/2)) {
				return (left + right)/2;
			}
			else {
				return left;
			}
		}
	}
	
	private void swap(LinkedList<Integer> scores, int a, int b) { //swaps 2 numbers in list
		int s = scores.get(a);
		scores.set(a, scores.get(b));
		scores.set(b, s);
	}
}
