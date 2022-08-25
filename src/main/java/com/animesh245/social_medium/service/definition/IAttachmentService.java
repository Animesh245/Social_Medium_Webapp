package com.animesh245.social_medium.service.definition;

import com.animesh245.social_medium.entity.Attachment;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.List;

public interface IAttachmentService
{
    Attachment getAttachment(Long id);

    Attachment saveAttachment(CommonsMultipartFile file, String path) throws IOException;

    List<Attachment> insertInBulks(CommonsMultipartFile[] attachments);


}
