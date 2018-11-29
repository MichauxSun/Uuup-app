package test;
import model.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MovieTest {
    private Movie movie;

    @BeforeEach
    void runBefore() {
        movie = new Movie("Venom", "2018,Oct,05", 1);
    }

    @Test
    void testGetMovieName() {
        assertTrue(movie.getMovieName().equals("<<Venom>>"));
    }

    @Test
    void testGetOnScreenDate() {
        assertTrue(movie.getOnScreenDate().equals("2018,Oct,05"));
    }
}
