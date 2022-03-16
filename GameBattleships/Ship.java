public class Ship extends Player{
    //FOR THE ROOT OF THE SHIP
    int xPos;
    int yPos;
    
    //DEFINES THE ORIENTATION OF THE SHIP IN CLOCKWISE DIRECTION ROTATION
    // 0=NORTH 1=EAST 2=SOUTH 3=WEST
    int Orientation;

    //DEFINES THE LENGTH OF THE SHIP
    int Size;

    //DEATH
    boolean shipDown = false;

    Ship(){
    }

    Ship(int xPos1, int yPos1, int Orientation1, int Size1){
        xPos = xPos1;
        yPos = yPos1;
        Orientation = Orientation1;
        Size = Size1;
    }
}