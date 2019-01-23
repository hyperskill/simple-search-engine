package simpleSearchEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by DIMA, on 21.01.2019
 */
class InvertedIndex {

   static Map<String, List<Integer>> buildInvertedIndex(List<Person> persons){
      Map<String, List<Integer>> map = new TreeMap<>();
      for(int i = 0; i < persons.size(); i++){
         Person p = persons.get(i);
         String[] personsFields = {p.getFirstName(), p.getSecondName(), p.getEmail()};
         int len = p.getEmail() == null ? 2 : 3;
         for(int f = 0; f < len; f++ ){
            if(map.containsKey(personsFields[f])){
                map.get(personsFields[f]).add(i);
            }else {
               List<Integer> indexList = new ArrayList<>();
               indexList.add(i);
               map.put(personsFields[f],indexList);
            }
         }
      }
      return map;
   }

   static void printInvertedIndexes(Map<String, List<Integer>> invertedIndexes){
      for(Map.Entry<String, List<Integer>> entry : invertedIndexes.entrySet()){
         System.out.println(entry.getKey() + " -> " + entry.getValue());
      }
   }

    static void printOnlyLongInvertedIndexes(Map<String, List<Integer>> invertedIndexes){
        for(Map.Entry<String, List<Integer>> entry : invertedIndexes.entrySet()){
            if(entry.getValue().size() > 3){
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }

        }
    }
}
