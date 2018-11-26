package simpleSearchEngine;

import java.util.*;
import static java.nio.file.StandardOpenOption.*;
import java.nio.file.*;
import java.io.*;

public class Util {

  private static final String DB_PATH = "db\\";

  public static List<Person> getPersonsFromFile(String filename) throws Exception {
    List<Person> persons = new ArrayList<>();
    Path file = Paths.get(DB_PATH + filename);
        try (InputStream in = Files.newInputStream(file);
          BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
          String line = null;
          while ((line = reader.readLine()) != null) {

            String[] words = line.trim().split(" ");

            switch(words.length) {
              case 2:
                Person p = new Person(words[0], words[1], null);
                persons.add(p);
                break;
              case 3:
                Person z = new Person(words[0], words[1], words[2]);
                persons.add(z);
                break;

              default:
                System.out.println("Person entry should contain at least first name and last name delimited with space");
          }

        }
      }

    return persons;

    }
}
