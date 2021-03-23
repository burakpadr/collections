import java.util.Iterator;

public class Queue<T> implements Iterable<T>{
    
    private LinkedList<T> linkedList;

    public Queue(){
        linkedList = new LinkedList<>();
    }

    public Queue<T> enqueue(T item){
        linkedList.insertLast(item);

        return this;
    }

    public T dequeue(){
        T item = linkedList.getFirst();

        linkedList.deleteFirst();

        return item;
    }

    public int getSize(){
        return linkedList.getSize();
    }

    public boolean isEmpty(){
        return linkedList.isEmpty();
    }

    public Iterator<T> iterator(){
        return linkedList.iterator();
    }
}
