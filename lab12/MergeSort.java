import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Quick;
import org.junit.Assert;
import org.junit.Test;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param q1 A Queue in sorted order from least to greatest.
     * @param q2 A Queue in sorted order from least to greatest.
     * @return The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        // Your code here!
        Queue<Queue<Item> > result = new Queue<>();
        while(!items.isEmpty()){
            Queue<Item> inputQueue = new Queue<>();
            inputQueue.enqueue(items.dequeue());
            result.enqueue(inputQueue);
        }
        return result;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param q1 A Queue in sorted order from least to greatest.
     * @param q2 A Queue in sorted order from least to greatest.
     * @returns A Queue containing all of the q1 and q2 in sorted order, from least to
     *     greatest.
     * @Warnning The input two queue cannot be null at the same time
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        // Your code here!
        if (q1==null) return q2;
        else if(q2==null) return q1;
        else{
            Queue<Item> output = new Queue<>();
            while(!q1.isEmpty()||!q2.isEmpty()){
                output.enqueue(MergeSort.getMin(q1,q2));
            }
            return output;
        }
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        // Your code here!
        Queue<Queue<Item>> seprateQueues = MergeSort.makeSingleItemQueues(items);
        while(seprateQueues.size()!=1){
            int size = seprateQueues.size();
            if(size%2==1)seprateQueues.enqueue(MergeSort.mergeSortedQueues(seprateQueues.dequeue(),null));
            for(int i=0;i<(int)size/2;i+=1){
                seprateQueues.enqueue(MergeSort.mergeSortedQueues(seprateQueues.dequeue(),seprateQueues.dequeue()));
            }
        }
        return seprateQueues.dequeue();
    }
    public static void main(String args[]){
        Queue<Integer> students = new Queue<>();
        students.enqueue(13);
        students.enqueue(4);
        students.enqueue(34);
        students.enqueue(32);
        students.enqueue(6);
        System.out.println("Unsorted Queue"+students.toString());
        //Queue<Queue<String>> receive =MergeSort.makeSingleItemQueues(students);
        Queue<Integer> sort = MergeSort.mergeSort(students);
        System.out.println("Sorted Queue"+sort.toString());
    }

}
