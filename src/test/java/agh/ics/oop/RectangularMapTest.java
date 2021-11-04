package agh.ics.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {
    private IWorldMap map;
    private Animal animal1;
    private Animal animal2;


    @Test
    void canMoveTo() {
        assertFalse(map.canMoveTo(new Vector2d(2,2)));
        assertFalse(map.canMoveTo(new Vector2d(3,4)));
        assertFalse(map.canMoveTo(new Vector2d(11,4)));
        assertFalse(map.canMoveTo(new Vector2d(4,11)));
        assertFalse(map.canMoveTo(new Vector2d(2,6)));
        assertFalse(map.canMoveTo(new Vector2d(-1,-1)));
        assertFalse(map.canMoveTo(new Vector2d(1,-1)));
        assertFalse(map.canMoveTo(new Vector2d(-1,1)));

        assertTrue(map.canMoveTo(new Vector2d(1,1)));
        assertTrue(map.canMoveTo(new Vector2d(3,2)));
        assertTrue(map.canMoveTo(new Vector2d(2,3)));
        assertTrue(map.canMoveTo(new Vector2d(4,4)));
        assertTrue(map.canMoveTo(new Vector2d(3,5)));
        assertTrue(map.canMoveTo(new Vector2d(0,0)));
        assertTrue(map.canMoveTo(new Vector2d(10,5)));
    }

    @Test
    void place() {
        assertFalse(map.place(new Animal(map, new Vector2d(2,2))));
        assertFalse(map.place(new Animal(map, new Vector2d(3,4))));
        assertFalse(map.place(new Animal(map, new Vector2d(11,4))));
        assertFalse(map.place(new Animal(map, new Vector2d(3,6))));
        assertFalse(map.place(new Animal(map, new Vector2d(-1,1))));
        assertFalse(map.place(new Animal(map, new Vector2d(1,-1))));

        assertTrue(map.place(new Animal(map, new Vector2d(1,1))));
        assertTrue(map.place(new Animal(map, new Vector2d(3,3))));
        assertTrue(map.place(new Animal(map, new Vector2d(4,4))));
        assertTrue(map.place(new Animal(map, new Vector2d(10,5))));
        assertTrue(map.place(new Animal(map, new Vector2d(0,0))));
        assertTrue(map.place(new Animal(map, new Vector2d(4,2))));
    }

    @Test
    void isOccupied() {
        assertTrue(map.isOccupied(new Vector2d(2,2)));
        assertTrue(map.isOccupied(new Vector2d(3,4)));

        assertFalse(map.isOccupied(new Vector2d(1,1)));
        assertFalse(map.isOccupied(new Vector2d(0,0)));
        assertFalse(map.isOccupied(new Vector2d(-1,1)));
        assertFalse(map.isOccupied(new Vector2d(-4,2)));
        assertFalse(map.isOccupied(new Vector2d(4,-2)));
        assertFalse(map.isOccupied(new Vector2d(10,5)));
        assertFalse(map.isOccupied(new Vector2d(-100,100)));
    }

    @Test
    void objectAt() {
        assertEquals(animal1, map.objectAt(new Vector2d(2,2)));
        assertEquals(animal2, map.objectAt(new Vector2d(3,4)));
    }

    @BeforeEach
    void setUp() {
        map = new RectangularMap(10,5);
        animal1 = new Animal(map,new Vector2d(2,2));
        animal2 = new Animal(map,new Vector2d(3,4));
        map.place(animal1);
        map.place(animal2);
    }
}