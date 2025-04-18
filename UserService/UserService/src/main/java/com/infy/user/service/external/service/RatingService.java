package com.infy.user.service.external.service;

import com.infy.user.service.dto.RatingDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

    @GetMapping(path = "/ratings/user/{userId}")
    List<RatingDTO> getRatingsOfUser(@PathVariable(name = "userId") String userId);
}
