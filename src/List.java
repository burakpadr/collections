import java.util.Iterator;

public class List<T> implements Iterable<T>{
    
    private LinkedList<T> linkedList;

    public List(){
        linkedList = new LinkedList<>();
    }

    public List<T> append(T item){
        linkedList.insertLast(item);

        return this;
    }

    public List<T> delete(T item){
        if (linkedList.delete(item) == null)
            return null;

        return this;
    }

    public List<T> delete(int index){
        linkedList.deleteAt(index);

        return this;
    }

    public T get(int index){
        return linkedList.find(index);
    }

    public int indexOf(T item){
        return linkedList.indexOf(item);
    }

    public boolean contains(T item){
        return linkedList.contains(item);
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
