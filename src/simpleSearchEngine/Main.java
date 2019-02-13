package simpleSearchEngine;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {

    private static Object[] dictObj;

    static {
        try {
            dictObj = fileToArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String[] dict = (String[]) dictObj[0];
    private static String[] indDict = (String[]) dictObj[1];

    public static void main(String[] args) {
        invertedIndex(dict, indDict);
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
    private static Object[] fileToArray() throws IOException {
        File testFile;
        BufferedReader readFile;
        String[] dict = new String[50];
        String[] indexDict = new String[dict.length * 2];
        int counter = 0;
        try {
            testFile = new File("D:/simple-search-engine/src/simpleSearchEngine/testcase.txt");
            Scanner input = new Scanner(testFile);
            while (input.hasNext()){
                String word = input.next();
                indexDict[counter] = word;
                counter++;
            }
        } catch (FileNotFoundException f){
            System.out.print("Fole not found!!!");
        }
        counter = 0;
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
        Object[] obj = new Object[2];
        obj[0] = dict;
        obj[1] = indexDict;
        return obj;
    }

    /**
     * Fix this Inverted index MAYBE SOME MAGIC WILL HELP :(
     * @param arr1 dictionary
     * @param arr2 dictionary of tokens for search in arr1
     */
    private static void invertedIndex(String[] arr1, String[] arr2){
        HashMap<String, int[]> invInd = new HashMap<>();
        int[] temp = new int[2];
        int count = 0;
        for(int j = 0; j < arr1.length; j++){
            for(int i = 0; i < arr2.length; i++){
                if(arr1[j].toLowerCase().contains(arr2[i].toLowerCase())){
                    temp[count] = j;
                    count++;
                }
            }
            invInd.put(arr1[j], temp);
            count = 0;
            temp = new int[2];
        }

        for (String k: invInd.keySet()) {
            System.out.print("Key: " + k + "   Value: ");
            int[] integers = invInd.get(k);
            for (int i = 0; i < integers.length; i++){
                System.out.print(integers[i] + ", ");
                }
            System.out.println();
        }
    }
}