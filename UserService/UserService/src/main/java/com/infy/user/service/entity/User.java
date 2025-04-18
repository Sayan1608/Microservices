package com.infy.user.service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_DETAILS")
public class User {
    @Id
    @Column(name = "USER_ID")
    private String userId;
    private String userName;
    private String userEmail;
    private String about;
    @JsonFormat(pattern="MM/dd/yyyy")
    private LocalDate userDob;
    @Transient
    private List<Rating> ratingList=new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(userName, user.userName) && Objects.equals(userEmail, user.userEmail) && Objects.equals(about, user.about) && Objects.equals(userDob, user.userDob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, userEmail, about, userDob);
    }


}
