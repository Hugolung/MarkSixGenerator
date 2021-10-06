/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mark.six.generator;
import java.util.Scanner;
import java.io.*;
/**
 *
 * @author gogovan
 */
public class MarkSixGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int set;
        int[] tempList = new int[6];
        int[] result = new int[6];
        String marksixText = "";
        String fileName = "marksix.txt";


        // User input
        Scanner input = new Scanner(System.in);
        System.out.print("How many sets of marksix do you want to generate? ");
        set = input.nextInt();
        System.out.println("Please Enter numbers that all sets contained");
        System.out.println("Stop input if number is not between 1-49.");

        for (int k = 1; k <= 6; k++) {

            System.out.print(k + " number: ");
            int num = input.nextInt();
            if (num <= 0 || num >= 50) {
                break;
            } else {
                tempList[k - 1] = num;
            }

            // Check duplicated input
            boolean same;
            do {
                same = false;
                for (int i = 0; i < k - 1; i++) {
                    if (i != k - 1 && tempList[k - 1] == tempList[i]) {
                        same = true;
                        System.out.print("Repeated Number. Please enter again:");
                        tempList[k - 1] = input.nextInt();
                        while (tempList[k - 1] <= 0 || num >= 50) {
                            System.out.print("Number is not from 1-49. Please enter again:");
                            tempList[k - 1] = input.nextInt();
                        }
                    }
                }
            } while (same == true);
        }

        System.arraycopy(tempList, 0, result, 0, 6);
        for (int count = 1; count <= set; count++) {
            marksixText += "Set " + count + ": ";
            System.arraycopy(tempList, 0, result, 0, 6);

            // Generate numbers

            for (int i = 0; i < result.length; i++) {
                if (result[i] == 0) { // Missing input = 0
                    result[i] = 1 + (int) (Math.random() * 49);
                }

                //Check repeating generated numbers
                boolean same;
                do {
                    same = false;
                    for (int j = 0; j <= i; j++) {
                        if (j != i && result[i] == result[j]) {
                            same = true;
                            System.out.println("Generated Depulicated Number");
                            result[j] = 1 + (int) (Math.random() * 49);
                        }
                    }
                } while (same == true);
            }

            // Insert Sorting

            for (int i = 1; i < result.length; i++) {
                int current = result[i];
                int j = i - 1;
                while (j >= 0 && current < result[j]) {
                    result[j + 1] = result[j];
                    j--;

                }
                result[j + 1] = current;
            }

            System.out.println("tempList");
            for (int i = 0; i < tempList.length; i++) {
                System.out.print(tempList[i] + " ");
            }
            System.out.println();
            System.out.println("result");

            for (int i = 0; i < tempList.length; i++) {
                System.out.print(result[i] + " ");
                marksixText += result[i] + ",";
            }
            marksixText += "\n";
            System.out.println("MarksixText");

            System.out.print(marksixText);

        }

        try {
            FileWriter outFile = new FileWriter(fileName);

            outFile.write(marksixText);


            outFile.write("\n");
            outFile.close();

        } catch (IOException e) {
            System.out.println("Write File Fail.");
        }
    }

// TODO code application logic here
    }
    

