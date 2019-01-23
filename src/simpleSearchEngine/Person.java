package simpleSearchEngine;

/**
 * Created by DIMA, on 17.01.2019
 */
class Person {
    private String firstName;
    private String secondName;
    private String email;

    Person(String data) {
        String[] items = data.split(" +");
        if(items.length == 2){
            this.firstName = items[0];
            this.secondName = items[1];
            this.email = null;
        }else if(items.length == 3){
            this.firstName = items[0];
            this.secondName = items[1];
            this.email =items[2];
        }else {
            System.out.println("Wrong data");
        }
    }

    String getFirstName() {        return firstName;    }
    String getSecondName() {        return secondName;    }
    String getEmail() {        return email;    }

    @Override
    public String toString() {
        if(email == null){
            return firstName + " " + secondName;
        }
        return firstName + " " + secondName + " " + email;
    }
}
