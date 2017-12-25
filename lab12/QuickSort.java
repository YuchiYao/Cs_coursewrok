import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Quick;

import java.util.Collection;
import java.util.Collections;

public class QuickSort {
    /**
     * Returns a new queue that contains the given queues catenated together.
     *
     * The items in q2 will be catenated after all of the items in q1.
     */
    private static <Item extends Comparable> Queue<Item> catenate(Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> catenated = new Queue<Item>();
        for (Item item : q1) {
            catenated.enqueue(item);
        }
        for (Item item: q2) {
            catenated.enqueue(item);
        }
        return catenated;
    }

    /** Returns a random item from the given queue. */
    private static <Item extends Comparable> Item getRandomItem(Queue<Item> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        Item pivot = null;
        // Walk through the queue to find the item at the given index.
        for (Item item : items) {
            if (pivotIndex == 0) {
                pivot = item;
                break;
            }
            pivotIndex--;
        }
        return pivot;
    }

    /**
     * Partitions the given unsorted queue by pivoting on the given item.
     *
     * @param unsorted a Queue of unsorted items
     * @param pivot the item to pivot on
     * @param less an empty Queue. When the function completes, this queue will contain
     *             all of the items in unsorted that are less than the given pivot.
     * @param equal an empty Queue. When the function completes, this queue will contain
     *              all of the items in unsorted that are equal to the given pivot.
     * @param greater an empty Queue. When the function completes, this queue will contain
     *                all of the items in unsorted that are greater than the given pivot.
     */
    private static <Item extends Comparable> void partition(
            Queue<Item> unsorted, Item pivot, Queue<Item> less,
            Queue<Item> equal, Queue<Item> greater) {
        // Your code here!

        while(!unsorted.isEmpty()){
            Item element = unsorted.dequeue();
            if(element.compareTo(pivot)>0){
                greater.enqueue(element);
            }else if (element.compareTo(pivot)<0){
                less.enqueue(element);
            }else{
                equal.enqueue(element);
            }
        }


    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> quickSort(
            Queue<Item> items) {
        
        items=quickSorthelp(items);
        return items;
    }

    public static <Item extends Comparable >Queue<Item> quickSorthelp(Queue<Item> unsorted){
        if (unsorted.isEmpty()) {
            return unsorted;
        }

        Item pivot = getRandomItem(unsorted);
        Queue<Item> les = new Queue<>();
        Queue<Item> equ = new Queue<>();
        Queue<Item> gre = new Queue<>();
        partition(unsorted,pivot,les,equ,gre);
        les=quickSorthelp(les);
        gre=quickSorthelp(gre);
        unsorted = QuickSort.catenate(les,equ);
        unsorted = QuickSort.catenate(unsorted,gre);
        return unsorted;
    }
    public static void main(String args[]){
        Queue<Integer> que = new Queue<>();
        que.enqueue(4);
        que.enqueue(1);
        que.enqueue(4);
        que.enqueue(9);
        que.enqueue(1);
        que.enqueue(20);
        que.enqueue(15);
        System.out.println(QuickSort.quickSort(que).toString());

    }
}
