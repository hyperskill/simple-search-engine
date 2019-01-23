package simpleSearchEngine;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    //X:\Programming\IdeaProjects\simple-search-engine\all.txt
    private static Scanner scanner;
    public static void main(String[] args) {
        scanner = new Scanner(System.in).useDelimiter("\n");
        List<Person> allPersons = PersonsGetter.getAllPersonsFromFile(scanner);
        Map<String, List<Integer>> invertedIndex = InvertedIndex.buildInvertedIndex(allPersons);
        //InvertedIndex.printInvertedIndexes(invertedIndex);
        //System.out.println("======== Long Indexes ================");
        //InvertedIndex.printOnlyLongInvertedIndexes(invertedIndex);
        int command;
        Printer.printMenu();
        while ((command = getCommand()) != 0){
            if (command == 2){
                System.out.println("=== List of people ===");
                Printer.printAllPersons(allPersons);
                System.out.println();
            }else if(command == 1){
                Processor.processQueries(allPersons, invertedIndex, scanner);
            }else {
                System.out.println("Incorrect option! Try again.");
                continue;
            }
            Printer.printMenu();
        }
        System.out.println("Bye!");
        scanner.close();
        System.exit(0);
    }

    private static int getCommand(){
        //Scanner scannerCommand = new Scanner(System.in);
        while (true){
            String commandLine = scanner.next();
            if(commandLine.toCharArray().length != 1 || !Character.isDigit(commandLine.charAt(0))){
                System.out.println("Please inset a number!");
                Printer.printMenu();
            }else if(Integer.parseInt(commandLine) >=0 && Integer.parseInt(commandLine) <= 2){
                //scanner.close();
                return Integer.parseInt(commandLine);
            }else {
                System.out.println("Incorrect option! Try again.");
                Printer.printMenu();
            }
        }
    }







}