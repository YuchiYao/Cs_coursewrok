package synthesizer;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        first =1;
        last=0;
        fillCount=0;
        this.capacity = capacity;
        rb = (T [])new Object [capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public int addone(int index){
        // Reach the up range and wrap back to 0
        if (index == capacity-1) return 0;

        else return index+1;
    }

    public int minusone(int index){
        if (index == 1) return capacity-1;

        else return index-1;
    }

    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (capacity <= fillCount ) throw new RuntimeException("Ring buffer overflow");
        else {
            this.last = addone(this.last);
            rb[this.last]=x;
            fillCount+=1;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        T output;
        if (fillCount==0 ) throw new RuntimeException("Ring buffer underflow");
        else {
            output=rb[this.first];
            rb[this.first]=null;
            this.first = addone(this.first);
            fillCount-=1;
        }
        return output;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        return rb[this.first];
    }

    public static void main(String[] args) {

        ArrayRingBuffer <String> a = new ArrayRingBuffer<>(5);
        a.enqueue("yao");
        a.enqueue("yu");
        a.enqueue("chi");
        a.enqueue("ai");
        a.enqueue("Jiang");
        System.out.println(a.dequeue());

    }

        // TODO: When you get to part 5, implement the needed code to support iteration.
}

