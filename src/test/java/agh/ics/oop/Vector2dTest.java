package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    Vector2d vector2dFirst = new Vector2d(0,0);
    Vector2d vector2dSecond = new Vector2d(1,1);

    @Test
    void testToString() {
        assertEquals(vector2dFirst.toString(), "(0,0)");
        assertEquals(vector2dSecond.toString(), "(1,1)");
    }

    @Test
    void precedes() {
        assertTrue(vector2dFirst.precedes(vector2dSecond));
        assertFalse(vector2dSecond.precedes(vector2dFirst));
    }

    @Test
    void follows() {
        assertFalse(vector2dFirst.follows(vector2dSecond));
        assertTrue(vector2dSecond.follows(vector2dFirst));
    }

    @Test
    void upperRight() {
        assertEquals(vector2dFirst.upperRight(vector2dSecond), new Vector2d(1,1));
    }

    @Test
    void lowerLeft() {
        assertEquals(vector2dFirst.lowerLeft(vector2dSecond), new Vector2d(0,0));
    }

    @Test
    void add() {
        assertEquals(vector2dFirst.add(vector2dSecond), new Vector2d(1,1));
    }

    @Test
    void subtract() {
        assertEquals(vector2dFirst.subtract(vector2dSecond), new Vector2d(-1,-1));
    }

    @Test
    void opposite() {
        assertEquals(vector2dFirst.opposite(), new Vector2d(0,0));
        assertEquals(vector2dSecond.opposite(), new Vector2d(-1,-1));
    }

    @Test
    void testEquals() {
        assertFalse(vector2dFirst.equals(vector2dSecond));
        assertFalse(vector2dSecond.equals(vector2dFirst));

        assertTrue(new Vector2d(3,2).equals(new Vector2d(3,2)));

    }

    @Test
    void testHashCode() {
        Vector2d firstVector = new Vector2d(3,2);
        Vector2d secondVector = new Vector2d(3,2);

        if (firstVector.equals(secondVector)) {
            assertEquals(firstVector.hashCode(), secondVector.hashCode());
        }
    }
}