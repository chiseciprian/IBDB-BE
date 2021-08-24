package ro.fasttrackit.bookapp.service;

import lombok.RequiredArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.fasttrackit.bookapp.model.CoverEntity;
import ro.fasttrackit.bookapp.respository.CoverRepository;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoverService {
    private final CoverRepository coverRepository;

    public CoverEntity addCover(String title, MultipartFile file) throws IOException {
        CoverEntity cover = new CoverEntity();
        cover.setTitle(title);
        cover.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        return coverRepository.save(cover);
    }

    public Optional<CoverEntity> getCover(String id) {
        return coverRepository.findById(id);
    }
}
