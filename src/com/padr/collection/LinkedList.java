package com.padr.collection;

import java.util.Iterator;

public class LinkedList implements Iterable<Object>{
    
    private class Node{
        Object item;
        Node prev;
        Node next;

        public Node(Object item, Node prev, Node next){
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        public Object getItem(){
            return item;
        }

        public Node getPrev(){
            return prev;
        }

        public Node getNext(){
            return next;
        }

        public Node setItem(Object item){
            this.item = item;

            return this;
        }

        public Node setPrev(Node prev){
            this.prev = prev;

            return this;
        }

        public Node setNext(Node next){
            this.next = next;

            return this;
        }
    }

    private Node first;
    private Node last;
    private int size;

    public LinkedList insertFirst(Object item){
        Node newNode = new Node(item, null, first);

        if (last == null)
            last = newNode;

        first = newNode;

        if (first.getNext() != null)
            first.getNext().setPrev(first);

        size++;

        return this;
    }

    public LinkedList insertLast(Object item){
        Node newNode = new Node(item, null, null);

        if (first == null)
            first = newNode;
        else{
            last.setNext(newNode);
            newNode.setPrev(last);
        }
        
        last = newNode;
        size++;

        return this;
    }

    public LinkedList insertAt(Object item, int position){
        if (position < 0 || position > size)
            throw new IndexOutOfBoundsException(String.format("%s: %d <= %s <= %d", "Position must be between like that", 0, "Position", size));
        else if (position == 0)
            insertFirst(item);
        else if (position == size)
            insertLast(item);
        else{
            Node wantedNode = first;

            for (int i = 0; i < position; i++)
                wantedNode = wantedNode.getNext();

            Node newNode = new Node(item, wantedNode.getPrev(), wantedNode);

            newNode.getPrev().setNext(newNode);
            newNode.getNext().setPrev(newNode);

            size++;
        }

        return this;
    }

    public LinkedList deleteFirst(){
        if (first.getNext() != null){
            first.getNext().setPrev(null);
            first = first.getNext();
        }
        else{
            first = null;
            last = null;
        }

        size--;

        return this;
    } 
    
    public LinkedList deleteLast(){
        if (last.getPrev() != null){
            last.getPrev().setNext(null);
            last = last.getPrev();

            size--;
        }
        else
            deleteFirst();

        return this;
    }

    public LinkedList deleteAt(int position){
        if (isEmpty())
            throw new NullPointerException("The linked list is empty!");
        else if (position < 0 || position >= size)
            throw new IndexOutOfBoundsException(String.format("%s: %d <= %s < %d", "Position must be between like that", 0, "Position", size == 0 ? 1: size));
        else if (position == 0)
            deleteFirst();
        else if (position == size - 1)
            deleteLast();
        else{
            Node wantedNode = first;

            for (int i = 0; i < position; i++)
                wantedNode = wantedNode.getNext();
            
            wantedNode.getPrev().setNext(wantedNode.getNext());
            wantedNode.getNext().setPrev(wantedNode.getPrev());

            size--;
        }

        return this;
    }

    public LinkedList delete(Object item){
        int itemIndex = indexOf(item);

        if (itemIndex == -1)
            return null;
        
        deleteAt(itemIndex);

        return this;
    }

    public Object getFirst(){
        return first.getItem();
    }

    public Object getLast(){
        return last.getItem();
    }

    public Object find(int position){
        if (isEmpty())
            throw new NullPointerException("The linked list is empty!");
        else if (position < 0 || position >= size)
            throw new IndexOutOfBoundsException(String.format("%s: %d <= %s < %d", "Position must be between like that", 0, "Position", size == 0 ? 1: size));
        else if (position == 0)
            return getFirst();
        else if (position == size - 1)
            return getLast();
        else{
            Node wantedNode = first;

            for (int i = 0; i < position; i++)
                wantedNode = wantedNode.getNext();
            
            return wantedNode.getItem();
        }
    }

    public int indexOf(Object item){
        int index = 0;

        for (Node temp = first; temp != null; temp = temp.getNext()){
            if (temp.getItem() == item)
                return index;
            
            index++;
        }

        return -1;
    }

    public boolean contains(Object item){
        for (Node temp = first; temp != null; temp = temp.getNext())
            if (temp.getItem() == item)
                return true;
        
        return false;
    }

    public LinkedList setFirst(Object item){
        first.setItem(item);

        return this;
    }

    public LinkedList setLast(Object item){
        last.setItem(item);

        return this;
    }

    public LinkedList setAt(Object item, int position){
        if (isEmpty())
            throw new NullPointerException("The linked list is empty!");
        else if (position < 0 || position >= size)
            throw new IndexOutOfBoundsException(String.format("%s: %d <= %s < %d", "Position must be between like that", 0, "Position", size));
        else if (position == 0)
            return setFirst(item);
        else if (position == size - 1)
            return setLast(item);
        else{
            Node wantedNode = first;

            for (int i = 0; i < position; i++)
                wantedNode = wantedNode.getNext();
            
            wantedNode.setItem(item);
        }

        return this;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public Iterator<Object> iterator(){
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Object>{

        Node iteratorFirst = first;

        @Override
        public boolean hasNext(){
            return iteratorFirst != null;
        }

        @Override
        public Object next(){
            Object item = iteratorFirst.getItem();

            iteratorFirst = iteratorFirst.getNext();

            return item;
        }
    }
}
