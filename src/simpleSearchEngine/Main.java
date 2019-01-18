package simpleSearchEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner;
    public static void main(String[] args) {
        scanner = new Scanner(System.in).useDelimiter("\n");
        List<Person> allPersons = getAllPersons();

        System.out.println("Enter the number of search queries:");
        int countQueries = scanner.nextInt();
        if(allPersons.size() == 0 || countQueries < 0){
            System.exit(0);
        }
        processQueries(allPersons, countQueries);
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
            System.out.println("Enter data to search people:");
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
}