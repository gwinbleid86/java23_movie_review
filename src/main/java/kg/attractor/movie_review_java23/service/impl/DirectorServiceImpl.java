package kg.attractor.movie_review_java23.service.impl;

import kg.attractor.movie_review_java23.dao.DirectorDao;
import kg.attractor.movie_review_java23.dto.DirectorDto;
import kg.attractor.movie_review_java23.exceptions.DirectorNotFoundException;
import kg.attractor.movie_review_java23.model.Director;
import kg.attractor.movie_review_java23.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final DirectorDao directorDao;

    @Override
    public DirectorDto getDirectorById(int id) {
        Director director = directorDao.findDirectorById(id)
                .orElseThrow(DirectorNotFoundException::new);
        return DirectorDto.builder()
                .id(director.getId())
                .fullName(director.getFullName())
                .build();
    }
}
