package com.animesh245.social_medium.entity;

import com.animesh245.social_medium.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String emailId;

    @Column(name = "dob")
    private Date dateOfBirth;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @JoinColumn(name = "location_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY)
    private Location location;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attachment_id", referencedColumnName = "id")
    private Attachment attachment;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Status> statusList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Status> postsLiked;
}
