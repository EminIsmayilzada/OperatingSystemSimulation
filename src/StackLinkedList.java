
import java.util.Comparator;
import java.util.EmptyStackException;



public class StackLinkedList<T> implements StackInterface<T>{

	private Node topNode;
	private int size;
	public StackLinkedList () {
		topNode = null;
		size = 0;
	}

	private class Node{
		private T data;
		private Node next;
		
		private Node(T dataPortion) {
			this(dataPortion, null);
		}

		public Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		}
		
		private T getData() {
			return data;
		}
		private void setData(T newData) {
			data = newData;
		}
		private Node getNextNode() {
			return next;
		}
		private void setNextNode(Node nextNode) {
			next = nextNode;
		}

	}
	public class BurstComparator implements Comparator<Task> {
        @Override
        public int compare(Task task1, Task task2) {
            // Compare based on priority in descending order
            int burstComparison = Integer.compare(task2.getBurstTime(), task1.getBurstTime());

            if (burstComparison != 0) {
                return burstComparison;
            } else {
                // If priorities are the same, compare based on arrival time
                return task1.getArrivalDateTime().compareTo(task2.getArrivalDateTime());
            }
        }
    }        

	@Override
	public void push(T newEntry) {
		 BurstComparator burstComparator = new BurstComparator();
	     Task newTask = (Task) newEntry;
	     Node newNode = new Node(newEntry);

	     if (isEmpty() || burstComparator.compare(newTask, (Task) topNode.getData()) <= 0) {
	         newNode.setNextNode(topNode);
	         topNode = newNode;
	     } else {
	         Node currentNode = topNode;

	         while (currentNode.getNextNode() != null &&
	                 burstComparator.compare(newTask, (Task) currentNode.getNextNode().getData()) > 0) {
	             currentNode = currentNode.getNextNode();
	         }

	         newNode.setNextNode(currentNode.getNextNode());
	         currentNode.setNextNode(newNode);
	     }

	     size++;

		
	}
	
	@Override
	public T pop() {
		T top = peek();
		
		assert topNode != null;
		topNode = topNode.getNextNode();
		size--;
		return top;
		
	}
	
	@Override
	public T peek() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		else {
			return topNode.getData();
		}
	}
	@Override
	public boolean isEmpty() {
		return topNode == null;
	}
	@Override
	public void clear() {
		topNode = null;
		size=0;
	}

	@Override
	public int getSize() {
		return size;
	}
	@Override
	public T getEntry(int givenPosition) {
	    if (givenPosition >= 1 && givenPosition <= size) {
	        Node currentNode = topNode;
	        int currentPosition = 1;

	        while (currentPosition < givenPosition) {
	            currentNode = currentNode.getNextNode();
	            currentPosition++;
	        }

	        return currentNode.getData();
	    } else {
	        throw new IndexOutOfBoundsException("Invalid position: " + givenPosition);
	    }
	}
	
	
}


