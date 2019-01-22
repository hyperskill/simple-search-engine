package simpleSearchEngine;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by DIMA, on 21.01.2019
 */
class Processor {
    static void processQueries(List<Person> allPersons, Map<String, List<Integer>> invertedIndexes, Scanner scanner){
        System.out.println("Enter a name or email to search all suitable people.");
        String targetData = scanner.next();
        List<Integer> findedIndexes = Searcher.retainAllIndexedList(invertedIndexes, targetData);
        //String[] fields = targetData.split(" ");
        List<Person> founded = PersonsGetter.getPersonFromListByIndexes(allPersons, findedIndexes);
        System.out.println("Found people:");
        if(founded != null && founded.size() > 0){
            for(Person p : founded){
                System.out.println(p); }
        }else {
            System.out.println("For query: " + targetData + " nothing was found");
        }
        System.out.println();
    }
}
