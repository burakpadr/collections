import java.util.Iterator;

public class Bag<T> implements Iterable<T>{
    
    private T[] items;
    private int size;

    public Bag(){
        items = (T[]) new Object[2];
    }

    public Bag<T> add(T item){
        if (size >= items.length / 2)
            resize(items.length * 2);
        
        int hash = getHash(item);

        while (items[hash] != null){
            if (item.equals(items[hash]))
                return this;

            hash = (hash + 1) % items.length;
        }
        
        items[hash] = item;
        size++;

        return this;
    }

    public Bag<T> delete(T item){
        int hash = getHash(item);

        while (items[hash] != null){
            if (item.equals(items[hash])){
                items[hash] = null;
                size--;
    
                hash = (hash + 1) % items.length;
    
                while (items[hash] != null){
                    T temp = items[hash];
    
                    items[hash] = null;
                    size--;
    
                    add(temp);
    
                    hash = (hash + 1) % items.length;
                }
    
                if (size <= items.length / 4)
                    resize(items.length / 2);
    
                return this;
            }

            hash = (hash + 1) % items.length;
        }
    
        return null;
    }

    public boolean contains(T item){
        int hash = getHash(item);

        while (items[hash] != null){
            if (item.equals(items[hash]))
                return true;

            hash = (hash + 1) % items.length;
        }

        return false;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public Iterator<T> iterator(){
        return new BagIterator();
    }

    private int getHash(T item){
        return (items.hashCode() & 0x7fffffff) % items.length;
    }

    private void resize(int newSize){
        T[] temp = items;

        items = (T[]) new Object[newSize];

        for (int i = 0; i < temp.length; i++)
            if (temp[i] != null){
                add(temp[i]);

                size--;
            }
    }

    private class BagIterator implements Iterator<T>{
        
        T[] iteratorItems;
        int iteratorSize;

        public BagIterator(){
            iteratorItems = (T[]) new Object[size];

            for (int i = 0; i < items.length; i++) 
                if (items[i] != null){
                    iteratorItems[iteratorSize] = items[i];
                    iteratorSize++;
                }
        }

        @Override
        public boolean hasNext(){
            return iteratorSize > 0;
        }

        @Override
        public T next(){
            iteratorSize--;

            return iteratorItems[iteratorSize];
        }
    }
}
