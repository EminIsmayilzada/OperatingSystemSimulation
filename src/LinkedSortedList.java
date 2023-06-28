import java.util.Comparator;

public class LinkedSortedList<T extends Comparable<? super T>> implements SortedListInterface<T> {
	private Node firstNode;
	private int numberOfEntries;
	public LinkedSortedList() {
		firstNode=null;
		numberOfEntries=0;
	}
	
	@Override
	public void add(T newEntry) {
		Node newNode= new Node(newEntry);
		Node nodeBefore= getNodebefore(newEntry);
		if (isEmpty()||(nodeBefore==null)) {
			newNode.setNextNode(firstNode);
			firstNode=newNode;
			}
		else {
			Node nodeAfter=nodeBefore.getNextNode();
			newNode.setNextNode(nodeAfter);
			nodeBefore.setNextNode (newNode) ;
		}
		numberOfEntries++;
	}
	private Node getNodebefore(T anEntry) {
		Node currentNode = firstNode;
		Node nodeBefore= null;
		while ((currentNode!=null)&&(anEntry.compareTo(currentNode.getData()) > 0)) {
			nodeBefore = currentNode;
			currentNode = currentNode.getNextNode();
			
		}
		return nodeBefore;
	}

	@Override
    public boolean remove(T anEntry) {
        boolean isRemoved = false;

        if (isEmpty()) {
            return false;
        }

        if (anEntry.equals(firstNode.data)) {
            firstNode = firstNode.next;
            numberOfEntries--;
            return true;
        }

        Node currentNode = firstNode;
        while (currentNode.next != null && !anEntry.equals(currentNode.next.data)) {
            currentNode = currentNode.next;
        }

        if (currentNode.next != null) {
            currentNode.next = currentNode.next.next;
            numberOfEntries--;
            isRemoved = true;
        }

        return isRemoved;
    }

	@Override
	public int getPosition(T anEntry) {
		int position = 1;
        Node currentNode = firstNode;

        while (currentNode != null && anEntry.compareTo(currentNode.data) > 0) {
            currentNode = currentNode.next;
            position++;
        }

        if (currentNode != null && anEntry.equals(currentNode.data)) {
            return position;
        }

        return -1;
    }
	

	@Override
	public T getEntry(int givenPosition) {
		 if (givenPosition >= 1 && givenPosition <= numberOfEntries) {
	            Node currentNode = getNodeAt(givenPosition);
	            return currentNode.getData();
	        }

	        return null;
	    }

	

	@Override
	public boolean contains(T anEntry) {
		return getPosition(anEntry)!=-1;
	}

	@Override
	public T remove(int givenPosition) {
		T result=null;
		if ((1<=givenPosition)&&(givenPosition<=numberOfEntries)) {
			assert isEmpty();
			if (givenPosition==1) {
				result = firstNode.getData();
				firstNode=firstNode.getNextNode();
				
			} else {
				Node nodeBefore=getNodeAt(givenPosition-1);
				Node nodeToRemove=nodeBefore.getNextNode();
				result=nodeToRemove.getData();
				Node nodeAfter=nodeToRemove.getNextNode();
				nodeBefore.setNextNode(nodeAfter);				
			}
			numberOfEntries--;
			return result;
			
		}
		else {
			throw new IndexOutOfBoundsException("Illegal position given to the remove operation.");
		}
	}
	private Node getNodeAt(int givenPosition) {
		assert (firstNode!=null)&&(1<=givenPosition)&&(givenPosition<=numberOfEntries);
		Node currentNode=firstNode;
		for (int counter = 1; counter < givenPosition; counter++) {
			currentNode=currentNode.getNextNode();
			assert currentNode!=null;	
		}
		return currentNode;
	}

	@Override
	public void clear() {
		firstNode=null;
		numberOfEntries=0;
		
	}

	@Override
	public int getLength() {
		return numberOfEntries;
	}

	@Override
	public boolean isEmpty() {
		return numberOfEntries==0;
	}

	@Override
	public T[] toArray() {
		 @SuppressWarnings("unchecked")
	        T[] result = (T[]) new Comparable[numberOfEntries];
	        int index = 0;
	        Node currentNode = firstNode;

	        while (currentNode != null) {
	            result[index] = currentNode.data;
	            currentNode = currentNode.next;
	            index++;
	        }

	        return result;
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

}
