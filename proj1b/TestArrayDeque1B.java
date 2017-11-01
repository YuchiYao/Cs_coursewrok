import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDeque1B {

    @Test
    public static void main(String[] args) {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> sad2 = new ArrayDequeSolution<>();
        OperationSequence operation = new OperationSequence();

        int Iterator = StdRandom.uniform(20,100);
        int redusal_time=0;                  // Check the array is empty or not
        for (int i=0;i<Iterator;i++){
            int option =StdRandom.uniform(4);//1:ADD FIRST,2:ADD LAST,3:REMOVE FIRST,4:REMOVE LAST
            if(option==0) {
                sad1.addFirst(i);
                sad2.addFirst(i);
                operation.addOperation(new DequeOperation("addFirst", i));
                redusal_time++;
                assertEquals("False:\n   Student Solution " + sad1.get(0)
                                + " not equal to " + sad2.get(0) + "!\n"+operation.toString(),
                        sad2.get(0), sad1.get(0));

            }
            else if (option ==1){
                sad1.addLast(i);
                sad2.addLast(i);
                operation.addOperation(new DequeOperation("addLast", i));
                redusal_time++;
                assertEquals("False:\n   Student Solution " + sad1.get(0)
                                + " not equal to " + sad2.get(0) + "!\n"+operation.toString(),
                        sad2.get(0), sad1.get(0));
            }
            else if(option ==2&&redusal_time>0){
                Integer student_s = sad1.removeFirst();
                Integer solution_s= sad2.removeFirst();
                operation.addOperation(new DequeOperation("removeFirst",solution_s ));
                redusal_time--;
                assertEquals("False:\n   Student Solution " + student_s
                                + " not equal to " + solution_s + "!\n"+operation.toString(),
                        solution_s, student_s);
            }
            else if(option ==3&&redusal_time>0){
                Integer student_s =sad1.removeLast();
                Integer solution_s=sad2.removeLast();
                operation.addOperation(new DequeOperation("removeLast", solution_s));
                redusal_time--;
                //System.out.println(operation[].toString());
                assertEquals("False:\n   Student Solution " + student_s
                                + " not equal to " + solution_s + "!\n"+operation.toString(),
                        solution_s, student_s);
            }
        }


    }
}
