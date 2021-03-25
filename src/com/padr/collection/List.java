package com.padr.collection;

import java.util.Iterator;

public class List implements Iterable<Object>{
    
    private LinkedList linkedList;

    public List(){
        linkedList = new LinkedList();
    }

    public List append(Object item){
        linkedList.insertLast(item);

        return this;
    }

    public List delete(Object item){
        if (linkedList.delete(item) == null)
            return null;

        return this;
    }

    public List delete(int index){
        linkedList.deleteAt(index);

        return this;
    }

    public Object get(int index){
        return linkedList.find(index);
    }

    public int indexOf(Object item){
        return linkedList.indexOf(item);
    }

    public boolean contains(Object item){
        return linkedList.contains(item);
    }

    public int getSize(){
        return linkedList.getSize();
    }

    public boolean isEmpty(){
        return linkedList.isEmpty();
    }

    public Iterator<Object> iterator(){
        return linkedList.iterator();
    }
}
