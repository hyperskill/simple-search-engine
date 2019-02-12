package simpleSearchEngine;

import java.util.Scanner;
import java.util.function.Consumer;

public class Main {

    private static String[] dict = input();

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
                    "0. Exit");
            System.out.print("Enter your choice: ");
            switch (inp.nextInt()) {
                case 1: searchFunc(dict);
                    break;
                case 2: printAll.accept(dict);
                    break;
                case 0: System.exit(0);
                    break;
                default: System.out.println("Wrong input!!!\n " +
                        "Please input a valid choice.");
                break;
            }
        }
    }

    private static Consumer<String[]> printAll = (arr)-> {
        for (int i = 0; i < arr.length; i++){
            System.out.println((i + 1) + ") " + arr[i]);
        }
    };
}