package com.animesh245.social_medium.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attachment_tbl")
public class Attachment
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "path")
    private String attachmentPath;

    @Column(name = "name")
    private String attachmentName;

    @Column(name = "type")
    private String attachmentType;
}
