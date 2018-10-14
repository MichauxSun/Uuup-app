package test;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class testWritable {
    private Writable movieDataBase;
    private ArrayList<Person> users;

    @BeforeEach
    void runBefore() {
        movieDataBase = new MovieDatabase("MovieList.txt");
        Person p1 = new Person("Michaux", "michaux2817@outlook.com", 1);
        Person p2 = new Person();
        users = new ArrayList<>();

        Media movie1 = new Media("Venom", "2018,Oct,05");
        Media movie2 = new Media("CreedII", "2018,Nov,21");
        Media movie3 = new Media("Spider-Man:IntoTheSpider-Verse", "2018,Dec,14");

        p1.addToMediaList(movie1, p1.getMovieList());
        p1.addToMediaList(movie2, p1.getMovieList());
        p2.addToMediaList(movie3, p2.getMovieList());

        users.add(p1);
        users.add(p2);
    }

    @Test
    void testWritable(){
        movieDataBase.writeIntoDatabase(users);
        try {
            FileReader fr = new FileReader("outputfile.txt");
            BufferedReader br = new BufferedReader(fr);
            String aLine = br.readLine();
            assertEquals("Michaux: <<Venom>> <<CreedII>> ", aLine);
            aLine = br.readLine();
            assertEquals("user100: <<Spider-Man:IntoTheSpider-Verse>> ", aLine);

        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
