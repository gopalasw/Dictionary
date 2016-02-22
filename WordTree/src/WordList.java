
public class WordList {
	Node root;

	public WordList() {
		root = 	null;
	}

	public void insert(String word) {
		boolean added = false;
		if(root == null) {
			root = new Node(word);
			added = true;
		} else {
			Node cur = root;
			while(cur.next != null && !added) {
				if(cur.word.equals(word)) {
					cur.count++;
					added = true;
				}
				cur = cur.next;	
			}
			if(!added) {
				Node temp = new Node(word);
				cur.next = temp;
			}
		}
	}

	public boolean contains(String word) {
		Node cur = root;
		while(cur.next != null) {
			if(cur.word.equals(word)) {
				return true;
			}
			cur = cur.next;	
		}
		return false;
	}

	public boolean delete(String word) {
		Node cur = root;
		Node nextNode = cur.next;
		if(cur.word.equals(word)) {
			cur.count--;
			if(cur.count <= 0) {
				root = null;
			}
			return true;
		} else {
			while(nextNode!=null) {
				if(nextNode.word.equals(word)) {
					nextNode.count--;
					if(nextNode.count <= 0) {
						cur.next = nextNode.next;
					}
					return true;
				}
				cur = nextNode;
				nextNode = cur.next;
			}
		}
		return false;
	}
	
	public void printAll() {
		Node cur = root;
		while(cur!=null) {
			System.out.println("Word: " + cur.word + " Count: " + cur.count);
			cur = cur.next;
		}
	}

		public int getWordCount(String word) {
			Node cur = root;
			while(!cur.word.equals(word)) {
				cur = cur.next;
			}
			return cur.count;
		}

	}
