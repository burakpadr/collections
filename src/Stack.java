import java.util.Iterator;

public class Stack<T> implements Iterable<T>{

    private LinkedList<T> linkedList;

    public Stack(){
        linkedList = new LinkedList<>();
    }

    public Stack<T> push(T item){
        linkedList.insertFirst(item);

        return this;
    }

    public T pop(){
        T item = linkedList.getFirst();

        linkedList.deleteFirst();

        return item;
    }

    public long getSize(){
        return linkedList.getSize();
    }

    public boolean isEmpty(){
        return linkedList.isEmpty();  
    }

    public Iterator<T> iterator(){
        return linkedList.iterator();
    }
}
