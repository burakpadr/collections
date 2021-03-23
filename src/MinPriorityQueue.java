import java.util.Iterator;

public class MinPriorityQueue<T extends Comparable<T>> implements Iterable<T>{
    
    private T[] binaryTree;
    private int size;

    public MinPriorityQueue(){
        binaryTree = (T[]) new Comparable[2];
    }

    public MinPriorityQueue<T> enqueue(T item){
        if (size >= binaryTree.length / 2)
            resize(binaryTree.length * 2);
        
        size++;
        binaryTree[size] = item;

        swim();

        return this;
    }

    public T popMin(){
        swap(1, size);

        T item = binaryTree[size];

        binaryTree[size] = null;
        size--;

        sink();

        if (size <= binaryTree.length / 4)
            resize(binaryTree.length / 2);

        return item;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private void swim(){
        for (int i = size; i > 1; i /= 2) {
            int indexOfCompare = i / 2;

            if (binaryTree[i].compareTo(binaryTree[indexOfCompare]) < 0)
                swap(i, indexOfCompare);
            else
                return;
        }
    }

    private void sink(){
        int indexOfParent = 1;
        int indexOfCompare = indexOfParent * 2;

        while (indexOfCompare <= size){
            if (indexOfCompare + 1 <= size)
                indexOfCompare += binaryTree[indexOfCompare + 1].compareTo(binaryTree[indexOfCompare]) < 0 ? 1: 0;  
            
            if (binaryTree[indexOfParent].compareTo(binaryTree[indexOfCompare]) > 0)
                swap(indexOfParent, indexOfCompare);
            else
                return;
            
            indexOfParent = indexOfCompare;
            indexOfCompare *= 2;
        }
    }

    private void swap(int firstIndex, int secondIndex){
        T temp = binaryTree[firstIndex];

        binaryTree[firstIndex] = binaryTree[secondIndex];
        binaryTree[secondIndex] = temp;
    }

    private void resize(int newSize){
        T[] temp = binaryTree;

        binaryTree = (T[]) new Comparable[newSize];

        for (int i = 1; i <= size; i++)
            binaryTree[i] = temp[i];
    }

    public Iterator<T> iterator(){
        return new MinPriorityQueueIterator();
    }

    private class MinPriorityQueueIterator implements Iterator<T>{

        private MinPriorityQueue<T> iteratorMinPQ;

        public MinPriorityQueueIterator(){
            iteratorMinPQ = new MinPriorityQueue<>();

            for (int i = 1; i <= getSize(); i++)
                iteratorMinPQ.enqueue(binaryTree[i]);
        }

        @Override
        public boolean hasNext(){
            return !iteratorMinPQ.isEmpty();
        }

        @Override
        public T next(){
            return iteratorMinPQ.popMin();
        }
    }
}
