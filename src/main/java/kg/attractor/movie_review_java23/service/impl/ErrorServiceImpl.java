package kg.attractor.movie_review_java23.service.impl;

import kg.attractor.movie_review_java23.exceptions.ErrorResponseBody;
import kg.attractor.movie_review_java23.service.ErrorService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ErrorServiceImpl implements ErrorService {

    @Override
    public ErrorResponseBody makeResponse(Exception ex) {
        String msg = ex.getMessage();
        return ErrorResponseBody.builder()
                .title(msg)
                .response(Map.of("errors", List.of(msg)))
                .build();
    }

    @Override
    public ErrorResponseBody makeResponse(BindingResult bindingResult) {
        Map<String, List<String>> reasons = new HashMap<>();
        bindingResult.getFieldErrors().stream()
                .filter(err -> err.getDefaultMessage() != null)
                .forEach(err -> {
                    List<String> errors = new ArrayList<>();
                    errors.add(err.getDefaultMessage());
                    if (!reasons.containsKey(err.getField())) {
                        reasons.put(err.getField(), errors);
                    }
                });
        return ErrorResponseBody.builder()
                .title("Validation Error")
                .response(reasons)
                .build();
    }
}
