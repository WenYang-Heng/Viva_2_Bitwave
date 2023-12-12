import java.util.ArrayList;
import java.util.Scanner;

public class Question_3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int min, max;
        do {
            System.out.printf("Enter the lower limit of the range: ");
            if(input.hasNextInt()){
                min = input.nextInt();
                if(min >= 0){
                    break;
                }
                else{
                    System.out.println("Please enter a number that is not negative.");
                }
            }
            else{
                System.out.println("Please enter an integer.");
                input.next();
            }
        }while(true);

        do {
            System.out.printf("Enter the upper limit of the range: ");
            if(input.hasNextInt()){
                max = input.nextInt();
                if(max > min){
                    break;
                }
                else{
                    System.out.println("Upper bound needs to be greater than lower bound.");
                }
            }
            else{
                System.out.println("Please enter a valid integer.");
                input.next();
            }
        }while(true);

        input.close();

        int[] list = generatePrimes(min, max);
        System.out.printf("The prime numbers within the range[%d, %d] are: ", min, max);
        displayPrimeNumbers(list);
    }

    public static void displayPrimeNumbers(int[] list){
        if(list[0] == 0){
            System.out.println("No prime numbers within this range.");
            return;
        }
        for(int i = 0; i < list.length && list[i] != 0; i++){
            System.out.printf("%d ", list[i]);
        }
    }

    public static int[] generatePrimes(int min, int max){
        int [] primeList = new int[max - min];
        for(int i = min, j = 0; i <= max; i++){
            if(isPrime(i))
                primeList[j++] = i;
        }
        return primeList;
    }

    public static boolean isPrime(int n){
        if(n < 2 || (n > 2 && n % 2 == 0)) return false;
        for(int i = 3; i < n; i += 2){
            if(n % i == 0) return false;
        }
        return true;
    }
}
