package kg.attractor.movie_review_java23.service.impl;

import kg.attractor.movie_review_java23.service.ImageService;
import kg.attractor.movie_review_java23.util.FileUtil;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public String saveImage(MultipartFile file) {
        FileUtil fu = new FileUtil();
        return fu.saveUploadFile(file, "images/");
    }

    @Override
    public ResponseEntity<?> findByName(String imageName) {
        return new FileUtil().getOutputFile(imageName, "images/", MediaType.IMAGE_JPEG);
    }

}
