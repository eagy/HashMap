import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Daniel Eagy
 * @version 1.0
 */
public class TwoProbeChainMap<Key, Value> implements Map<Key, Value> {
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
    
    public TwoProbeChainMap() {
        this(997);
    }
    
    public TwoProbeChainMap(int M) {
        this.N = 0;
        this.M = M;
        entries = new LinkedList[M];
        for (int i = 0; i < M; i++)
            entries[i] = new LinkedList<>();
    }
    
	@Override
	public void put(Key key, Value val) {
        boolean added = false;
        
        for(Entry entry : entries[hash(key)])
            if(key.hashCode() == entry.key.hashCode()) {
                entry.value = val;
                added = true;
            }
        
        for(Entry entry : entries[hash2(key)])
            if(key.hashCode() == entry.key.hashCode()) {
                entry.value = val;
                added = true;
            }
        
        if(!added) {
        	if(entries[hash(key)].size() < entries[hash2(key)].size()) {
	             entries[hash(key)].add(new Entry(key, val));
	             N++;
        	}
        	else {
        		entries[hash2(key)].add(new Entry(key, val));
        		N++;
        	}
        }
		
	}

	@Override
	public Value get(Key key) {
		   for(Entry entry : entries[hash(key)])
	            if(key.hashCode() == entry.key.hashCode())
	                return entry.value;
		   for(Entry entry : entries[hash2(key)])
	            if(key.hashCode() == entry.key.hashCode())
	                return entry.value;
	        return null;
	}
	
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	private int hash2(Key key) {
		return (((key.hashCode() & 0x7fffffff)%M) * 31) % M;
	}
	@Override
	public void remove(Key key) {
       if(contains(key)) {
            Entry target = null;
            
            for(Entry e : entries[hash(key)]) {
                if(e.key == key) {
                    target = e;
                    entries[hash(key)].remove(target);
                }
            }
            
            for(Entry e : entries[hash2(key)]) {
                if(e.key == key) {
                    target = e;
                    entries[hash2(key)].remove(target);
                }
            }
            
            N--;
        }
	}

	@Override
	public boolean contains(Key key) {
		return get(key) != null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return N==0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return N;
	}

	@Override
	public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<>();
        
        for (int i = 0; i < M; i++)
            for(Entry e : entries[i])
                    queue.add(e.key);
        
        return queue;
	}

}
