import java.util.Comparator;

public class LinkedPriorityQueue<T extends Comparable<? super T>> implements PriorityQueueInterface<T> {
    private LList<T> elements;

    public LinkedPriorityQueue() {
        elements = new LList<>();
    }
   
    public class PriorityComparator implements Comparator<Task> {
        @Override
        public int compare(Task task1, Task task2) {
            // Compare based on priority in descending order
            int priorityComparison = Integer.compare(task2.getPriority(), task1.getPriority());

            if (priorityComparison != 0) {
                return priorityComparison;
            } else {
                // If priorities are the same, compare based on arrival time
                return task1.getArrivalDateTime().compareTo(task2.getArrivalDateTime());
            }
        }
    }


    public void add(T newEntry) {
        // Add the new entry to the appropriate position based on its priority
        PriorityComparator priorityComparator = new PriorityComparator();
        Task newTask = (Task) newEntry;

        if (isEmpty()) {
            elements.add(newEntry);
        } else {
            boolean added = false;
            int currentPosition = 1;

            while (!added && currentPosition <= getSize()) {
                Task currentTask = (Task) elements.getEntry(currentPosition);

                if (priorityComparator.compare(newTask, currentTask) < 0) {
                    elements.add(currentPosition, newEntry);
                    added = true;
                }

                currentPosition++;
            }

            if (!added) {
                elements.add(newEntry);
            }
        }
    }
    public T remove() {
        // Remove and return the element with the highest priority (the first element)
        return elements.remove(1);
    }

    public T peek() {
        // Return the element with the highest priority without removing it
        return elements.getEntry(1);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public int getSize() {
        return elements.getLength();
    }

    public void clear() {
        elements.clear();
    }
    public T getEntry(int givenPosition) {
        if (givenPosition >= 1 && givenPosition <= getSize()) {
            return elements.getEntry(givenPosition);
        } else {
            throw new IndexOutOfBoundsException("Invalid position: " + givenPosition);
        }
    }
}






