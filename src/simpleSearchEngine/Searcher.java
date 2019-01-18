package simpleSearchEngine;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.util.ArrayList;
import java.util.List;

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
            boolean isFound = true;
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
}
