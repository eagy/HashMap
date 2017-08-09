import java.util.LinkedList;
import java.util.Queue;



/**
 * @author Daniel Eagy
 * @version 1.0
 *
 */
public class LinearProbingMap<Key, Value> implements Map<Key, Value> {

    
    private int N; // number of key-value pairs
    private int M; // hash table size
    
    private Key[] key;
    private Value[] value;
    public LinearProbingMap() {
        this(997);
    }
    
    @SuppressWarnings("unchecked")
	public LinearProbingMap(int M) {
        this.N = 0;
        this.M = M;
        //entries = (Entry[]) new Object[M];
        key = (Key[]) new Object[M];
        value = (Value[]) new Object[M];
    }
    
	@Override
	public void put(Key key, Value val) {
		
       boolean added = false;
        
       int i = 0;
        for(i = (hash(key, i)); this.key[i] != null; i++)
            if(key.hashCode() == this.key[i].hashCode()) {
                this.value[i] = val;
                added = true;
            }
       
        if(!added) {
			i = numCollision(key);
			this.key[hash(key, i)] = key;
			this.value[hash(key, i)] = val;
			N++;
        }
	}
	
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
	private int numCollision(Key key) {
		int i = 0;
		
		while (this.key[hash(key, i)] != null) {
			i++;
		}
		
		return i; 
	}
	
	private int hash(Key key, int i) {
		return (hash(key)+i) % M;
	}
	
	@Override
	public Value get(Key key) {
		int i = 0;
		for (i = hash(key, i); this.key[i] != null; i++) {
			if(key.hashCode() == this.key[i].hashCode())
				return this.value[i];
		}

        return null;
	}

	@Override
	public void remove(Key key) {
		// TODO Auto-generated method stub
		if(contains(key)) {
			int i = hash(key);
			
			
			while(!(key.hashCode() ==this.key[i].hashCode())){
				i++;
			}
			
			this.key[i] = null;
			this.value[i] = null;
			i++;
			
			while (this.key[i] != null) {
			    // delete keys[i] an vals[i] and reinsert
				Key keyRehash = this.key[i];
				Value valueRehash = this.value[i];
				this.key[i] = null;
				this.value[i] = null;
				N--;
				put(keyRehash, valueRehash);
				i++;
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
		return N==0;
	}

	@Override
	public int size() {
		return N;
	}

	@Override
	public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<>();
        
		
		for (int i = 0; i < M; i++) {
			if(key[i] != null)
				queue.add(key[i]);
		}

        return queue;
	}

}
