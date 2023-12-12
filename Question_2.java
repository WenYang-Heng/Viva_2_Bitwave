import java.util.Scanner;
public class Question_2 {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        //receive sentence input from user
        System.out.print("Enter a sentence: ");
        String sentence = sc.nextLine();
        //close the input
        sc.close();
        //call removeSpecialCharacters() method to remove special character
        String resultNoSpecial = removeSpecialCharacters(sentence);
        //use toLowerCase() method to convert all letter to lower case
        String lowerSentence = resultNoSpecial.toLowerCase();
        //call isPalindrome() method to check whether user input is a Palindrome
        boolean result = isPalindrome(lowerSentence);
        //Display message indicating their input is Palindrome or not
        System.out.println("The sentence entered is " + (result? "a Palindrome.":"not a Palindrome."));
    }

    public static String removeSpecialCharacters(String sentence){
        //this method removes all special characters in user input and return it
        return sentence.replaceAll("[^0-9a-zA-Z]","");
    }

    public static boolean isPalindrome(String string){
        int length = string.length();
        //check whether sentence input is a palindrome by looping from the first character
        for(int i = 0; i < length/2 ; i++){
            if(string.charAt(i) != string.charAt(length-i-1)) {
                return false;
            }
        }
        return true;
    }
}
