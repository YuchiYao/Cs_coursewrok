// TODO: Make sure to make this class a part of the synthesizer package
//package <package name>;
package synthesizer;

import org.omg.CORBA.INTERNAL;

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

//Make sure this class is public
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        // TODO: Create a buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this divsion operation into an int. For better
        //       accuracy, use the Math.round() function before casting.
        //       Your buffer should be initially filled with zeros.
        buffer =new ArrayRingBuffer<Double>((int)Math.round(SR/frequency));
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // TODO: Dequeue everything in the buffer, and replace it with random numbers
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        //
        //       Make sure that your random numbers are different from each other.
        Set<Double> random_input= new HashSet<>(buffer.capacity());
        while (random_input.size() <buffer.capacity()){
            random_input.add(Math.random() - 0.5);
        }
        Iterator<Double> it = random_input.iterator();
        BoundedQueue<Double> bufferStack =new ArrayRingBuffer<>(buffer.capacity());
        while(it.hasNext()){
            bufferStack.enqueue(it.next());
        }
        buffer = bufferStack;
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() {
        // TODO: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       Do not call StdAudio.play().
        double a= buffer.dequeue();
        buffer.enqueue((a+buffer.peek())*0.5*DECAY);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // TODO: Return the correct thing.

        return buffer.peek();
    }

    public static void main(String argus[]){
        GuitarString a = new GuitarString(1000);
        a.pluck();
        a.tic();
        System.out.println("1");
    }
}
