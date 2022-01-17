package agh.ics.oop;

public enum MapDirection {
    NORTH,  // kolejność ma znaczenie
    SOUTH,
    EAST,
    WEST,
    NORTH_WEST,
    NORTH_EAST,
    SOUTH_WEST,
    SOUTH_EAST;

    public String toString(){
        return switch (this) {
            case NORTH -> "N";
            case SOUTH -> "S";
            case EAST -> "E";
            case WEST -> "W";
            case NORTH_EAST -> "NE";
            case NORTH_WEST -> "NW";
            case SOUTH_EAST -> "SE";
            case SOUTH_WEST -> "SW";
        };
    }

    public MapDirection next(){
        return switch (this){
            case NORTH -> NORTH_EAST;
            case NORTH_EAST -> EAST;
            case EAST -> SOUTH_EAST;
            case SOUTH_EAST -> SOUTH;
            case SOUTH -> SOUTH_WEST;
            case SOUTH_WEST -> WEST;
            case WEST -> NORTH_WEST;
            case NORTH_WEST -> NORTH;
        };
    }

//    public MapDirection previous(){
//        return switch (this) {
//            case NORTH -> WEST;
//            case WEST -> SOUTH;
//            case SOUTH -> EAST;
//            case EAST -> NORTH;
//        };
//    }

    public Vector2d toUnitVector(){
        return switch (this){
            case NORTH -> new Vector2d(0,1);    // nowy wektor co wywołanie
            case SOUTH -> new Vector2d(0,-1);
            case EAST -> new Vector2d(1,0);
            case WEST -> new Vector2d(-1,0);
            case NORTH_EAST -> new Vector2d(1,1);
            case NORTH_WEST -> new Vector2d(-1, 1);
            case SOUTH_EAST -> new Vector2d(1, -1);
            case SOUTH_WEST -> new Vector2d(-1, -1);
        };
    }

}