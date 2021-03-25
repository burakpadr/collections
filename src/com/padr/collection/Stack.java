package com.padr.collection;

import java.util.Iterator;

public class Stack implements Iterable<Object>{

    private LinkedList linkedList;

    public Stack(){
        linkedList = new LinkedList();
    }

    public Stack push(Object item){
        linkedList.insertFirst(item);

        return this;
    }

    public Object pop(){
        Object item = linkedList.getFirst();

        linkedList.deleteFirst();

        return item;
    }

    public long getSize(){
        return linkedList.getSize();
    }

    public boolean isEmpty(){
        return linkedList.isEmpty();  
    }

    public Iterator<Object> iterator(){
        return linkedList.iterator();
    }
}
