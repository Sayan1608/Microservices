package com.infy.user.service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.user.service.dto.RatingDTO;
import com.infy.user.service.dto.UserDTO;
import com.infy.user.service.entity.Rating;
import com.infy.user.service.entity.User;
import com.infy.user.service.exceptions.ResourceNotFoundException;
import com.infy.user.service.external.service.RatingService;
import com.infy.user.service.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    private final RestTemplate restTemplate;

    @Autowired
    private RatingService ratingService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDTO createNewUser(UserDTO userDto) {
        String uuid = UUID.randomUUID().toString();
        userDto.setUserId(uuid);
        User savedUser = userRepository.saveAndFlush(modelMapper.map(userDto, User.class));
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public UserDTO getUserById(String userId) {
        existUser(userId);

        List<RatingDTO> ratings = getRatingsOfUser(userId);
        logger.info("{}",ratings);
        UserDTO user = modelMapper.map(userRepository
                        .findById(userId)
                        .orElseThrow(() -> new RuntimeException("Something Went wrong while fetching user in method getUserById in UserServiceImpl class ")),
                UserDTO.class);
        List<Rating> ratingsList = getRatingList(ratings);
        user.setRatingList(ratingsList);
        return user;
    }

    private List<Rating> getRatingList(List<RatingDTO> ratings) {
        List<Rating> ratingsList = ratings
                .stream()
                .map(ratingDTO ->modelMapper.map(ratingDTO, Rating.class)).collect(Collectors.toList());
        return ratingsList;
    }

    private void existUser(String userId) {
        if(!userRepository.existsById(userId)){
            throw new ResourceNotFoundException("User with Id : " + userId + " not found!");
        }
    }

    @Override
    public UserDTO getUserByName(String userName) {
        Optional<User> byUserName = userRepository.findByUserName(userName);
        return modelMapper.map(byUserName
                        .orElseThrow(()->new RuntimeException("Something Went wrong while fetching user in method getUserByName in UserServiceImpl class ")),
                UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        List<UserDTO> userDTOList = allUsers.stream().map(user -> modelMapper.map(user, UserDTO.class)).toList();
        userDTOList.forEach(userDTO -> {
            List<RatingDTO> ratingsOfUser = getRatingsOfUser(userDTO.getUserId());
            userDTO.setRatingList(getRatingList(ratingsOfUser));
        });
        return userDTOList;
    }

    @Override
    public UserDTO updateUserById(String userId, UserDTO userDto) {
        existUser(userId);
        userDto.setUserId(userId);
        User updatedUser = userRepository.saveAndFlush(modelMapper.map(userDto, User.class));
        return modelMapper.map(updatedUser, UserDTO.class);
    }

    @Override
    public void deleteUser(String userId) {
        existUser(userId);
        userRepository.deleteById(userId);
    }

    public List<RatingDTO> getRatingsOfUser(String userId){
        String url = "http://RATINGSERVICE/ratings/user/"+userId;
        /*ResponseEntity<List<RatingDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RatingDTO>>(){}
        );*/
        List<RatingDTO> ratingsOfUser = ratingService.getRatingsOfUser(userId);
        return ratingsOfUser;
    }
}
