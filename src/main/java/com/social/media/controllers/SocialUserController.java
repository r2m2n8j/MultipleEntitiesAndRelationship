package com.social.media.controllers;

import com.social.media.model.SocialUser;
import com.social.media.services.SocialUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SocialUserController {

    @Autowired
    private SocialUserServiceImpl socialUserService;

    @GetMapping("/social/users")
    public ResponseEntity<List<SocialUser>> getAllSocialUsers(){
        return new ResponseEntity<>(socialUserService.getAllSocialUsers(),HttpStatus.OK);
    }

    @PostMapping("/social/users")
    public ResponseEntity<SocialUser> saveSocialUser(@RequestBody SocialUser socialUser){
        return new ResponseEntity<>(socialUserService.saveSocialUser(socialUser),HttpStatus.CREATED);
    }
}
