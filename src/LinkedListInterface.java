
public interface LinkedListInterface<T> {
	public void add(T newEntry);
	public void add (int givenPosition, T newEntry);
	public void clear();
	public T replace(int givenPosition, T newEntry);
	public boolean isEmpty();
	public T[] toArray();
	public boolean contains(T anEntry);
	public T remove(int givenPosition);
	public int getLength();
	public T getEntry(int givenPosition);
}



