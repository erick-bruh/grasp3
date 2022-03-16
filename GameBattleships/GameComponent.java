import java.util.Scanner;

class GameComponent{


    //PUBLIC VARIABLES FOR AI
    public static int AilastShotX = -1;
    public static int AilastShotY = -1;
    public static int AiOrientation = -4;
    
    public static void main(String[] args) {
        
        //INITIALISATION
        Player player1 = new Player(10, 10);
        Player player2 = new Player(10, 10);
        

        //USER INTERFACE
        MainMenuSeq(2);

        //GAMEMODE PROMPT
        while(true){
        String input = "0";
        while(Integer.parseInt(input) != 1 && Integer.parseInt(input) != 2){
        System.out.print("Gamemode:");
        input = GetInput();
        }

        //SETUP/INSTRUCTIONS
        if(Integer.parseInt(input) == 1){
            input = "0";
            PlaceRandom(player1, 1, 2 ,3 ,4);
            PlaceRandom(player2, 1, 2 ,3 ,4);
            while(true){
            while(Integer.parseInt(input) != 1 && Integer.parseInt(input) != 2){
                Ascii.APrintHorBarN(100, "@", 2);
                System.out.println("@ Below is your generated board                                                                   @");
                System.out.println("@ If you're satisfied with the generation, press 1, otherwise press 2 to reshuffle                @");
                Ascii.APrintHorBarN(100, "@", 2);
                PrintDeckShips(player1);
                input = GetInput();
            }
                
                if(Integer.parseInt(input) == 1){
                    Ascii.APrintHorBarN(100, "@", 2);
                    System.out.println("@ Hello, you can play this game. Type in the coordinates and shoot the computer opponent          @");
                    System.out.println("@ X being the horizontal coordinate, Y being the vertical both from 1-10                          @");
                    System.out.println("@ Undiscovered areas marked \"■\", shot areas marked \"X\", shot areas with a ship marked \"0\"         @");
                    Ascii.APrintHorBarN(100, "@", 2);
                    System.out.println("¤    Your Board                                             opponent's board                      ¤");
                    PrintDeckShips(player1, player2);
                    break;
                }
                else if(Integer.parseInt(input) == 2){
                    PlaceRandom(player1, 1, 2 ,3 ,4);
                    input = "0";
                }
            }
            break;
        }
            else if(Integer.parseInt(input) == 2){
            Ascii.Trollface(1);
            System.out.println("We didn't manage multiplayer");
            System.out.println();
            }
        }

        //MAIN GAMEPLAY
        while(PlayerAlive(player1) == true && PlayerAlive(player2) == true){
            String inputX = "0";
            String inputY = "0";
            while(Integer.parseInt(inputX) <= 0 || Integer.parseInt(inputX) > 10 || Integer.parseInt(inputY) <= 0 || Integer.parseInt(inputY) > 10){
                System.out.print("Shoot X:");
                inputX = GetInput();
                System.out.print("Y:");
                inputY = GetInput();
            }
            player2.matrix.grid[Integer.parseInt(inputX)-1][Integer.parseInt(inputY)-1] = 1;
            System.out.println("¤    Your Board                                             opponent's board                      ¤");
            AiMove(player1);
            PrintDeckShips(player1, player2);
            DeadShips(player1);
            DeadShips(player2);
        }

        //WIN LOSE
        if(PlayerAlive(player1) == false){
            Ascii.GameOver(20);
            Ascii.APrintHorBarN(100, "@", 2);
            System.out.println("@ Oh my!! looks like you've lost!!!                                                               @");
            System.out.println("@ Likely due to testing purposes since its really hard to lose against this AI so not too bad     @");
            Ascii.APrintHorBarN(100, "@", 2);
            Ascii.ShipExplosion(10);
        }
        else if(PlayerAlive(player2) == false){
            System.out.println("you win!");
        }

        Ascii.APrintHorBarN(100, "@", 2);
        System.out.println("@ Thank you for playing                                                                           @");
        System.out.println("@ 5 from AP cs exam incoming                                                                      @");
        Ascii.APrintHorBarN(100, "@", 2);
        
    }

    //---GAMEPLAY---//

    //CHECKS IF ALL SHIPS OF PLAYER ARE DEAD
    public static boolean PlayerAlive(Player player){
        int i = 0;
        int s = 0;
        while(i < player.ships.length){
            if(player.ships[i].shipDown == true){
            s++;
            }
            i++;
        }
        if(i == s)return false;
        return true;
    }
    
    //RANDOM PLACEMENT
    public static void PlaceRandom(Player player, int of5Ships, int of4Ships, int of3Ships, int of2Ships){
        int i = 0;
        int shipSize = 5;
        while(i < of5Ships + of4Ships + of3Ships + of2Ships){

            //CHANGES THE SIZE OF THE SHIP BEING MADE; FIRST IT PLACES SHIPS SIZE 5, THEN SHIPS SIZE 4 ETC.
            if(i < of5Ships)
            shipSize = 5;
            else if(i < of5Ships + of4Ships)
            shipSize = 4;
            else if(i < of5Ships + of4Ships + of3Ships)
            shipSize = 3;
            else if(i < of5Ships + of4Ships + of3Ships + of2Ships)
            shipSize = 2;

            //CREATES A NEW SHIP; POSITION+ORIENTATION ARE RANDOMISED
            player.ships[i] = new Ship(
            (int)(Math.random()*(player.matrix.grid.length)),
            (int)(Math.random()*(player.matrix.grid.length)),
            (int) (Math.random()*4),
            shipSize);

            //CHECKS IF THE NEW SHIP IS OUT OF BOUNDS OR PENETRATING ANOTHER SHIP, IF YES, IT RECREATES THE SAME SHIP
            if(OutOfBounds(player.ships[i], player.matrix.grid) == false){
                if(i < 1){
                    i++;
                }
                else{
                    //GOES THROUGH ALL OF THE PREVIOUSLY PLACED SHIPS AND CHECKS FOR PENETRATING
                    int CheckedPastShips = 0;
                    while(CheckedPastShips < i && Penetration(player.ships[i], player.ships[CheckedPastShips]) == false)
                    CheckedPastShips++;
                    if(CheckedPastShips == i)
                    i++;
                }
            }
        }
    }

    //AI
    public static void AiMove(Player player){
        int deltaX = 0;
        int deltaY = 0;
        int randomX = -1;
        int randomY = -1;

        //KNOWN SHIP MODE - IF SHIP WAS FOUND, CONTINUE SHOOTING IT DOWN
        if(AiOrientation >= 0){
                //DETERMINES THE DELTA X AND Y TO BE USED IN LATER FOR SHOOTING DOWN FOUND SHIP
                if(AiOrientation == 0){deltaY = -1;}
                else if(AiOrientation == 1){deltaX = 1;}
                else if(AiOrientation == 2){deltaY = 1;}
                else if(AiOrientation == 3){deltaX = -1;}
            //IF NOT OOB, SHOOT, IF OOB, PROGRESS TO NEXT POSSIBLE MODE (ALSO SHOT BEFORE)
            if((AilastShotX+deltaX) >= 0 && (AilastShotX+deltaX) <= 9 && (AilastShotY+deltaY) >= 0 && (AilastShotY+deltaY) <= 9){
                player.matrix.grid[AilastShotX+deltaX][AilastShotY+deltaY] = 1;
            }
        }
        //SHIP FOUND, UNCERTAIN ORIENTATION MODE - AI KNOWS WHERE A SHIP BLOCK IS, LOKS IN ALL DIRECTIONS TO FIND THE REST OF IT
        else if(AilastShotX != -1 && AilastShotY != -1){

            while(true){
            deltaX = 0;
            deltaY = 0;
            if(AiOrientation == -4){deltaY = -1;}
            else if(AiOrientation == -3){deltaX = 1;}
            else if(AiOrientation == -2){deltaY = 1;}
            else if(AiOrientation == -1){deltaX = -1;}
                //OOB PREVENTION
                if((AilastShotX+deltaX) < 0 || (AilastShotX+deltaX) > 9 || (AilastShotY+deltaY) < 0 || (AilastShotY+deltaY) > 9){
                    AiOrientation++;
                    if(AiOrientation >= 0){
                        AiOrientation=-4;
                    }
                }
                else break;
            }
            //IF NOT OOB, SHOOT, IF OOB, PROGRESS TO NEXT POSSIBLE MODE
            if(player.matrix.grid[AilastShotX+deltaX][AilastShotY+deltaY] == 0){
                player.matrix.grid[AilastShotX+deltaX][AilastShotY+deltaY] = 1;
            }
        }
        //NO INFO ON SHIPS MODE - RANDOMLY PICKING 
        else{
            while(true){
            randomX = (int)(Math.random()*(player.matrix.grid.length));
            randomY = (int)(Math.random()*(player.matrix.grid.length));
                if(player.matrix.grid[randomX][randomY] == 0){
                    player.matrix.grid[randomX][randomY] = 1;
                    break;
                }
            }
        }

        //AFTER SHOOTING
        //IF WE KNEW THERE WAS A SHIP; AND IF THERES A SHIP AGAIN, SHOOT IN THE SAME DIRECTION AS BEFORE; IF THERE IS NOT, LOOK TO DIFFERENT DIRECTION
        if(deltaX != 0 || deltaY != 0){
            if(ShipInPosition(player, AilastShotX+deltaX, AilastShotY+deltaY) != -1){
                AilastShotX = AilastShotX+deltaX;
                AilastShotY = AilastShotY+deltaY;
                if(AiOrientation < 0)
                AiOrientation = AiOrientation+4;
            }
            else if(AiOrientation <= -2){
                AiOrientation++;
            }
            else{
                AilastShotX = -1;
                AilastShotY = -1;
                AiOrientation = -4;
            }
        }
        else if(ShipInPosition(player, randomX, randomY) != -1){
            AilastShotX = randomX;
            AilastShotY = randomY;
            AiOrientation = -4;
        }
    }

    //---UI STUFF---//
    
    //WAIT FUNCTION
    public static void WaitMilliseconds(int ms){
        try{
            Thread.sleep(ms);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //GET USER INPUT
    public static String GetInput(){
        Scanner scanner = new Scanner(System.in);
        String input = (String)scanner.nextLine();
        return input;
    }

    //MAIN MENU SEQUENCE
    public static void MainMenuSeq(int ms){
        Ascii.APrintHorBarN(100, "@", ms);
        Ascii.APrintHorBar(38, "@", ms);
        System.out.print(" Erick & Matej software ");
        Ascii.APrintHorBarN(39, "@", ms);
        Ascii.APrintHorBarN(100, "@", ms);
        System.out.println();
        Ascii.AsciiPrintShip1(20);
        Ascii.APrintHorBarN(100, "~", (int)(ms/2));
        Ascii.Title(16);
        System.out.println();
        Ascii.APrintHorBarN(100, "@", ms);
        System.out.println("@ Hello, welcome to Battleship©                                                                   @");
        System.out.println("@ Would you like to play a game with a computer opponent(1) or with a real opponent(2)?           @");
        System.out.println("@                                           (type 1/2)                                            @");
        Ascii.APrintHorBarN(100, "@", ms);
    }

    //---UTILITY---//

    //PRINT DECK
    public static void PrintDeckShips(Player player){
        String block;
        String row = new String();
        //GOES THROUGH EACH ROW AND THROUGH EACH COLLUMN AND PRINTS CORRESPONDING SYMBOL
        for (int y = 0; y<player.matrix.grid.length; y++){
            for(int x = 0; x<player.matrix.grid.length; x++){
                    //IF SHOT, SEND TO BLOCK PRINTER
                    if(player.matrix.grid[x][y] == 1 && ShipInPosition(player, x, y) == -1){
                        block = "X ";
                    }
                    //LOOKS FOR A SHIP BLOCK; IF FOUND, SENDS THAT INTO THE BLOCK PRINTER
                    else if(ShipInPosition(player, x, y) != -1 && player.matrix.grid[x][y] != 1){
                        block = player.ships[ShipInPosition(player, x, y)].Size + " ";
                    }
                    else if(ShipInPosition(player, x, y) != -1 && player.matrix.grid[x][y] == 1){
                        block = "¤ ";
                    }
                    else{
                        block = "■ ";
                    }
            row = row + block;
            block = new String();
            }
            System.out.println(row);
            row = new String();
        }
    }

    //DUAL PRINT DECK
    public static void PrintDeckShips(Player player, Player oponent){
        String block;
        String row = new String();
        //GOES THROUGH EACH ROW AND THROUGH EACH COLLUMN AND PRINTS CORRESPONDING SYMBOL
        for (int y = 0; y<player.matrix.grid.length; y++){
            for(int x = 0; x<player.matrix.grid.length; x++){
                    //IF SHOT, SEND TO BLOCK PRINTER
                    if(player.matrix.grid[x][y] == 1 && ShipInPosition(player, x, y) == -1){
                        block = "X ";
                    }
                    //LOOKS FOR A SHIP BLOCK; IF FOUND, SENDS THAT INTO THE BLOCK PRINTER
                    else if(ShipInPosition(player, x, y) != -1 && player.matrix.grid[x][y] != 1){
                        block = player.ships[ShipInPosition(player, x, y)].Size + " ";
                    }
                    else if(ShipInPosition(player, x, y) != -1 && player.matrix.grid[x][y] == 1){
                        block = "¤ ";
                    }
                    else{
                        block = "■ ";
                    }
            row = row + block;
            block = new String();
            }

            row = row + "                                       ";

            for(int x = 0; x<oponent.matrix.grid.length; x++){
                //IF SHOT, SEND TO BLOCK PRINTER
                
                
                if(oponent.matrix.grid[x][y] == 1 && ShipInPosition(oponent, x, y) == -1){
                    block = "X ";
                }
                else if(ShipInPosition(oponent, x, y) != -1 && oponent.matrix.grid[x][y] == 1){
                    block = "¤ ";
                }
                else{
                    block = "■ ";
                }
                
            row = row + block;
            block = new String();
            }

            System.out.println(row);
            row = new String();
        }
    }
    //OUT OF BOUNDS CHECKER
    public static boolean OutOfBounds(Ship ship, int erej[][]){
        
        //ROOT OF SHIP OOB CHECK
        if(
            ship.xPos+1 > erej.length ||
            ship.yPos+1 > erej.length ||
            ship.xPos+1 < 1 ||
            ship.yPos+1 < 1
            ){
            return true;
        }
        //SHIP BODY OOB CHECK
        else if(
            ship.Orientation == 0 && ship.yPos-ship.Size+1 < 0 || //NORTH
            ship.Orientation == 1 && ship.xPos+ship.Size > erej.length || //EAST
            ship.Orientation == 2 && ship.yPos+ship.Size > erej.length || //SOUTH
            ship.Orientation == 3 && ship.xPos-ship.Size+1 < 0 //WEST
            ){
            return true;
        }
        return false;
    }
    
    //CHECK DEAD SHIPS 
    public static void DeadShips(Player player){
        int i = 0;
        while(i < player.ships.length){
            int ShipLives = player.ships[i].Size;
            for (int y = 0; y<player.matrix.grid.length; y++){
                for(int x = 0; x<player.matrix.grid.length; x++){
                    if(ShipInPosition(player.ships[i], x, y) == true && player.matrix.grid[x][y] == 1){
                        ShipLives--;
                    }
                }
                if(ShipLives == 0){
                    player.ships[i].shipDown = true;
                    break;
                }
            }
            i++;
        }
    }

    //CHECKS FOR SHIPS FOR GIVEN POSITION, RETURNS SHIP ID AS INT; OTHERWISE RETURNS -1
    public static int ShipInPosition(Player player, int x, int y){
    int i = 0;
    while(i < player.ships.length)
        if(
            player.ships[i].Orientation == 0 && x == player.ships[i].xPos && y <= player.ships[i].yPos && y > player.ships[i].yPos - player.ships[i].Size || //NORTH ORIENTED SHIPS
            player.ships[i].Orientation == 2 && x == player.ships[i].xPos && y >= player.ships[i].yPos && y < player.ships[i].yPos + player.ships[i].Size || //SOUTH ORIENTED SHIPS
            player.ships[i].Orientation == 1 && y == player.ships[i].yPos && x >= player.ships[i].xPos && x < player.ships[i].xPos + player.ships[i].Size || //EAST ORIENTED SHIPS
            player.ships[i].Orientation == 3 && y == player.ships[i].yPos && x <= player.ships[i].xPos && x > player.ships[i].xPos - player.ships[i].Size    //WEST ORIENTED SHIPS
        ){
            return i;
        }
        else{i++;}

        return -1;
    }

    //IF A SPECIFIC SHIP IS IN A GIVEN POSITION, RETURNS TRUE; OTHERWISE FALSE
    public static boolean ShipInPosition(Ship ship, int x , int y){
        if(
            ship.Orientation == 0 && x == ship.xPos && y <= ship.yPos && y > ship.yPos - ship.Size || //NORTH ORIENTED SHIPS
            ship.Orientation == 2 && x == ship.xPos && y >= ship.yPos && y < ship.yPos + ship.Size || //SOUTH ORIENTED SHIPS
            ship.Orientation == 1 && y == ship.yPos && x >= ship.xPos && x < ship.xPos + ship.Size || //EAST ORIENTED SHIPS
            ship.Orientation == 3 && y == ship.yPos && x <= ship.xPos && x > ship.xPos - ship.Size    //WEST ORIENTED SHIPS
        ){
            return true;
        }
        return false;
    }

    //CHECKING FOR OVERLAPING SHIPS
    public static boolean Penetration(Ship shipCollider, Ship shipObstructor) {
        int x = shipCollider.xPos;
        int y = shipCollider.yPos;
        int deltaX = 0;
        int deltaY = 0;
        //DETERMINES THE DELTA X AND Y TO BE USED IN LATER FOR LOOP FOR CYCLING THROUGH SHIP SPACES
        if(shipCollider.Orientation == 0){deltaY = -1;}
        else if(shipCollider.Orientation == 1){deltaX = 1;}
        else if(shipCollider.Orientation == 2){deltaY = 1;}
        else if(shipCollider.Orientation == 3){deltaX = -1;}

        //GOES THROUGH POSITIONS OF COLLIDER SHIP AND CHECKS FOR PENETRATION WITH OBSTRUCTOR SHIP
        for(int i = 0; i < shipCollider.Size; i++){
            if(ShipInPosition(shipObstructor, x, y) == true){
                return true;
            }
            x = x + deltaX;
            y = y + deltaY;
        }
        return false;
    }
}