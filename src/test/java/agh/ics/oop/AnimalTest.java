//package agh.ics.oop;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class AnimalTest {
//
//    private Animal animal22North;
//
//    private Animal animal1; // (2,2) - start
//    private Animal animal2; // (0,0) - start
//    private Animal animal3; // (1,1) - start
//    private Animal animal4; // (4,2) - start
//    private String res1;
//    private String res2;
//    private String res3;
//    private String res4;
//
//    private IWorldMap map;
//
////    @Test
////    void isInside() {
////        assertTrue(animal1.isInside(0,0));
////        assertTrue(animal1.isInside(1,1));
////        assertTrue(animal1.isInside(2,2));
////        assertTrue(animal1.isInside(4,2));
////        assertTrue(animal1.isInside(1,3));
////        assertTrue(animal1.isInside(2,4));
////
////        assertFalse(animal1.isInside(-1,0));
////        assertFalse(animal1.isInside(-2,2));
////        assertFalse(animal1.isInside(5,5));
////        assertFalse(animal1.isInside(1000,1));
////        assertFalse(animal1.isInside(-1000,2));
////        assertFalse(animal1.isInside(42,0));
////    }
//
//    @Test
//    void orientationTest() {
//        animal22North.move(MoveDirection.FORWARD);
//        assertEquals(MapDirection.NORTH, animal22North.getOrientation());
//
//        animal22North.move(MoveDirection.RIGHT);
//        assertEquals(MapDirection.EAST, animal22North.getOrientation());
//
//        animal22North.move(MoveDirection.FORWARD);
//        assertEquals(MapDirection.EAST, animal22North.getOrientation());
//
//        animal22North.move(MoveDirection.RIGHT);
//        assertEquals(MapDirection.SOUTH, animal22North.getOrientation());
//
//        animal22North.move(MoveDirection.FORWARD);
//        assertEquals(MapDirection.SOUTH, animal22North.getOrientation());
//
//        animal22North.move(MoveDirection.LEFT);
//        assertEquals(MapDirection.EAST, animal22North.getOrientation());
//
//        animal22North.move(MoveDirection.FORWARD);
//        assertEquals(MapDirection.EAST, animal22North.getOrientation());
//
//        animal22North.move(MoveDirection.BACKWARD);
//        assertEquals(MapDirection.EAST, animal22North.getOrientation());
//
//        animal22North.move(MoveDirection.BACKWARD);
//        assertEquals(MapDirection.EAST, animal22North.getOrientation());
//
//        animal22North.move(MoveDirection.LEFT);
//        assertEquals(MapDirection.NORTH, animal22North.getOrientation());
//
//        animal22North.move(MoveDirection.LEFT);
//        assertEquals(MapDirection.WEST, animal22North.getOrientation());
//    }
//
//    @Test
//    void positionTest() {
//        animal22North.move(MoveDirection.FORWARD);
//        assertEquals(new Vector2d(2,3), animal22North.getPosition());
//
//        animal22North.move(MoveDirection.RIGHT);
//        assertEquals(new Vector2d(2,3), animal22North.getPosition());
//
//        animal22North.move(MoveDirection.FORWARD);
//        assertEquals(new Vector2d(3,3), animal22North.getPosition());
//
//        animal22North.move(MoveDirection.RIGHT);
//        assertEquals(new Vector2d(3,3), animal22North.getPosition());
//
//        animal22North.move(MoveDirection.FORWARD);
//        assertEquals(new Vector2d(3,2), animal22North.getPosition());
//
//        animal22North.move(MoveDirection.LEFT);
//        assertEquals(new Vector2d(3,2), animal22North.getPosition());
//
//        animal22North.move(MoveDirection.FORWARD);
//        assertEquals(new Vector2d(4,2), animal22North.getPosition());
//
//        animal22North.move(MoveDirection.BACKWARD);
//        assertEquals(new Vector2d(3,2), animal22North.getPosition());
//
//        animal22North.move(MoveDirection.BACKWARD);
//        assertEquals(new Vector2d(2,2), animal22North.getPosition());
//
//        animal22North.move(MoveDirection.LEFT);
//        assertEquals(new Vector2d(2,2), animal22North.getPosition());
//
//        animal22North.move(MoveDirection.LEFT);
//        assertEquals(new Vector2d(2,2), animal22North.getPosition());
//    }
//
//    @Test
//    void borderTest(){
//        animal22North.move(MoveDirection.RIGHT);
//        animal22North.move(MoveDirection.FORWARD);
//        animal22North.move(MoveDirection.FORWARD);
//        animal22North.move(MoveDirection.FORWARD);
//        assertEquals(new Vector2d(4,2), animal22North.getPosition());
//
//        animal22North.move(MoveDirection.LEFT);
//        animal22North.move(MoveDirection.FORWARD);
//        animal22North.move(MoveDirection.FORWARD);
//        animal22North.move(MoveDirection.FORWARD);
//        assertEquals(new Vector2d(4,4), animal22North.getPosition());
//
//        animal22North.move(MoveDirection.LEFT);
//        animal22North.move(MoveDirection.FORWARD);
//        animal22North.move(MoveDirection.FORWARD);
//        animal22North.move(MoveDirection.FORWARD);
//        animal22North.move(MoveDirection.FORWARD);
//        animal22North.move(MoveDirection.FORWARD);
//        assertEquals(new Vector2d(0,4), animal22North.getPosition());
//
//        animal22North.move(MoveDirection.LEFT);
//        animal22North.move(MoveDirection.FORWARD);
//        animal22North.move(MoveDirection.FORWARD);
//        animal22North.move(MoveDirection.FORWARD);
//        animal22North.move(MoveDirection.FORWARD);
//        animal22North.move(MoveDirection.FORWARD);
//        assertEquals(new Vector2d(0,0), animal22North.getPosition());
//    }
//
//    @BeforeEach
//    void setUp() {
//
//        map = new RectangularMap(5,5);
//        animal22North = new Animal(map, new Vector2d(2,2),MapDirection.NORTH);
//
//        animal1 = new Animal(map, new Vector2d(2,2));
//        animal2 = new Animal(map,new Vector2d(0,0),MapDirection.SOUTH);
//        animal3 = new Animal(map,new Vector2d(1,1),MapDirection.WEST);
//        animal4 = new Animal(map,new Vector2d(4,2),MapDirection.EAST);
//
//        res1 = "Animal{orientation=Polnoc, position=(2,2)}";
//        res2 = "Animal{orientation=Poludnie, position=(0,0)}";
//        res3 = "Animal{orientation=Zachod, position=(1,1)}";
//        res4 = "Animal{orientation=Wschod, position=(4,2)}";
//    }
//}