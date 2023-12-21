package com.jboss.spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.jboss.spring.beans.Userabc;
import com.jboss.spring.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

@Autowired
private UserRepository userRepository;

    public List<Userabc> getUsers() {
        List<Userabc> userList = userRepository.findAll();
        return userList;
    }

    public Userabc getUserByName(String name) {
      List<Userabc>  userabcList =  userRepository.findAllByFirstName(name.trim());
        Userabc userabc= new Userabc();
        if(!CollectionUtils.isEmpty(userabcList)){

            userabcList.get(0);

            userabc.setId(userabcList.get(0).getId());
            userabc.setFirstName(userabcList.get(0).getFirstName());
            userabc.setLastName(userabcList.get(0).getLastName());
            userabc.setEmail(userabcList.get(0).getEmail());
            userabc.setPassword(userabcList.get(0).getPassword());

            return userabc;
        }
        return null;
    }

    public String saveUser(Userabc user) {
        Userabc userabc = constructUser(user);

        userabc.setId(user.getId());
        userabc.setFirstName(user.getFirstName());
        userabc.setLastName(user.getLastName());
        userabc.setEmail(user.getEmail());
        userabc.setPassword(user.getPassword());

        Userabc response = userRepository.save(userabc);

        if (response != null) {
            return "User Added Successfully";
        }
        else
            return "Not able to Add the User";
    }

    private Userabc constructUser(Userabc user) {
        return new Userabc(user.getId(), user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword());
    }

    public String removeUser(String email) {

        List<Userabc> userList = userRepository.deleteByEmail(email);

           if(CollectionUtils.isEmpty(userList)){

            return "Removed User Successfully";
        }
        return "Not able to Remove the user";
    }
}
