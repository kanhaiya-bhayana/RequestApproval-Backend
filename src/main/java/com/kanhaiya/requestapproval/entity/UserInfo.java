package com.kanhaiya.requestapproval.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "userinfo")
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class UserInfo extends BaseEntity {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;

}
