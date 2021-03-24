import java.util.Iterator;

public class SymbolTable{
    
    private Object[] keys;
    private Object[] values;
    private int size;

    private int getHash(Object key){
        return (key.hashCode() & 0x7fffffff) % keys.length;
    }

    private void resize(int newSize){
        Object[] keysTemp = keys;
        Object[] valuesTemp = values;

        keys = new Object[newSize];
        values = new Object[newSize];

        for (int i = 0; i < keysTemp.length; i++)
            if (keysTemp[i] != null){
                put(keysTemp[i], valuesTemp[i]);
                
                size--;
            }
    }

    public SymbolTable(){
        keys = new Object[2];
        values = new Object[2];
    }

    public SymbolTable put(Object key, Object value){
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

    public Object get(Object key){
        int hash = getHash(key);

        while (keys[hash] != null){
            if (key.equals(keys[hash]))
                return values[hash];
            
            hash = (hash + 1) % keys.length;
        }

        return null;
    }

    public SymbolTable delete(Object key){
        int hash = getHash(key);

        while (keys[hash] != null){
            if (key.equals(keys[hash])){
                keys[hash] = null;
                values[hash] = null;
                size--;

                hash = (hash + 1) % keys.length;

                while (keys[hash] != null){
                    Object keyTemp = keys[hash];
                    Object valueTemp = values[hash];

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

    public boolean contains(Object key){
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

    public Iterator<Object> keys(){
        return new SymbolTableIterator(keys);
    }

    public Iterator<Object> values(){
        return new SymbolTableIterator(values);
    }

    private class SymbolTableIterator implements Iterator<Object>{
        private Object[] iteratorItems;
        private int iteratorSize;

        public SymbolTableIterator(Object[] items){
            iteratorItems = new Object[size];
            
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
        public Object next(){
            iteratorSize--;

            return iteratorItems[iteratorSize];
        }
    }
}
