package com.stackroute.userservices.service;

import com.stackroute.userservices.domain.User;
import com.stackroute.userservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {



    private UserRepository userRepository;

    //Constructor

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserServiceImpl() {

    }

     // Get the List of user

    public List<User> getListOfUser(){

        List<User> userList = (List<User>) userRepository.findAll();
        return userList;

    }

   public User getUserById(int id){
        Optional<User> getUserById = userRepository.findById(id);
        if(getUserById != null){
            return getUserById.get();
        }
        else{
            return null;
        }
   }

    // Saved User to the database

    public User saveUser(User user){

        User savedUser = userRepository.save(user);
        return  savedUser;
    }

    public User updateUser(User user,int userId){

        Boolean isUserWithIDExists = userRepository.existsById(userId);
        if(isUserWithIDExists){
            User updateUser = (User) userRepository.findById(userId).get();
            updateUser.setAge(user.getAge());
            updateUser.setGender(user.getGender());
            updateUser.setName(user.getName());
            return  userRepository.save(updateUser);
        }
        else{
            return null;
        }

    }


    // Update user info

   public User deleteUser(int userId){
        User deletedUser = (User) userRepository.findById(userId).get();
       userRepository.deleteById(userId);
        return deletedUser;
   }


}
