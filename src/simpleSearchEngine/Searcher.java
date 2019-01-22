package simpleSearchEngine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by DIMA, on 18.01.2019
 */
class Searcher {
    private static List<Person> searchByOneField(List<Person> persons, String data){
        List<Person> retrievedPersons = new ArrayList<>();
        for(Person p : persons){
            if(p.getFirstName().equalsIgnoreCase(data) ||
                    p.getSecondName().equalsIgnoreCase(data) ||
                    p.getEmail() != null && p.getEmail().equalsIgnoreCase(data)){
                retrievedPersons.add(p);
            }
        }
        return retrievedPersons;
    }

    static List<Person> search(List<Person> persons, String[] data){
        if(data.length == 1){
            return searchByOneField(persons, data[0]);
        }
        List<Person> retrievedPersons = new ArrayList<>();
        for(Person p : persons){
            boolean[] blockedFields = {false, false, false};
            int countBlockedFields = 0;
            for(String s : data){
                int result = compareField(p, s, blockedFields);
                if(result != -1){
                    blockedFields[result] = true;
                    countBlockedFields++;
                }else {
                    break;
                }
            }
            if(data.length == countBlockedFields){
                retrievedPersons.add(p);
            }
        }
        return retrievedPersons;
    }

    private static int compareField(Person p, String data, boolean[] blocked){
        if(!blocked[0] && p.getFirstName().equalsIgnoreCase(data))return 0;
        if(!blocked[1] && p.getSecondName().equalsIgnoreCase(data))return 1;
        if(!blocked[2] && p.getEmail() != null && p.getEmail().equalsIgnoreCase(data))return 2;
        return -1;
    }

    private static List<Integer> getIndexes (Map<String, List<Integer>> invertedIndexes, String field){
        for(Map.Entry<String, List<Integer>> entry : invertedIndexes.entrySet()){
            if(field.equalsIgnoreCase(entry.getKey())){
                return entry.getValue();
            }
        }
        return null;
    }

    static List<Integer> retainAllIndexedList(Map<String, List<Integer>> invertedIndexes, String data){
        List<Integer> first;
        if(data == null) {
            System.out.println("No data");
            return null;
        }
        String[] fields = data.split(" +");
        if(fields.length == 0){
            System.out.println("You insert only spaces");
            return null;
        }

        if(getIndexes(invertedIndexes, fields[0]) == null){
            return null;
        }
        first = new ArrayList<>(getIndexes(invertedIndexes, fields[0]));
        //System.out.println(first);
        //List<Integer> usedIndexes = new ArrayList<>(first);


        for(int i = 1; i < fields.length; i++){
            if(getIndexes(invertedIndexes, fields[i]) == null){
                return null;
            }
            List<Integer> nextList = new ArrayList<>(getIndexes(invertedIndexes, fields[i]));
            //System.out.println(nextList);
            //nextList.removeAll(usedIndexes);
            //usedIndexes.addAll(nextList);
            first.retainAll(nextList);
        }
        //delete duplicates
        first = new ArrayList<>(new HashSet<>(first));
        return first;
    }




}
