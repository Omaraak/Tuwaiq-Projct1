import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {

    static ArrayList<Integer> numbers = new ArrayList<>(), history = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int menu_choice;

        System.out.println(
                """
                        \nEnter -1 to quit
                        
                        Enter 0 to enter the numbers

                        Enter 1 to addition the numbers

                        Enter 2 to subtraction the numbers

                        Enter 3 to multiplication the numbers

                        Enter 4 to division the numbers

                        Enter 5 to modulus the numbers

                        Enter 6 to find minimum number

                        Enter 7 to find maximum number

                        Enter 8 to find the average of numbers

                        Enter 9 to print the last result in calculator

                        Enter 10 to print the list of all results in calculator
                        """
        );

        try {
            do {
                System.out.println("Enter choice: ");
                menu_choice = scanner.nextInt();

                switch (menu_choice) {
                    case 0:
                        addNumbers();
                        break;
                    case 1:
                        if (!numbers.isEmpty())
                            System.out.println("addition: "+addition());
                        else
                            System.out.println("Can not do this operation");
                        break;
                    case 2:
                        if (!numbers.isEmpty())
                            System.out.println("subtraction: "+subtraction());
                        else
                            System.out.println("Can not do this operation");
                        break;
                    case 3:
                        if (!numbers.isEmpty())
                            System.out.println("multiplication: "+multiplication());
                        else
                            System.out.println("Can not do this operation");
                        break;
                    case 4:
                        if (numbers.size() == 2)
                            System.out.println("division: "+division());
                        else
                            System.out.println("It is only possible with two numbers");
                        break;
                    case 5:
                        if (numbers.size() == 2)
                            System.out.println("modulus: "+modulus());
                        else
                            System.out.println("It is only possible with two numbers");
                        break;
                    case 6:
                        if (!numbers.isEmpty())
                            System.out.println("the min: "+minimum());
                        else
                            System.out.println("Can not do this operation");
                        break;
                    case 7:
                        if (!numbers.isEmpty())
                            System.out.println("the max: "+maximum());
                        else
                            System.out.println("Can not do this operation");
                        break;
                    case 8:
                        if (!numbers.isEmpty())
                            System.out.println("the average: "+average());
                        else
                            System.out.println("Can not do this operation");
                        break;
                    case 9:
                        if (!history.isEmpty())
                            System.out.println("the last result is: "+history.getLast());
                        else
                            System.out.println("Can not do this operation");
                        break;
                    case 10:
                        if (!history.isEmpty())
                            System.out.println("All the result: "+history);
                        else
                            System.out.println("Can not do this operation");
                        break;
                    default:
                        if (menu_choice != -1)
                            System.out.println("try again");
                        break;
                }
            } while (menu_choice != -1);
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }
    }

    public static void addNumbers(){
        int enter_number;
        try {
            do {
                System.out.println("Enter a number or -1 to stop");
                enter_number = scanner.nextInt();
                if (enter_number != -1)
                    numbers.add(enter_number);
            } while (enter_number != -1);
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }
    }

    public static int addition(){
        int result = 0;
        for (int i: numbers){
            result+=i;
        }
        System.out.println(numbers);
        history.add(result);
        return result;
    }

    public static int subtraction(){
        int result = numbers.getFirst();
        for (int i: numbers){
            if (numbers.getFirst() == i)
                continue;
            result-=i;
        }
        System.out.println(numbers);
        history.add(result);
        return result;
    }

    public static int multiplication(){
        int result = 1;
        for (int i: numbers){
            result*=i;
        }
        System.out.println(numbers);
        history.add(result);
        return result;
    }

    public static int division(){
        int result= numbers.getFirst();
        try {
            result/=numbers.getLast();
        }catch (ArithmeticException e){
            System.out.println("Can't divide by zero");
            return -1;
        }
        System.out.println(numbers);
        history.add(result);
        return result;
    }

    public static int modulus(){
        int result= numbers.getFirst();
        try {
            result%=numbers.getLast();
        }catch (ArithmeticException e){
            System.out.println(e.getMessage());
        }
        System.out.println(numbers);
        history.add(result);
        return result;
    }

    public static int minimum(){
        int min=numbers.getFirst();
        for (int i: numbers){
            if (i < min)
                min=i;
        }
        System.out.println(numbers);
        history.add(min);
        return min;
    }

    public static int maximum(){
        int max=numbers.getFirst();
        for (int i: numbers){
            if (i > max)
                max=i;
        }
        System.out.println(numbers);
        history.add(max);
        return max;
    }

    public static int average(){
        int result = addition();
        try {
            result /= numbers.size();
        }catch (ArithmeticException e){
            System.out.println(e.getMessage());
        }
        System.out.println(numbers);
        history.add(result);
        return result;
    }
}
