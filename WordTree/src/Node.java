
public class Node {

		public Node next;
		public String word;
		public int count;
		
		public Node(String word) {
		next = null;
		this.word = word;
		count = 1;
		}
		
		public Node() {
		this(null);	
		}
}			
