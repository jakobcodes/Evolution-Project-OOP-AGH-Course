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
    void placeGrass() {
        List<Vector2d> grassPositions = new ArrayList<>();
        Vector2d leftBottomGrassCorner = new Vector2d(0,0);
        Vector2d rightTopGrassCorner = new Vector2d(map.getGrassBorder(), map.getGrassBorder());

        for (Grass grass: map.getGrasses()){
            assertFalse(grassPositions.contains(grass.getPosition()));
            assertTrue(grass.getPosition().follows(leftBottomGrassCorner));
            assertTrue(grass.getPosition().precedes(rightTopGrassCorner));
            grassPositions.add(grass.getPosition());
        }
        assertEquals(map.getGrassQuantity(), grassPositions.size());
    }

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
        assertFalse(map.place(new Animal(map, new Vector2d(2,2))));
        assertFalse(map.place(new Animal(map, new Vector2d(3,4))));

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
        for (Grass grass: map.getGrasses()){
            leftBottom = leftBottom.lowerLeft(grass.getPosition());
            rightTop = rightTop.upperRight(grass.getPosition());
        }

        assertEquals(leftBottom, map.leftBottomCorner);
        assertEquals(rightTop, map.rightTopCorner);

        map.place(new Animal(map,new Vector2d(1000,1000)));
        assertEquals(new Vector2d(1000,1000), map.rightTopCorner);

        map.place(new Animal(map,new Vector2d(-1000,-1000)));
        assertEquals(new Vector2d(-1000,-1000), map.leftBottomCorner);

        map.place(animalRight);
        assertEquals(new Vector2d(1001,1000), map.rightTopCorner);
        animalRight.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(1002,1000), map.rightTopCorner);

        map.place(animalTop);
        assertEquals(new Vector2d(1002,1001), map.rightTopCorner);
        animalTop.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(1002,1002), map.rightTopCorner);

        map.place(animalLeft);
        assertEquals(new Vector2d(-1001,-1000), map.leftBottomCorner);
        animalLeft.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(-1002,-1000), map.leftBottomCorner);

        map.place(animalBottom);
        assertEquals(new Vector2d(-1002,-1001), map.leftBottomCorner);
        animalBottom.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(-1002,-1002), map.leftBottomCorner);
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