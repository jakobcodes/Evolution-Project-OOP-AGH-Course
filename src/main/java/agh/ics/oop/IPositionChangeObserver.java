package agh.ics.oop;

public interface IPositionChangeObserver<E>{
    void positionChanged(Vector2d oldPosition, Vector2d newPosition, E o);
}
