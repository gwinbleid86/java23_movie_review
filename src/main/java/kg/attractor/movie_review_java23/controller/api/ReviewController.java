package kg.attractor.movie_review_java23.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/reviews")
public class ReviewController {

    @GetMapping("by_movie_id")
    public void getByMovieId(@RequestParam(name = "movieId") Long movieId) {
        System.out.println("getByMovieId: " + movieId);
    }
}
