package hash;

import edu.princeton.cs.algs4.Complex;

import java.util.HashSet;
import java.util.Set;

public class HashTableVisualizer {

    public static void main(String[] args) {
        /* scale: StdDraw scale
           N:     number of items
           M:     number of buckets */

        double scale = 1.0;
        int N = 1000;
        int M = 10;

        HashTableDrawingUtility.setScale(scale);
        Set<Oomage> oomies = new HashSet<Oomage>();
        for (int i = 0; i < N; i += 1) {
            oomies.add(ComplexOomage.randomComplexOomage());
        }
        visualize(oomies, M, scale);
    }

    public static void visualize(Set<Oomage> set, int M, double scale) {
        HashTableDrawingUtility.drawLabels(M);
        HashTableDrawingUtility.setScale(scale);

        /* TODO: Create a visualization of the given hash table. Use
           du.xCoord and du.yCoord to figure out where to draw
           Oomages.
         */
        //SimpleOomage someOomage = (SimpleOomage) set;
        int[] counter = new int[M];
        for (int i=0; i<counter.length;i++){
            counter[i]=0;
        }
        for (Oomage iter:set){
            int buckNumber =( iter.hashCode()&0x7FFFFFFF) % M;


            iter.draw(HashTableDrawingUtility.xCoord(counter[buckNumber]),
                    HashTableDrawingUtility.yCoord(buckNumber,M),scale);
            counter[buckNumber]+=1;
        }




        /* When done with visualizer, be sure to try 
           scale = 0.5, N = 2000, M = 100. */           
    }
} 
