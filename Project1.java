import java.util.*;

public class Project1 {

    static String[][] bord = {{"1","2","3"},{"4","5","6"},{"7","8","9"}};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int number_of_round,player1_tile,player2_tile,game_mode,p1_score=0,p2_score=0;
        ArrayList<Integer> invalid_random_val = new ArrayList<>();

        System.out.println("Welcoming\nto the game");

        try {
            System.out.println("Press 1 for one player\nPress 2 for two players");
            game_mode = scanner.nextInt();

            System.out.print("How many rounds: ");
            number_of_round = scanner.nextInt();

            do {
                //check if player one win
                if (p1_score > p2_score && p1_score + p2_score == number_of_round){
                    System.out.println("player one win");
                    break;
                }

                //check if player two win
                if (p2_score > p1_score && p1_score + p2_score == number_of_round){
                    System.out.println("\nplayer two win");
                    break;
                }

                //check for drw
                if (p1_score == p2_score && p1_score + p2_score == number_of_round){
                    System.out.println("\nDrw");
                    break;
                }

                System.out.println("\n");
                display();

                System.out.println("Player one turn");
                try {
                    System.out.print("Chose a tile: ");
                    player1_tile = scanner.nextInt();

                    invalid_random_val.add(player1_tile);

                    switch (player1_tile) {
                        case 1:
                            play(0, 0, "o");
                            break;
                        case 2:
                            play(0, 1, "o");
                            break;
                        case 3:
                            play(0, 2, "o");
                            break;
                        case 4:
                            play(1, 0, "o");
                            break;
                        case 5:
                            play(1, 1, "o");
                            break;
                        case 6:
                            play(1, 2, "o");
                            break;
                        case 7:
                            play(2, 0, "o");
                            break;
                        case 8:
                            play(2, 1, "o");
                            break;
                        case 9:
                            play(2, 2, "o");
                            break;
                    }

                    //check if player one win a round
                    if (game_status().equals("player one win")) {
                        p1_score++;
                        reset_bord();
                        continue;
                    }

                    //check for drw round
                    if (is_bord_full() && p1_score + p2_score != number_of_round){
                        p1_score++;
                        p2_score++;
                        reset_bord();
                        continue;
                    }

                    System.out.println("Player two");

                    //game mod two
                    if(game_mode == 2) {
                        System.out.print("Chose a tile: ");
                        player2_tile = scanner.nextInt();
                        invalid_random_val.add(player2_tile);
                    }
                    //game mode one
                    else {
                        int stop_condition = 0;
                        do {
                            player2_tile = random.nextInt(1, 9);
                            //if the value is not present in the
                            if (!invalid_random_val.contains(player2_tile))
                                stop_condition++;
                        } while (stop_condition == 0);
                        invalid_random_val.add(player2_tile);
                        System.out.println("Chose a tile: "+player2_tile);
                    }

                    switch (player2_tile) {
                        case 1:
                            play(0, 0, "x");
                            break;
                        case 2:
                            play(0, 1, "x");
                            break;
                        case 3:
                            play(0, 2, "x");
                            break;
                        case 4:
                            play(1, 0, "x");
                            break;
                        case 5:
                            play(1, 1, "x");
                            break;
                        case 6:
                            play(1, 2, "x");
                            break;
                        case 7:
                            play(2, 0, "x");
                            break;
                        case 8:
                            play(2, 1, "x");
                            break;
                        case 9:
                            play(2, 2, "x");
                            break;
                    }

                    //check if player two win a round
                    if (game_status().equals("player two win")) {
                        p2_score++;
                        reset_bord();
                    }

                } catch (InputMismatchException e) {
                    System.out.println("The value is wrong\n have a nice day");
                    break;
                }
            } while (true);
        }catch (InputMismatchException e) {
            System.out.println("Wrong input value");
        }
    }
    public static boolean is_position_valid (int row, int colum){
        boolean result = true;
        try {
            //check if the tile has a number
            for (int x = 0; x < bord.length; x++) {
                for (int y = 0; y < bord[x].length; y++) {
                    if (bord[row][colum].equals(bord[x][y])) {
                        if (!isNumeric(bord[x][y]))
                            result = false;
                        break;
                    }
                }
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println("Invalid row or colum value");
        }
        return result;
    }

    public static void display (){
        for (String[] x : bord) {
            for (String val : x) {
                System.out.print(val);
            }
            System.out.println();
        }
//        System.out.println();
    }

    public static void play(int row, int colum, String player){
        try {
            if (is_position_valid(row, colum)){
                for(int x=0; x < bord.length; x++){
                    for(int y=0; y < bord[x].length; y++){
                        if(row == x && colum == y) {
                            bord[x][y] = player;
                            break;
                        }
                    }
                }
                display();
            }else {
                System.out.println("Invalid move");
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println("Invalid row or colum value");
        }
    }

    public static boolean is_bord_full(){
        boolean result = true;
        for (String[] x: bord){
            for (String val: x){
                if (isNumeric(val)){
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    public static String game_status(){
        if (check_for_winner("o"))
            return "player one win";
        else if (check_for_winner("x"))
            return "player two win";
        return "Drw";
    }

    public static boolean check_for_winner(String p){

        return (bord[0][0].equals(p) && bord[0][1].equals(p) && bord[0][2].equals(p)) ||
                (bord[1][0].equals(p) && bord[1][1].equals(p) && bord[1][2].equals(p)) ||
                (bord[2][0].equals(p) && bord[2][1].equals(p) && bord[2][2].equals(p)) ||

                (bord[0][0].equals(p) && bord[1][0].equals(p) && bord[2][0].equals(p)) ||
                (bord[0][1].equals(p) && bord[1][1].equals(p) && bord[2][1].equals(p)) ||
                (bord[0][2].equals(p) && bord[1][2].equals(p) && bord[2][2].equals(p)) ||

                (bord[0][0].equals(p) && bord[1][1].equals(p) && bord[2][2].equals(p)) ||
                (bord[0][2].equals(p) && bord[1][1].equals(p) && bord[2][0].equals(p));
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static void reset_bord(){
        int counter = 1;
        for (int i=0; i < bord.length; i++){
            for (int j=0; j < bord[i].length; j++){
                bord[i][j] = Integer.toString(counter);
                counter++;
            }
        }
    }
}
