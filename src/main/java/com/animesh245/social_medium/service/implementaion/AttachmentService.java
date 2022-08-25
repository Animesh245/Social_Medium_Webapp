package com.animesh245.social_medium.service.implementaion;

import com.animesh245.social_medium.config.Properties;
import com.animesh245.social_medium.entity.Attachment;
import com.animesh245.social_medium.exception.NotFoundException;
import com.animesh245.social_medium.repository.AttachmentRepo;
import com.animesh245.social_medium.service.definition.IAttachmentService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AttachmentService implements IAttachmentService
{
    private final AttachmentRepo attachmentRepo;

    public AttachmentService(AttachmentRepo attachmentRepo)
    {
        this.attachmentRepo = attachmentRepo;
    }

    @Override
    public Attachment getAttachment(Long id)
    {
        return attachmentRepo.findById(id).orElseThrow(() -> new NotFoundException("Attachment not found"));
    }

    @Override
    public Attachment saveAttachment(CommonsMultipartFile file, String path) {

        Path fileLocation = Paths.get(Properties.WRITE_PATH + path); // Actual file location where the files will be stored

        if (!file.isEmpty())
        {
            String fileName = file.getOriginalFilename();
            assert fileName != null;
            Path createFilePath = fileLocation.resolve(fileName); // Creating a path, named after the fileName in the actual file location

            if (!createFilePath.toFile().exists())
            {
                createFilePath.toFile().mkdirs(); // if an empty file is not created in the createdFilePath, then this method will create one.
            }

            try(InputStream inputStream = file.getInputStream())
            {
                Files.copy(inputStream,createFilePath, StandardCopyOption.REPLACE_EXISTING); //Copy method will create a file in the createFilePath location and if needed it will replace the existing.

                //Now store data in attachment table
                var attachment = new Attachment();
                attachment.setAttachmentName(fileName);
                attachment.setAttachmentPath(path + fileName);
                attachment.setAttachmentType(file.getContentType());
                attachmentRepo.save(attachment);
                return attachment;
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }

       return null;
    }

    @Override
    public List<Attachment> insertInBulks(CommonsMultipartFile[] files)
    {
        String path = Properties.STATUS_FOLDER;
        List<Attachment> attachmentList = new ArrayList<>();

        for (CommonsMultipartFile file: files)
        {
           var attached = saveAttachment(file,path);
           attachmentList.add(attached);
        }
        return attachmentList;
    }


}
