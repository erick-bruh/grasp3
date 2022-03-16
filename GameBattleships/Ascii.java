public class Ascii extends GameComponent{
    

    
    //LINE FUNCTIONS FOR PRINTING LINES OF CHARACTER
    public static void PrintHorBar(int length, String s){
        for(int l = length; l > 1; l--){
            System.out.print(s);
        }
    }

    public static void PrintHorBarN(int length, String s){
        for(int l = length; l > 1; l--){
            System.out.print(s);
        }
        System.out.println();
    }

    //ANIMATED LINE FUNCTIONS
    public static void APrintHorBar(int length, String s, int ms){
        for(int l = length; l > 1; l--){
            System.out.print(s);
            try{
                Thread.sleep(ms);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void APrintHorBarN(int length, String s, int ms){
        for(int l = length; l > 1; l--){
            System.out.print(s);
            try{
                Thread.sleep(ms);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    //ASCII ART TRANSLATED TO PRINT
    public static void AsciiPrintShip1(int offsetX){
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("                                  )___(");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("                           _______/__/_");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("                  ___     /===========|   ___");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println(" ____       __   [@@@]___/____________|__[@@@]   __");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println(" \\   \\_____[@@]__/___________________________\\__[//]___");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("  \\                                                    |");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("   \\                                                   /");
    }

    public static void Title(int offsetX){
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println(" ____    ____  ______  ______  _        ___  _____ __ __  ____  ____");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("|    \\  /    ||      ||      || |      /  _]/ ___/|  |  ||    ||    \\");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("|  o  )|  o  ||      ||      || |     /  [_(   \\_ |  |  | |  | |  o  )");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("|     ||     ||_|  |_||_|  |_|| |___ |    _]\\__  ||  _  | |  | |   _/");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("|  O  ||  _  |  |  |    |  |  |     ||   [_ /  \\ ||  |  | |  | |  |");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("|     ||  |  |  |  |    |  |  |     ||     |\\    ||  |  | |  | |  |");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("|_____||__|__|  |__|    |__|  |_____||_____| \\___||__|__||____||__|");
    }

    public static void Trollface(int offsetX){
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("░░░░░▄▄▄▄▀▀▀▀▀▀▀▀▄▄▄▄▄▄░░░░░░░");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("░░░░░█░░░░▒▒▒▒▒▒▒▒▒▒▒▒░░▀▀▄░░░░");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("░░░░█░░░▒▒▒▒▒▒░░░░░░░░▒▒▒░░█░░░");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("░░░█░░░░░░▄██▀▄▄░░░░░▄▄▄░░░░█░░");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("░▄▀▒▄▄▄▒░█▀▀▀▀▄▄█░░░██▄▄█░░░░█░");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("█░▒█▒▄░▀▄▄▄▀░░░░░░░░█░░░▒▒▒▒▒░█");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("█░▒█░█▀▄▄░░░░░█▀░░░░▀▄░░▄▀▀▀▄▒█");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("░█░▀▄░█▄░█▀▄▄░▀░▀▀░▄▄▀░░░░█░░█░");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("░░█░░░▀▄▀█▄▄░█▀▀▀▄▄▄▄▀▀█▀██░█░░");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("░░░█░░░░██░░▀█▄▄▄█▄▄█▄████░█░░░");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("░░░░█░░░░▀▀▄░█░░░█░█▀██████░█░░");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("░░░░░▀▄░░░░░▀▀▄▄▄█▄█▄█▄█▄▀░░█░░");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("░░░░░░░▀▄▄░▒▒▒▒░░░░░░░░░░▒░░░█░");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("░░░░░░░░░░▀▀▄▄░▒▒▒▒▒▒▒▒▒▒░░░░█░");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("░░░░░░░░░░░░░░▀▄▄▄▄▄░░░░░░░░█░░");
    }

    public static void ShipExplosion(int offsetX){
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("                                    ____");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("                            __,-~~/~    `---.");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("                          _/_,---(      ,    )");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("                      __ /        <    /   )  \\___");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("         - ------===;;;'====------------------===;;;===----- -  -");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("                         \\/  ~\"~\"~\"~\"~\"~\\~\"~)~\"/");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("                         (_ (   \\  (     >    \\)");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("                           \\_( _ <         >_>'");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("                              ~ `-i' ::>|--\"");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("                                I;|.|.|");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("                                <|i::|i|`.");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("                               (` ^'\"`-' \")                           /\\");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("                ____`-' ||___-{]_| _[}-  |     |_[___\\==--            \\/   _");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println(" __..._____--==/___]_|__|_____________________________[___\\==--____,------' .7");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("|                                                                     BB-61/");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println(" \\_________________________________________________________________________|");
    }
    
    public static void GameOver(int offsetX){
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("   _____          __  __ ______    ______      ________ _____  ");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("  / ____|   /\\   |  \\/  |  ____|  / __ \\ \\    / /  ____|  __ \\ ");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println(" | |  __   /  \\  | \\  / | |__    | |  | \\ \\  / /| |__  | |__) |");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println(" | | |_ | / /\\ \\ | |\\/| |  __|   | |  | |\\ \\/ / |  __| |  _  / ");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println(" | |__| |/ ____ \\| |  | | |____  | |__| | \\  /  | |____| | \\ \\ ");
        for(int i = offsetX; i >1; i--)System.out.print(" ");
        System.out.println("  \\_____/_/    \\_\\_|  |_|______|  \\____/   \\/   |______|_|  \\_\\");
    }
}