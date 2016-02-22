import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordCounter {

	public static void main(String args[]) {
		long startTime, endTime, findMinTime, findMaxTime, insertTime = 0, deleteTime = 0, getCountTime = 0;
		String[] filesList = {"Holmes.txt", "OxfordMedical.txt", "words2.txt"};
		for(int file = 0; file < filesList.length; file++) {
			WordList list = new WordList();

			Scanner line = null;
			Scanner word = null;
			String fileName = filesList[file];

			try {
				line = new Scanner(new File(fileName));
			} catch (FileNotFoundException e) {
				System.out.println("Working Directory = " +  System.getProperty("user.dir")); //checking the files are in the right directory
				e.printStackTrace();  
			}

			//reading in and adding all words
			while(line.hasNextLine()){
				word = new Scanner(line.nextLine());
				while(word.hasNext()) {
					list.insert(word.next().toLowerCase());
				}
			}
			line.close();

			switch(fileName) {
			case "Holmes.txt":
				startTime = System.nanoTime();
				list.insert("wumpus");
				endTime = System.nanoTime();
				insertTime = endTime - startTime;
				
				startTime = System.nanoTime();
				list.delete("king");
				endTime = System.nanoTime();
				deleteTime = endTime - startTime;

				startTime = System.nanoTime();
				list.getWordCount("holmes");
				endTime = System.nanoTime();
				getCountTime = endTime - startTime;
				break;
			case "OxfordMedical.txt":
				startTime = System.nanoTime();
				list.insert("anteater");
				endTime = System.nanoTime();
				insertTime = endTime - startTime;
				
				startTime = System.nanoTime();
				list.delete("the");
				endTime = System.nanoTime();
				deleteTime = endTime - startTime;

				startTime = System.nanoTime();
				list.getWordCount("aardvark");
				endTime = System.nanoTime();
				getCountTime = endTime - startTime;
				break;
			case "words2.txt":
				startTime = System.nanoTime();
				list.insert("hyphenation");
				endTime = System.nanoTime();
				insertTime = endTime - startTime;
				
				startTime = System.nanoTime();
				list.delete("brisk");
				endTime = System.nanoTime();
				deleteTime = endTime - startTime;

				startTime = System.nanoTime();
				list.getWordCount("likely");
				endTime = System.nanoTime();
				getCountTime = endTime - startTime;
				break;
			}

			startTime = System.nanoTime();
			Node minNode = minWord(list);
			endTime = System.nanoTime();
			findMinTime = endTime - startTime;

			startTime = System.nanoTime();
			Node maxNode = maxWord(list);
			endTime = System.nanoTime();
			findMaxTime = endTime - startTime;

			System.out.println("\nFile: " + fileName);
			System.out.println("Most common word: \"" + maxNode.word + "\" appeared " + maxNode.count + " times.");
			System.out.println("Took " + findMaxTime/1000 + " microseconds to find the max.");
			System.out.println("Least common word: \"" + minNode.word + "\" appeared " + minNode.count + " times.");
			System.out.println("Took " + findMinTime/1000  + " microseconds to find the min.");
			System.out.println("Took " + insertTime/1000  + " microseconds to insert a word.");
			System.out.println("Took " + deleteTime/1000  + " microseconds to delete a word.");
			System.out.println("Took " + getCountTime/1000  + " microseconds to get a word.\n");


		}
	}

	public static Node maxWord(WordList list) {
		Node curMaxNode = null;
		int maxCount = 0;
		Node cur = list.root;
		while(cur!=null) {
			if(cur.count > maxCount) {
				curMaxNode = cur;
				maxCount = cur.count;
			}
			cur = cur.next;
		}
		return curMaxNode;
	}

	public static Node minWord(WordList list) {
		Node curMinNode = null;
		int minCount = Integer.MAX_VALUE;
		Node cur = list.root;
		while(cur!=null) {
			if(cur.count < minCount) {
				curMinNode = cur;
				minCount = cur.count;
			}
			cur = cur.next;
		}
		return curMinNode;
	}
}

