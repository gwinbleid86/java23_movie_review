package kg.attractor.movie_review_java23.controller;

import kg.attractor.movie_review_java23.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @GetMapping("{imageName}")
    public ResponseEntity<?> getImage(@PathVariable String imageName) {
        return imageService.findByName(imageName);
    }

    @PostMapping
    public String uploadImage(MultipartFile file) {
        return imageService.saveImage(file);
    }
}
