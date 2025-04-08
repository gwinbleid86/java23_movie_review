package kg.attractor.movie_review_java23.controller.api;

import kg.attractor.movie_review_java23.dto.MovieImageDto;
import kg.attractor.movie_review_java23.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @GetMapping
    public ResponseEntity<?> getImageById(@RequestParam(name = "id") Long id) {
        return imageService.findById(id);
    }

    @GetMapping("{imageName}")
    public ResponseEntity<?> getImageByName(@PathVariable String imageName) {
        return imageService.findByName(imageName);
    }

    @GetMapping("movies/{movieId}")
    public ResponseEntity<?> getImageByMovieId(@PathVariable int movieId) {
        return imageService.findByMovieId(movieId);
    }

    @PostMapping
    public String uploadImage(MovieImageDto movieImageDto) {
        return imageService.saveImage(movieImageDto);
    }
}
