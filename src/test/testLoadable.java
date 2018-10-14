package test;

import model.Loadable;
import model.MediaDataBase;
import model.MovieDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testLoadable {
    private Loadable movieDataBase;

    @BeforeEach
    void runBefore() {
        movieDataBase = new MovieDatabase("MovieList.txt");
    }

    @Test
    void testLoadMedia() {
        assertEquals("<<Venom>>", movieDataBase.getDataBase().get(0).getMediaName());
        assertEquals("<<CreedII>>", movieDataBase.getDataBase().get(1).getMediaName());
        assertEquals("<<FantasticBeast2>>", movieDataBase.getDataBase().get(3).getMediaName());
    }
}
