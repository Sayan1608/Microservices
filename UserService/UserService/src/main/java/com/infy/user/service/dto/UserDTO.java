package com.infy.user.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.infy.user.service.entity.Rating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String userId;
    private String userName;
    private String userEmail;
    private String about;
   @JsonFormat(pattern="MM/dd/yyyy")
   private LocalDate userDob;
   private List<Rating> ratingList;
}
