package simpleSearchEngine;

import java.util.*;

class Main {
  public static void main(String args[]) {
    List<Person> persons = new ArrayList<>();
    Finder<Person> finder = new Finder<Person>();

    try {
      persons = Util.getPersonsFromFile("pdata.txt");
        finder.addAll(persons);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    Scanner sc = new Scanner(System.in);
    boolean cont = true;
    while(cont) {
      System.out.println("  === Menu ===");
      System.out.println("  1. Find a person ");
      System.out.println("  2. Print all people");
      System.out.println("  0. Exit ");
      String inputLine = sc.nextLine();
      int input = -1;
      try {
        input = Integer.parseInt(inputLine);
      } catch (Exception e) {
        System.out.println("Only numeric options allowed.");
      }

      switch(input) {
          case 1:
            System.out.println("Select a matching strategy: ALL, ANY, NONE. Enter /stop to terminate. Case sensitive.");
            while(true) {
              String strategy = sc.nextLine();
              if (strategy.trim().equals("/stop")) break;
              try {
              switch(strategy) {

                case "ALL":
                  System.out.println("Enter seach terms, delimited with whitespace. Programm will return records which contains all terms. Enter /stop to terminate.");
                  String termAll = sc.nextLine();
                  String[] termsAll = termAll.trim().split(" ");
                  List<Person> allPersons = finder.findAll(termsAll);
                  if (allPersons != null) {
                    System.out.printf("Found %d people: %n", allPersons.size());

                    for (Person person : allPersons) {
                        System.out.print(person.toString());
                    }
                    System.out.println("");
                  } else {
                      System.out.println("No matching people found.");
                  }
                  break;
                case "ANY":
                    System.out.println("Enter seach terms, delimited with whitespace. Programm will return records which contains any of terms. Enter /stop to terminate.");
                    String termAny = sc.nextLine();
                    String[] termsAny = termAny.trim().split(" ");
                    List<Person> anyPersons = finder.findAny(termsAny);
                    if (anyPersons != null) {
                        System.out.printf("Found %d people: %n", anyPersons.size());
                        for (Person person : anyPersons) {
                            System.out.print(person.toString());
                        }
                        System.out.println("");
                    } else {
                        System.out.println("No matching people found.");
                    }
                    break;
                  case "NONE":
                        System.out.println("Enter seach terms, delimited with whitespace. Programm will return records which does not contain any of terms. Enter /stop to terminate.");
                        String noneTerm = sc.nextLine();
                        String[] noneTerms = noneTerm.trim().split(" ");
                        List<Person> nonePersons = finder.findNone(noneTerms);
                        if (nonePersons != null) {
                            System.out.printf("Found %d people: %n", nonePersons.size());
                            for (Person person : nonePersons) {
                                System.out.print(person.toString());
                            }
                            System.out.println("");
                        } else {
                            System.out.println("No matching people found.");
                        }
                        break;
                    default:
                      System.out.println("Select a matching strategy: ALL, ANY, NONE. Enter /stop to terminate. Case sensitive.");
                      break;
              }
            } catch (Exception e) {
              System.out.println("An error occured: ");
               e.printStackTrace();
            }
          }
          break;

          case 2:
            System.out.printf("Found %d people: %n", persons.size());
            for(Person p : persons) {
              System.out.print(p.toString());
            }
            System.out.println("");
          break;

          case 0:
            cont = false;
            System.out.println("Bye!");
            break;

          default:
            System.out.println("Incorrect option. Try again.");

          }


        }
      }

  /**
  **/
  private static List<Person> addPersonsFromCLI(Scanner sc){
    List<Person> persons = new ArrayList<>();
    while(true) {
      System.out.println("Enter persons data in format: first name | last name | email (optional). Each entry from a new line, delimit with spaces. To terminate enter /stop");
      String input = sc.nextLine();
      if (input.trim().equals("/stop")) break;
      if (input != null && input.length() > 0) {
        String words[] = input.trim().split(" ");
        if (words.length >= 2) {
          String lastName = words[1];
          String firstName = words[0];
          String email = (words.length == 3) ? words[2] : null;
          Person person = new Person(firstName, lastName, email);
          persons.add(person);
        } else {
          System.out.println("First name and last name are requied params");
        }
      }
    }
    return persons;
  }
}

interface Searcheable {
		/**
		Returns values which should be indexed
		**/
		List<String> getIndex();
}

class Finder<T extends Searcheable> {

	private final boolean caseSensitive;

	public Finder() {
		caseSensitive = false;
		elements = new ArrayList<>();
		mapping = new HashMap<>();
	}

	public Finder(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
		elements = new ArrayList<>();
		mapping = new HashMap<>();
	}

	public List<T> getAll() {

		return elements;

	}

	public void addAll(List<T> list) throws Exception {

		for (T element : list) {

			add(element);

		}

	}

	public void add(T element) throws Exception{

		boolean elementAdded = elements.add(element);
		int position = elements.lastIndexOf(element);

		if (!elementAdded || position == -1) throw new Exception();

		addToIndex(element, position);

	}

  private List<T> findElements(Set<Integer> indexes) throws Exception {

    if (indexes == null || indexes.size() == 0) return null;
    List<T> result = new ArrayList<>();

    for (Integer i  : indexes) {
      result.add(elements.get(i));
    }

    return result;


  }

	private Set<Integer> find(String term) {

		term = sanitizeIndexValue(term);

		if (mapping.containsKey(term)) {

			return mapping.get(term);

		} else return null;
	}


	public List<T> findAll(String[] terms) throws Exception {
    if (terms == null || terms.length == 0) return null;

		Set<Integer> result = new HashSet<>();

		for (String term : terms) {
			if (result.size() == 0) {
        Set<Integer> init = find(term);
        if (init != null) result.addAll(find(term));
			} else {
        Set<Integer> found = find(term);
        if (found != null) {
				      result.retainAll(found);
        } else {
          return null;
        }
			}
		}

		return findElements(result);
	}

	public List<T> findAny(String[] terms) throws Exception {
		if (terms == null || terms.length == 0) return null;

    Set<Integer> result = new HashSet<>();

		for (String term : terms) {
      if (result.size() == 0) {
        Set<Integer> init = find(term);
        if (init != null) result.addAll(find(term));
			} else {
        Set<Integer> found = find(term);
        if (found != null) {
            result.addAll(found);
          }
      }
		}

		return findElements(result);
	}

	public List<T> findNone(String[] terms) throws Exception {
    if (terms == null || terms.length == 0) return null;
		List<T> anys = findAny(terms);

		if (anys != null) {
			List<T> noneElements = new ArrayList<T>(elements);
			noneElements.removeAll(anys);
			return noneElements;
		} else return elements;
	}

	private void addToIndex(T element, int pos) {

		List<String> index = element.getIndex();

		for (String string : index) {

			string = sanitizeIndexValue(string);
      if (string != null) {

			if (mapping.containsKey(string)) {
				Set<Integer> postions = mapping.get(string);
				postions.add(pos);
			} else {
				Set<Integer> postions = new HashSet<>();
				postions.add(pos);
				mapping.put(string, postions);
			}
    }
		}
	}


	private String sanitizeIndexValue(String string) {

		if (string == null || string.trim().length() == 0) return null;

		if (caseSensitive) {
			return string.trim();
		} else {
			return string.trim().toLowerCase();
		}
	}

	private List<T> elements;
	private Map<String, Set<Integer>> mapping;
}



class Person implements Searcheable {

	Person(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public String toString() {
    StringBuilder sb = new StringBuilder();
    if (firstName != null) sb.append(firstName).append(" ");
		if (lastName != null) sb.append(lastName).append(" ");
		if (email != null) sb.append(email);
    sb.append("; ");
		return sb.toString();
	}

	public List<String> getIndex() {
		List<String> result = new ArrayList<>();

		if (firstName != null) result.add(firstName);
		if (lastName != null) result.add(lastName);
		if (email != null) result.add(email);

		return result;
	}

	private String firstName;
	private String lastName;
	private String email;

}

/*
javac src/com/kostylevv/paksrms/*.java
java -cp src/ com.kostylevv.paksrms.Paks
*/
