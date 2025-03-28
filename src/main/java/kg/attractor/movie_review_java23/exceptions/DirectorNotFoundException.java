package kg.attractor.movie_review_java23.exceptions;

import java.util.NoSuchElementException;

public class DirectorNotFoundException extends NoSuchElementException {
    public DirectorNotFoundException() {
        super("Director not found");
    }
}
