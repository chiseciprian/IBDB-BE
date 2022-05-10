package ro.fasttrackit.bookapp.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.fasttrackit.bookapp.model.FileEntity;
import ro.fasttrackit.bookapp.respository.FileRepository;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    @Autowired

    private GridFsTemplate gridFsTemplate;
    @Autowired

    private GridFsOperations operations;

    public String addFile(String title, MultipartFile file) throws IOException {
        DBObject metaData = new BasicDBObject();
        metaData.put("type", "pdf");
        metaData.put("title", title);
        ObjectId id = gridFsTemplate.store(
                file.getInputStream(), file.getName(), file.getContentType(), metaData);
        return id.toString();
    }

    public FileEntity getFile(String id) throws IllegalStateException, IOException {
        GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        FileEntity fileEntity = new FileEntity();
        fileEntity.setTitle(file.getMetadata().get("title").toString());
        fileEntity.setFile(operations.getResource(file).getInputStream());
        return fileEntity;
    }
}
