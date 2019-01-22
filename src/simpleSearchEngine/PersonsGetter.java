package simpleSearchEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by DIMA, on 21.01.2019
 */
class PersonsGetter {
    static List<Person> getAllPersonsFromFile(Scanner scanner){
        //Scanner scanner = new Scanner(System.in);
        while (true){
            List<Person> allPersons = new ArrayList<>();
            System.out.println("Enter path to file");
            String path = scanner.next();
            File dataFile = new File(path);
            try(FileReader fileReader = new FileReader(dataFile)){
                Scanner scannerFile = new Scanner(fileReader).useDelimiter("\n");
                String data;
                while (scannerFile.hasNextLine()){
                    data = scannerFile.nextLine().trim();
                    //System.out.println(data);
                    allPersons.add(new Person(data));
                }
                scannerFile.close();
            }catch (FileNotFoundException e){
                System.out.println("File not found. Please check path");
                continue;
            }catch (IOException e2){
                System.out.println("ioexceprion " + e2.getMessage());
                continue;
            }
            //scanner.close();
            return allPersons;
        }
    }

    private static List<Person> getAllPersons(Scanner scanner){
        //Scanner scanner = new Scanner(System.in);
        List<Person> allPersons = new ArrayList<>();
        System.out.println("Enter the number of people:");
        int countPeople = scanner.nextInt();
        System.out.println("Enter all people:");
        for(int i = 0; i < countPeople; i++){
            allPersons.add(new Person(scanner.next()));
        }
        //scanner.close();
        return allPersons;
    }

    static List<Person> getPersonFromListByIndexes(List<Person> allPersons, List<Integer> indexesPerson){
        if(indexesPerson == null){
            return null;
        }
        List<Person> findedPersons = new ArrayList<>();
        for(Integer index : indexesPerson){
            findedPersons.add(allPersons.get(index));
        }
        return findedPersons;

    }
}
