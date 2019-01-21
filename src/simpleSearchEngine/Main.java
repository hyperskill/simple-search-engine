package simpleSearchEngine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    static Scanner scanner;
    public static void main(String[] args) {
        scanner = new Scanner(System.in).useDelimiter("\n");
        List<Person> allPersons = getAllPersons();
        int command;
        printMenu();
        while ((command = getCommand()) != 0){
            if (command == 2){
                System.out.println("=== List of people ===");
                printAllPersons(allPersons);
                System.out.println();
            }else if(command == 1){
                processQuery(allPersons);
            }else {
                System.out.println("Incorrect option! Try again.");
            }
            printMenu();
        }
        System.out.println("Bye!");
        System.exit(0);
    }

    private static List<Person> getAllPersons(){
        List<Person> allPersons = new ArrayList<>();
        System.out.println("Enter the number of people:");
        int countPeople = scanner.nextInt();
        System.out.println("Enter all people:");
        for(int i = 0; i < countPeople; i++){
            allPersons.add(new Person(scanner.next()));
        }
        return allPersons;
    }

    private static void processQueries(List<Person> allPersons, int countQueries){
        for(int i = 0; i < countQueries; i++ ){
            System.out.println("Enter a name or email to search all suitable people.");
            String targetData = scanner.next();
            String[] fields = targetData.split(" ");
            List<Person> founded = Searcher.search(allPersons, fields);
            System.out.println("Found people:");
            if(founded.size() > 0){
                for(Person p : founded){
                    System.out.println(p);
                }
            }else {
                System.out.println("For query: " + targetData + " nothing was found");
            }
            System.out.println();
        }
    }

    private static void processQuery(List<Person> allPersons){
        processQueries(allPersons, 1);
    }

    private static void printMenu(){
        System.out.println("=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit");
    }

    private static int getCommand(){
        while (true){
            String commandLine = scanner.next();
            Character ch;
            if(commandLine.toCharArray().length != 1 || !Character.isDigit(commandLine.charAt(0))){
                System.out.println("Please inset a number!");
                printMenu();
            }else if(Integer.parseInt(commandLine) >=0 && Integer.parseInt(commandLine) <= 2){
                return Integer.parseInt(commandLine);
            }else {
                System.out.println("Incorrect option! Try again.");
                printMenu();
            }
        }
    }

    private static List<Person> getAllPersonsFromFile(){
        List<Person> allPersons = new ArrayList<>();
        System.out.println("Enter path to file");
        String path = scanner.next();
        File dataFile = new File(path);
        String lineData;
        while ((lineData = scanner.next()) != null){
            allPersons.add(new Person(lineData));
        }
        return allPersons;
    }

    private static void printAllPersons(List<Person> personList){
        personList.forEach(System.out :: println);
    }

}