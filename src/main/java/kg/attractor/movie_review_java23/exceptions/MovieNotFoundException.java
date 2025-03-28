package kg.attractor.movie_review_java23.exceptions;

import java.util.NoSuchElementException;

public class MovieNotFoundException extends NoSuchElementException {
    public MovieNotFoundException() {
        super("Movie not found");
    }
}
