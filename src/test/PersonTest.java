package test;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonTest {
    private Person p2;
    private  MediaDataBase movieDataBase;
    private  MediaDataBase tvShowDataBase;

    @BeforeEach
    void runBefore() {
        p2 = new Person("Michaux", "michaux2817@outlook.com", 1);
        movieDataBase = new MovieDatabase("MovieList.txt");
        tvShowDataBase = new TVShowDatabase("TVShowList.txt");
    }

    @Test
    void testAddNewToMovieList() {
        Media m1 = new Movie("Venom", "2018,Oct,05");
        Media m2 = new Movie("CreedII", "2018,Nov,21");
        Media m3 = new Movie("Spider-Man:IntoTheSpider-Verse", "2018,Dec,14");
        p2.addToMediaList(m1, p2.getMovieList());
        p2.addToMediaList(m2, p2.getMovieList());
        p2.addToMediaList(m3, p2.getMovieList());
        assertTrue(p2.containMedia(m1, p2.getMovieList()));
    }

    @Test
    void testAddDuplicateToMovieList() {
        Media m1 = new Movie("Venom", "2018,Oct,05");
        Media m2 = new Movie("CreedII", "2018,Nov,21");
        Media m3 = new Movie("Spider-Man:IntoTheSpider-Verse", "2018,Dec,14");
        Media m4 = new Movie("Venom", "2018,Oct,05");
        p2.addToMediaList(m1, p2.getMovieList());
        p2.addToMediaList(m2, p2.getMovieList());
        p2.addToMediaList(m3, p2.getMovieList());
        p2.addToMediaList(m4, p2.getMovieList());
        assertTrue(p2.getMovieList().size() == 3);
    }

    @Test
    void testAddToWishListInMovieList() {
        p2.addToWishList("1", movieDataBase, tvShowDataBase);
        assertTrue(p2.containMedia(new Movie("Venom", "2018,Oct,05"), p2.getMovieList()));
    }

    @Test
    void testAddToWishListInTVShowList() {
        p2.addToWishList("6", movieDataBase, tvShowDataBase);
        assertTrue(p2.containMedia(new TVShow("Westworld-Season3", "2019"), p2.getTVShowList()));
    }

    @Test
    void testDeleteFromWishList() {
        Media m1 = new Movie("Venom", "2018,Oct,05");
        Media m2 = new Movie("CreedII", "2018,Nov,21");
        Media m3 = new Movie("Spider-Man:IntoTheSpider-Verse", "2018,Dec,14");
        p2.addToMediaList(m1, p2.getMovieList());
        p2.addToMediaList(m2, p2.getMovieList());
        p2.addToMediaList(m3, p2.getMovieList());
        p2.deleteFromWishList(1, "M");
        assertTrue(p2.getMovieList().size() == 2);
    }
}
