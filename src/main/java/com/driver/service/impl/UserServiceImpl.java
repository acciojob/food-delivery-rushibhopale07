package com.driver.service.impl;


import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.model.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl {

    @Autowired
    UserRepository userRepository;
    public UserResponse getUser( String id) throws Exception{


        return null;
    }

  List<UserResponse> userResponseList = new ArrayList<>();
    public UserResponse createUser( UserDetailsRequestModel userDetails) throws Exception{

        UserEntity user = new UserEntity();
        user.setEmail(userDetails.getEmail());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setUserId(userDetails.getUserId());
        userRepository.save(user);

        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getUserId());
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponseList.add(userResponse);
        return userResponse;
    }


    public UserResponse updateUser( String id, UserDetailsRequestModel userDetails) throws Exception{

        UserEntity user = userRepository.findByUserId(id);
        if(user==null)
            throw new Exception("User Not Found");

        user.setEmail(userDetails.getEmail());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setUserId(userDetails.getUserId());
        userRepository.save(user);

        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getUserId());
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponseList.add(userResponse);
        return userResponse;
    }


    public OperationStatusModel deleteUser( String id) throws Exception{
        OperationStatusModel operationStatusModel = new OperationStatusModel();
        operationStatusModel.setOperationName(RequestOperationName.DELETE.toString());

        UserEntity user = userRepository.findByUserId(id);
        userRepository.delete(user);

        operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.toString());
        return operationStatusModel;
    }


    public List<UserResponse> getUsers(){

        return userResponseList;
    }

}