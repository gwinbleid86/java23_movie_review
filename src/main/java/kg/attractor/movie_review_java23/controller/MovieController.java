package kg.attractor.movie_review_java23.controller;

import kg.attractor.movie_review_java23.dto.CastDto;
import kg.attractor.movie_review_java23.dto.MovieDto;
import kg.attractor.movie_review_java23.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
@RequestMapping("movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public String getMovies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "movies/movies";
    }

    @GetMapping("create")
    public String createMovie() {
        return "movies/new_movie";
    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String createMovie(
            @RequestParam(name = "castMemberName") String castMemberName,
            @RequestParam(name = "castMemberRole") String castMemberRole,
            MovieDto movieDto
    ) {
        movieService.createMovie(MovieDto.builder()
                .name(movieDto.getName())
                .description(movieDto.getDescription())
                .year(movieDto.getYear())
                .director(movieDto.getDirector())
                .cast(List.of(CastDto.builder()
                        .fullName(castMemberName)
                        .role(castMemberRole)
                        .build()))
                .build());
        return "redirect:/movies";
    }
}
