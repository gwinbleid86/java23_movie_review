package kg.attractor.movie_review_java23.service;

import kg.attractor.movie_review_java23.exceptions.ErrorResponseBody;
import org.springframework.validation.BindingResult;

public interface ErrorService {
    ErrorResponseBody makeResponse(Exception ex);

    ErrorResponseBody makeResponse(BindingResult bindingResult);
}
