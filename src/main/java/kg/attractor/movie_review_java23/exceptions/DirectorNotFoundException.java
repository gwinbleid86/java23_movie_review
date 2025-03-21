package kg.attractor.movie_review_java23.exceptions;

public class DirectorNotFoundException extends RuntimeException {
    public DirectorNotFoundException() {
        super("Director not found");
    }
}
