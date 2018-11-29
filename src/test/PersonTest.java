package test;

import exceptions.InputOutOfRangeException;
import exceptions.InvalidInputException;
import exceptions.NegativeNumException;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
    void testAddToWishListInMovieList() {
        p2.addToWishList("1", movieDataBase, tvShowDataBase);
        Movie media = new Movie("Venom", "2018,Oct,05", 1);
        assertTrue(p2.getMovieList().containMedia(media.getMovieName()));
    }

    @Test
    void testAddToWishListInTVShowList() {
        p2.addToWishList("6", movieDataBase, tvShowDataBase);
        TVShow aShow = new TVShow("Westworld-Season3", "2019", 2);
        assertTrue(p2.getTVShowList().containMedia(aShow.getTVShowName()));
    }

    @Test
    void testDeleteFromWishListSafty() {
        p2.addToWishList("1", movieDataBase, tvShowDataBase);
        p2.addToWishList("2", movieDataBase, tvShowDataBase);
        p2.addToWishList("3", movieDataBase, tvShowDataBase);
        try {
            p2.deleteFromWishList(1, "M");
            assertTrue(p2.getMovieList().getSize() == 2);
        } catch(InvalidInputException e) {
            fail("shouldn't catch this exception here");
        }

    }

//    @Test
//    void testDeleteFromWishListWithOverRangeException() {
//        p2.addToWishList("1", movieDataBase, tvShowDataBase);
//        p2.addToWishList("2", movieDataBase, tvShowDataBase);
//        p2.addToWishList("3", movieDataBase, tvShowDataBase);
//        try {
//            p2.delecteFromMovieList(4);
//            fail("should not have catch InvalidInputException here");
//
//        } catch(InputOutOfRangeException e) {
//            System.out.println("Out of range number choosing. Got ya~!");
//        }
//    }
//
//    @Test
//    void testDeleteFromWishListWithNegativeNumException() {
//        p2.addToWishList("1", movieDataBase, tvShowDataBase);
//        p2.addToWishList("2", movieDataBase, tvShowDataBase);
//        p2.addToWishList("3", movieDataBase, tvShowDataBase);
//        try {
//            p2.delecteFromMovieList(-1);
//            fail("should not have catch InvalidInputException here");
//
//        } catch(NegativeNumException e) {
//            System.out.println("Negative number choosing. Got ya~!");
//        }
//    }
}
