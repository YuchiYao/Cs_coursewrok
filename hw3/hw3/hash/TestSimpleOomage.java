package hash;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;

public class TestSimpleOomage {

    @Test
    public void testHashCodeDeterministic() {
        SimpleOomage so = SimpleOomage.randomSimpleOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    @Test
    public void testHashCodePerfect() {
        SimpleOomage ooA = new SimpleOomage(15, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 30);
        SimpleOomage ooA3 = new SimpleOomage(30, 10, 5);
        SimpleOomage ooA4 = new SimpleOomage(20, 15, 10);
        SimpleOomage ooA5 = new SimpleOomage(50, 50, 50);
        SimpleOomage ooB = new SimpleOomage(15, 10, 20);
        SimpleOomage ooB2 = new SimpleOomage(5, 10, 30);
        SimpleOomage ooB3 = new SimpleOomage(40, 5, 0);
        HashSet<SimpleOomage> hashSet = new HashSet<SimpleOomage>();
        hashSet.add(ooA);
        hashSet.add(ooA2);
        hashSet.add(ooA3);
        hashSet.add(ooA4);
        assertTrue(hashSet.contains(ooB));
        assertTrue(hashSet.contains(ooB2));
        assertFalse(hashSet.contains(ooB3));
        assertFalse(hashSet.contains(ooA5));
    }

    @Test
    public void testEquals() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(50, 50, 50);
        assertEquals(ooA, ooA2);
        assertNotEquals(ooA, ooB);
        assertNotEquals(ooA2, ooB);
        assertNotEquals(ooA, "ketchup");
    }

    @Test
    public void testHashCodeAndEqualsConsistency() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        HashSet<SimpleOomage> hashSet = new HashSet<SimpleOomage>();
        hashSet.add(ooA);
        assertTrue(hashSet.contains(ooA2));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestSimpleOomage.class);
    }
}
