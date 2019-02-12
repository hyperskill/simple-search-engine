package simpleSearchEngine;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        searchFunc();
    }


    /**
     * function for user input data
     * @return returns array of Strings that user input'ed
     */
    private static String[] input(){
        Scanner inp = new Scanner(System.in);
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
    private static void searchFunc(){
        Scanner inp = new Scanner(System.in);
        System.out.print("Enter number of people: ");
        String[] dict = input();
        System.out.print("Enter number of people to search: ");
        int num = inp.nextInt();
        while(num > 0){
            String search = inp.next().toLowerCase();
            for (String s : dict) {
                if (s.toLowerCase().contains(search)) {
                    System.out.println(s);
                }
            }
            num--;
        }

    }

    /**
     * Test subroutine that prints out all elements of array
     * @param arr for print out all it's elements with index
     */
    private static void testLoop(String[] arr){
        for (int i = 0; i < arr.length; i++){
            System.out.println(i + ") " + arr[i]);
        }
    }
}