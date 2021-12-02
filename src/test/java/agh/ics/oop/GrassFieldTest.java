package agh.ics.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {
    private GrassField map;
    private Animal animal1;
    private Animal animal2;

    private Animal animalRight;
    private Animal animalTop;
    private Animal animalLeft;
    private Animal animalBottom;
    private Vector2d leftBottom;
    private Vector2d rightTop;

    @Test
    void canMoveTo() {
        assertFalse(map.canMoveTo(new Vector2d(2,2)));
        assertFalse(map.canMoveTo(new Vector2d(3,4)));

        assertTrue(map.canMoveTo(new Vector2d(1,1)));
        assertTrue(map.canMoveTo(new Vector2d(-1,-1)));
        assertTrue(map.canMoveTo(new Vector2d(1,-1)));
        assertTrue(map.canMoveTo(new Vector2d(-1,1)));
        assertTrue(map.canMoveTo(new Vector2d(1000,1000)));
        assertTrue(map.canMoveTo(new Vector2d(-1000,1000)));
        assertTrue(map.canMoveTo(new Vector2d(0,0)));
    }

    @Test
    void place() {
        assertTrue(map.place(new Animal(map, new Vector2d(1,1))));
        assertTrue(map.place(new Animal(map, new Vector2d(-1,-1))));
        assertTrue(map.place(new Animal(map, new Vector2d(100,100))));
        assertTrue(map.place(new Animal(map, new Vector2d(-100,-100))));
        assertTrue(map.place(new Animal(map, new Vector2d(100,-100))));
        assertTrue(map.place(new Animal(map, new Vector2d(-100,100))));
    }

    @Test
    void isOccupied() {
        assertTrue(map.isOccupied(new Vector2d(2,2)));
        assertTrue(map.isOccupied(new Vector2d(3,4)));
    }

    @Test
    void objectAt() {
        assertEquals(animal1, map.objectAt(new Vector2d(2,2)));
        assertEquals(animal2, map.objectAt(new Vector2d(3,4)));
    }

    @Test
    void checkMapBorders() {

        map.place(new Animal(map,new Vector2d(1000,1000)));
        assertEquals(new Vector2d(1000,1000), map.getRightTopCorner());

        map.place(new Animal(map,new Vector2d(-1000,-1000)));
        assertEquals(new Vector2d(-1000,-1000), map.getLeftBottomCorner());

        map.place(animalRight);
        assertEquals(new Vector2d(1001,1000), map.getRightTopCorner());
        animalRight.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(1002,1000), map.getRightTopCorner());

        map.place(animalTop);
        assertEquals(new Vector2d(1002,1001), map.getRightTopCorner());
        animalTop.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(1002,1002), map.getRightTopCorner());

        map.place(animalLeft);
        assertEquals(new Vector2d(-1001,-1000), map.getLeftBottomCorner());
        animalLeft.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(-1002,-1000), map.getLeftBottomCorner());

        map.place(animalBottom);
        assertEquals(new Vector2d(-1002,-1001), map.getLeftBottomCorner());
        animalBottom.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(-1002,-1002), map.getLeftBottomCorner());
    }

    @BeforeEach
    void setUp() {
        map = new GrassField(8);
        animal1 = new Animal(map,new Vector2d(2,2));
        animal2 = new Animal(map,new Vector2d(3,4));
        map.place(animal1);
        map.place(animal2);

        // for border tests
        leftBottom = animal1.getPosition().lowerLeft(animal2.getPosition());
        rightTop = animal1.getPosition().upperRight(animal2.getPosition());
        animalRight = new Animal(map,new Vector2d(1001,1), MapDirection.EAST);
        animalTop = new Animal(map,new Vector2d(1,1001), MapDirection.NORTH);
        animalLeft = new Animal(map,new Vector2d(-1001,1), MapDirection.WEST);
        animalBottom = new Animal(map,new Vector2d(1,-1001), MapDirection.SOUTH);
    }
}