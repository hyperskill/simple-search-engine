package simpleSearchEngine;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {

    private static String[] dict;

    static {
        try {
            dict = fileToArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        mainMenu();
    }


    /**
     * function for user input data
     * @return returns array of Strings that user input'ed
     */
    private static String[] input(){
        Scanner inp = new Scanner(System.in);
        System.out.print("Enter the number of people: ");
        int inpNum = inp.nextInt();
        String[] asset = new String[inpNum + 1];
        System.out.println("Enter all people: ");
        for(int i = 0; i <= inpNum; i++){
            asset[i] = inp.nextLine();
        }
        String[] finalAsset = new String[inpNum];
        System.arraycopy(asset, 1, finalAsset, 0, finalAsset.length);
        return finalAsset;
    }

    /**
     * subroutine that search all occurances of input String in dict
     * and prints out found results.
     */
    private static void searchFunc(String[] dict){
        Scanner inp = new Scanner(System.in);
        String search = inp.next().toLowerCase();
            for (String s : dict) {
                if (s.toLowerCase().contains(search)) {
                    System.out.println(s);
                }
            }
    }

    /**
     * Main menu for program
     */
    private static void mainMenu(){
        Scanner inp = new Scanner(System.in);
        while(true) {
            System.out.print("=== Menu ===\n" +
                    "1. Find a person\n" +
                    "2. Print all people\n" +
                    "0. Exit\n\n");
            System.out.print("Enter your choice: ");
            try {
                switch (inp.nextInt()) {
                    case 1:
                        searchFunc(dict);
                        break;
                    case 2:
                        printAll.accept(dict);
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Wrong input!!!\n " +
                                "Please input a valid choice.");
                        break;
                }
            } catch (InputMismatchException i) {
                System.out.println("Input valid integer number!");
            }
        }
    }

    private static Consumer<String[]> printAll = (arr)-> {
        for (int i = 0; i < arr.length; i++){
            System.out.println((i + 1) + ") " + arr[i]);
        }
    };

    /**
     * Function for converting file into array of people
     * @return dict array that contains all people from file
     * @throws IOException
     */
    private static String[] fileToArray() throws IOException {
        File testFile;
        BufferedReader readFile;
        String[] dict = new String[50];
        int counter = 0;
        try {
            testFile = new File("D:/simple-search-engine/src/simpleSearchEngine/testcase.txt");
            readFile = new BufferedReader(new FileReader(testFile));
            String line;
            while ((line = readFile.readLine()) != null) {
                dict[counter] = line;
                counter++;
            }
            readFile.close();
        } catch (FileNotFoundException f) {
            System.out.println("file not found!!!");
        }
            return dict;
    }
}