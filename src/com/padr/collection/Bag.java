package com.padr.collection;

import java.util.Iterator;

public class Bag implements Iterable<Object>{
    
    private Object[] items;
    private int size;

    public Bag(){
        items = new Object[2];
    }

    public Bag add(Object item){
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

    public Bag delete(Object item){
        int hash = getHash(item);

        while (items[hash] != null){
            if (item.equals(items[hash])){
                items[hash] = null;
                size--;
    
                hash = (hash + 1) % items.length;
    
                while (items[hash] != null){
                    Object temp = items[hash];
    
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

    public boolean contains(Object item){
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

    private int getHash(Object item){
        return (items.hashCode() & 0x7fffffff) % items.length;
    }

    private void resize(int newSize){
        Object[] temp = items;

        items = new Object[newSize];

        for (int i = 0; i < temp.length; i++)
            if (temp[i] != null){
                add(temp[i]);

                size--;
            }
    }

    public Iterator<Object> iterator(){
        return new BagIterator();
    }


    private class BagIterator implements Iterator<Object>{
        
        Object[] iteratorItems;
        int iteratorSize;

        public BagIterator(){
            iteratorItems = new Object[size];

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
        public Object next(){
            iteratorSize--;

            return iteratorItems[iteratorSize];
        }
    }
}
