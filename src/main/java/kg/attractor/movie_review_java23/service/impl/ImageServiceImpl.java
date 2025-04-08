package kg.attractor.movie_review_java23.service.impl;

import kg.attractor.movie_review_java23.dao.MovieImageDao;
import kg.attractor.movie_review_java23.dto.MovieImageDto;
import kg.attractor.movie_review_java23.exceptions.ImageNotFoundException;
import kg.attractor.movie_review_java23.model.MovieImage;
import kg.attractor.movie_review_java23.service.ImageService;
import kg.attractor.movie_review_java23.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final MovieImageDao movieImageDao;
    private final FileUtil fileUtil;

    @Override
    public String saveImage(MovieImageDto movieImageDto) {

        String filename = fileUtil.saveUploadFile(movieImageDto.getFile(), "images/");
        movieImageDao.save(movieImageDto.getMovieId(), filename);
        return filename;
    }

    @Override
    public ResponseEntity<?> findByName(String imageName) {
        return fileUtil.getOutputFile(imageName, "images/", MediaType.IMAGE_JPEG);
    }

    @Override
    public ResponseEntity<?> findById(long imageId) {
        MovieImage image = movieImageDao.getImageById(imageId)
                .orElseThrow(ImageNotFoundException::new);
        String filename = image.getFileName();
        log.info("Found image with name: {}", filename);
        return fileUtil.getOutputFile(filename, "images/", MediaType.IMAGE_JPEG);
    }

    @Override
    public ResponseEntity<?> findByMovieId(long movieId) {
        MovieImage image = movieImageDao.findByMovieId(movieId)
                .orElseThrow(ImageNotFoundException::new);
        String filename = image.getFileName();
        return fileUtil.getOutputFile(filename, "images/", MediaType.IMAGE_JPEG);
    }

}
