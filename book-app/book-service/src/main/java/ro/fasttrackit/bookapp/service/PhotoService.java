package ro.fasttrackit.bookapp.service;

import lombok.RequiredArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.fasttrackit.bookapp.model.PhotoEntity;
import ro.fasttrackit.bookapp.respository.PhotoRepository;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PhotoService {
    private final PhotoRepository photoRepository;

    public PhotoEntity addPhoto(String title, MultipartFile file) throws IOException {
        PhotoEntity photo = new PhotoEntity();
        photo.setTitle(title);
        photo.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        return photoRepository.save(photo);
    }

    public PhotoEntity getPhoto(String id) {
        return photoRepository.findById(id).get();
    }
}
