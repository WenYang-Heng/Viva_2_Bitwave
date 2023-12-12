import java.util.Arrays;
import java.util.Scanner;
public class Question_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of rows of matrices 1: ");
        int rows = scanner.nextInt();
        System.out.print("Enter the number of columns of matrices 1: ");
        int columns = scanner.nextInt();
        double[][] a = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print("Enter element of matrices 1 at position [" + (i+1) + "][" + (j+1) + "]: ");
                a[i][j] = scanner.nextInt();
            }
        }
        System.out.println("Matrices 1: ");
        for (double[] row:a) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
        System.out.print("Enter the number of rows of matrices 2: ");
        int rows2 = scanner.nextInt();
        System.out.print("Enter the number of columns of matrices 2: ");
        int columns2 = scanner.nextInt();

        double[][] b = new double[rows2][columns2];
        for (int i = 0; i < rows2; i++) {
            for (int j = 0; j < columns2; j++) {
                System.out.print("Enter element of matrices 2 at position [" + (i+1) + "][" + (j+1) + "]: ");
                b[i][j] = scanner.nextInt();
            }
        }
        System.out.println("Matrices 2: ");
        for (double[] row:b) {
            System.out.println(Arrays.toString(row));
        }

        if(rows==rows2&&columns==columns2){
            System.out.println("Addition of Matrices:");
            for(double[]ans:addition(a,b)){
                System.out.println(Arrays.toString(ans));
            }
            //
            System.out.println("Subtraction of Matrices:");
            for(double[]ans:subtraction(a,b)){
                System.out.println(Arrays.toString(ans));
            }
        }else{
            System.out.println("Error, cannot perform addition and subtraction operation!");
        }

        if(columns==rows2){
            System.out.println("Multiplication of Matrices:");
            for(double[]ans:multiplication(a,b)){
                System.out.println(Arrays.toString(ans));
            }
        }else{
            System.out.println("Error, cannot perform multiplication operation!");
        }

        System.out.print("\nTo calculate inverse of matrix, enter size of square matrix: ");
        int size = scanner.nextInt();
        double[][] matrix = new double[size][size];

        System.out.println("Matrix A\n");
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                System.out.printf("Enter matrix A at [%d][%d]: ", i+1, j+1);
                matrix[i][j] = scanner.nextDouble();
            }
            System.out.println();
        }
        System.out.println("Matrix A: ");
        displayMatrix(matrix, size);

        double[][] resultMatrix = inverse(matrix);

        if (resultMatrix != null) {
            System.out.printf("\nDeterminant of matrix : %.2f " , determinant(matrix));
            System.out.println("\nInverse Matrix:");
            displayMatrix(resultMatrix, size);
        } else {
            System.out.println("Matrix is not invertible (determinant is zero).");
        }
    }
    public static void displayMatrix(double matrix[][], int size){
        for(double[] row:matrix){
            System.out.println(Arrays.toString(row));
        }
    }

    public static double[][] addition(double[][] a,double[][] b){
        double[][]arrAdd=new double[a.length][a[0].length];
        for(int row=0;row<a.length;row++){
            for(int col=0;col<a[row].length;col++){
                arrAdd[row][col]=a[row][col]+b[row][col];
            }
        }
        return arrAdd;
    }

    public static double[][] subtraction(double[][] a,double[][] b){
        double[][]arrSub=new double[a.length][a[0].length];
        for(int row=0;row<a.length;row++){
            for(int col=0;col<a[row].length;col++){
                arrSub[row][col]=a[row][col]-b[row][col];
            }
        }
        return arrSub;
    }

    public static double[][]multiplication(double[][]a,double[][]b){
        double[][]arrMul=new double[a.length][b[0].length];
        for(int row = 0; row < a.length; row++){
            for(int col = 0; col < b[0].length; col++){
                int prod = 0;
                for(int row2 = 0; row2 < b.length; row2++){
                    prod += a[row][row2] * b[row2][col];
                }
                arrMul[row][col] = prod;
            }
        }
        return arrMul;
    }

    //Method to find DETERMINANT
    public static double determinant(double [][] a){
        double result = 0;
        int length = a.length;

        //Base case 1: 1x1 matrix, return the first element in array
        if (length == 1)
            return a[0][0];

            //Base case 2: 2x2 matrix, use the formula for a 2x2 determinant
        else if (length == 2)
            return a[0][0] * a[1][1] - a[0][1] * a[1][0];

        else {
            double[][] detMat = new double[length - 1][length - 1];

            // Iterate over the elements of the first row (colMain is the current column)
            for (int colMain = 0; colMain < length; colMain++) {
                getMinor(a, detMat, 0, colMain, length);
                // Add the determinant of the submatrix multiplied by the corresponding element of the first row
                result += (((colMain % 2) == 1 ? -1 : 1) * a[0][colMain] * determinant(detMat));
            }
        }
        return result; //return determinant
    }

    // Method to calculate the minor matrix by excluding a specified row and column.
    // The resulting minor matrix is stored in detMat.
    public static void getMinor(double[][] a, double[][] detMat, int discardedRow, int discardedCol, int length) {
        //assign the 0 to discarded row as determinant only involves first row
        // Initialize the row index for the determinant matrix.
        int detMatRow = 0;
        // Iterate through each row of the original matrix.
        for (int row = 0; row < length; row++) {
            // Skip the discarded row to create the minor matrix.
            if (row == discardedRow)
                continue;

            // Initialize the column index for the determinant matrix.
            int detMatCol = 0;

            // Iterate through each column of the original matrix.
            for (int col = 0; col < length; col++) {
                // Skip both the discarded row and column to create the minor matrix.
                if (row != discardedRow && col != discardedCol) {
                    // Copy the element to the determinant matrix.
                    //find the determinant(a,d,b,c)one by one of [0][0]through loop and place it into detMat
                    //after get detMatRow and detMatCol, detMatCol will increase by 1 (until col is not < length anymore)
                    detMat[detMatRow][detMatCol++] = a[row][col];
                }
            } //exit loop

            // Move to the next row in the determinant matrix.
            detMatRow++;
        }
    }

    //Method to find ADJUGATE
    public static double[][] adjugate(double[][] a) {
        int length = a.length;
        double[][] result = new double[length][length];
        double[][] detMat = new double[length - 1][length - 1];

        for (int rowMain = 0; rowMain < length; rowMain++) {
            //difference between adjugate and determinant method is adjugate will get all elements' minor(first row, 2nd and third) while determinant only get minor of elements at first row
            for (int colMain = 0; colMain < length; colMain++) {
                //Call method getMinor to calculate the determinant of the minor matrix formed by excluding the current row and column.
                getMinor(a, detMat, rowMain, colMain, length);
                result[colMain][rowMain] = ((rowMain + colMain) % 2 == 1 ? -1 : 1) * determinant(detMat);
            }
        }
        return result; //return adjugate
    }

    public static double[][] inverse(double[][] a) {
        //find determinant of matrix using determinant method
        double det = determinant(a);
        if (det == 0)
            return null; //inverse does not exist

// Initialize a matrix to store the result (inverse)
        double[][] result = new double[a.length][a.length];
        double[][] adjoint = adjugate(a);
        for (int row = 0; row < a.length; row++) {
            for (int col = 0; col < a.length; col++) {
                result[row][col] = adjoint[row][col] / det;
            }
        }
        return result;
    }
}
