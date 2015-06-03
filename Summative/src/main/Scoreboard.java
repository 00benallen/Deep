package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

public class Scoreboard implements Serializable{
	private static final long serialVersionUID = 4426303114495020688L;
	private LinkedList<Integer> scores;
	private static int eleme;
	
	public Scoreboard() {
		scores = new LinkedList<Integer>();
	}
	
	public void addScore(int score) {
		scores.add(score);
		sort();
	}
	
	public void save() throws IOException {
		FileOutputStream fileOut = new FileOutputStream("res/scores.dp");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(this);
		out.close();
		fileOut.close();
	}
	
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
	
	public void quickSort(int left, int right) {
		if(left < right) {
			int p = partition(left, right);
			quickSort(left, p-1);
			quickSort(p+1, right);
		}
	}
	
	private int partition(int left, int right) {
		int pivotIndex = findPivot(scores, left, right);
		int pivot = scores.get(pivotIndex);
		swap(scores, pivotIndex, right);
		int storeIndex = left;
		for(int i = left; i < right; i++) {
			if(scores.get(i) <= pivot) {
				swap(scores, i, storeIndex);
				storeIndex++;
			}
		}
		swap(scores, storeIndex, right);
		return storeIndex;
	}
	
	private static int findPivot(LinkedList<Integer> scores, int left, int right) {
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
	
	private void swap(LinkedList<Integer> scores, int a, int b) {
		int s = scores.get(a);
		scores.set(a, scores.get(b));
		scores.set(b, s);
	}
}