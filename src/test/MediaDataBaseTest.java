package test;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MediaDataBaseTest {
    private  MediaDataBase movieDataBase;
    private  MediaDataBase tvShowDataBase;

    @BeforeEach
    void runBefore() {
        movieDataBase = new MovieDatabase("MovieList.txt");
        tvShowDataBase = new TVShowDatabase("TVShowList.txt");
    }

    @Test
    void testLoadingMovie() {
        assertTrue(movieDataBase.getMediaDataBaseSize() == 4);
    }

    @Test
    void testLoadingTVShow() {
        assertTrue(tvShowDataBase.getMediaDataBaseSize() == 2);
    }

    @Test
    void testGetAMovie() {
        Media m = new Movie("CreedII", "2018,Nov,21", 1);
        assertEquals(m.getMediaName(), movieDataBase.getAMedia(2).getMediaName());
    }

    @Test
    void testGetATVShow() {
        Media m = new TVShow("StrangerThings-Season3",  "unknown,2019orLater", 2);
        assertEquals(m.getMediaName(), tvShowDataBase.getAMedia(1).getMediaName());
    }

}
