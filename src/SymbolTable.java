import java.util.Iterator;

public class SymbolTable<K, V> {
    
    private K[] keys;
    private V[] values;
    private int size;

    private int getHash(K key){
        return (key.hashCode() & 0x7fffffff) % keys.length;
    }

    private void resize(int newSize){
        K[] keysTemp = keys;
        V[] valuesTemp = values;

        keys = (K[]) new Object[newSize];
        values = (V[]) new Object[newSize];

        for (int i = 0; i < keysTemp.length; i++)
            if (keysTemp[i] != null){
                put(keysTemp[i], valuesTemp[i]);
                
                size--;
            }
    }

    public SymbolTable(){
        keys = (K[]) new Object[2];
        values = (V[]) new Object[2];
    }

    public SymbolTable<K, V> put(K key, V value){
        if (size >= keys.length / 2)
            resize(keys.length * 2);

        int hash = getHash(key);

        while (keys[hash] != null){
            if (keys[hash] == key){
                values[hash] = value;

                return this;
            }
            
            hash = (hash + 1) % keys.length;
        }

        keys[hash] = key;
        values[hash] = value;
        size++;

        return this;
    }

    public V get(K key){
        int hash = getHash(key);

        while (keys[hash] != null){
            if (key.equals(keys[hash]))
                return values[hash];
            
            hash = (hash + 1) % keys.length;
        }

        return null;
    }

    public SymbolTable<K, V> delete(K key){
        int hash = getHash(key);

        while (keys[hash] != null){
            if (key.equals(keys[hash])){
                keys[hash] = null;
                values[hash] = null;
                size--;

                hash = (hash + 1) % keys.length;

                while (keys[hash] != null){
                    K keyTemp = keys[hash];
                    V valueTemp = values[hash];

                    keys[hash] = null;
                    values[hash] = null;
                    size--;

                    put(keyTemp, valueTemp);

                    hash = (hash + 1) % keys.length;
                }

                if (size <= keys.length / 4)
                    resize(keys.length / 2);    

                return this;
            }

            hash = (hash + 1) % keys.length;
        }

        return null;
    }

    public boolean contains(K key){
        int hash = getHash(key);

        while (keys[hash] != null){
            if (key.equals(keys[hash]))
                return true;

            hash = (hash + 1) % keys.length;
        }

        return false;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public Iterator<K> keys(){
        return new SymbolTableIterator<K>(keys);
    }

    public Iterator<V> values(){
        return new SymbolTableIterator<V>(values);
    }

    private class SymbolTableIterator<T> implements Iterator<T>{
        private T[] iteratorItems;
        private int iteratorSize;

        public SymbolTableIterator(T[] items){
            iteratorItems = (T[]) new Object[size];
            
            for (int i = 0; i < items.length; i++) {
                if (items[i] != null){
                    iteratorItems[iteratorSize] = items[i];
                    iteratorSize++;
                }
            }
        }

        @Override
        public boolean hasNext(){
            return iteratorSize != 0;
        }

        @Override
        public T next(){
            iteratorSize--;

            return iteratorItems[iteratorSize];
        }
    }
}
