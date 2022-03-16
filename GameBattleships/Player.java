public class Player extends GameComponent{
    //FIELDS
    Matrix matrix;
    
    //Ship ship1;
    Ship ships[];
    
    Player(){
    }
    
    Player(int GridSize){
        matrix = new Matrix(GridSize);
    }

    //CONSTRUCTOR WITH 1 SHIP MANUAL SETTING
    Player(int GridSize, int ofShips){
        matrix = new Matrix(GridSize);
        ships = new Ship[ofShips];
        //ship1 = new Ship(xPos1, yPos1, Orientation1, Size1);
    }
}