import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Daniel Eagy
 * @version 1.0
 *
 */
public class LinearProbingMap<Key, Value> implements Map<Key, Value> {
    private class Entry {
        public Key key;
        public Value value;
        public Entry (Key k, Value v) {
            key = k;
            value = v;
        }
    }
    
    private int N; // number of key-value pairs
    private int M; // hash table size
    
    private LinkedList<Entry>[] entries;
    
    public LinearProbingMap() {
        this(997);
    }
    
    public LinearProbingMap(int M) {
        this.N = 0;
        this.M = M;
        entries = new LinkedList[M];
        for (int i = 0; i < M; i++)
            entries[i] = new LinkedList<>();
    }
    
	@Override
	public void put(Key key, Value val) {
		// TODO Auto-generated method stub
		
	}
	
	private int hash(Key key) {
		// TODO
		return 0;
	}
	
	private boolean collision() {
		// TODO
		return false; 
	}
	@Override
	public Value get(Key key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Key key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Key key) {
		return get(key) != null;
	}

	@Override
	public boolean isEmpty() {
		return N==0;
	}

	@Override
	public int size() {
		return N;
	}

	@Override
	public Iterable<Key> keys() {
		// TODO Auto-generated method stub
		return null;
	}

}
