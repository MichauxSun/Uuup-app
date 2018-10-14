package model;

import java.util.ArrayList;

public interface Writable {
    void writeIntoDatabase(ArrayList<Person> users);
}
