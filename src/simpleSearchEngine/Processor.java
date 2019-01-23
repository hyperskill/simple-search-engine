package simpleSearchEngine;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by DIMA, on 21.01.2019
 */
class Processor {
    static void processQueries(List<Person> allPersons, Map<String, List<Integer>> invertedIndexes, Scanner scanner){
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        String stringMode;
        List<String> modes =Arrays.stream(Mode.values()).map(Mode::name).map(String::toLowerCase).collect(Collectors.toList());

        while (!modes.contains(stringMode = scanner.next().toLowerCase())){
            System.out.println("Wrong Strategy. Select a matching strategy: ALL, ANY, NONE");
        }

        Mode mode = Mode.valueOf(stringMode.toUpperCase());
        System.out.println("Enter a name or email to search all suitable people.");
        String targetData = scanner.next();
        List<Integer> findedIndexes = Searcher.getResultIndexedList(invertedIndexes, targetData, mode);
        //String[] fields = targetData.split(" ");
        List<Person> founded = PersonsGetter.getPersonFromListByIndexes(allPersons, findedIndexes);

        if(founded == null || founded.size() == 0){
            System.out.println("For query: " + targetData + " nothing was found");
        }else if(founded.size() == 1){
            System.out.println("1 person found:");
            System.out.println(founded.get(0));
        }else {
            System.out.println(founded.size() + " persons found:");
            for(Person p : founded){
                System.out.println(p);
            }
        }
        System.out.println();
    }
}
