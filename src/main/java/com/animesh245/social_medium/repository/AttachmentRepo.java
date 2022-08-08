package com.animesh245.social_medium.repository;

import com.animesh245.social_medium.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepo extends JpaRepository<Attachment, Long> {
}
