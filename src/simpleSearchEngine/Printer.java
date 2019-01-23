package simpleSearchEngine;

import java.util.List;

/**
 * Created by DIMA, on 21.01.2019
 */
class Printer {
    static void printAllPersons(List<Person> personList){
        personList.forEach(System.out :: println);
    }

    static void printMenu(){
        System.out.println("=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit");
    }
}
