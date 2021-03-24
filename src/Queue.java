import java.util.Iterator;

public class Queue implements Iterable<Object>{
    
    private LinkedList linkedList;

    public Queue(){
        linkedList = new LinkedList();
    }

    public Queue enqueue(Object item){
        linkedList.insertLast(item);

        return this;
    }

    public Object dequeue(){
        Object item = linkedList.getFirst();

        linkedList.deleteFirst();

        return item;
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
