package com.social.media.services;

import com.social.media.model.Post;
import com.social.media.model.SocialUser;
import com.social.media.repositories.SocialUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialUserServiceImpl {

    @Autowired
    private SocialUserRepository socialUserRepository;

    public List<SocialUser> getAllSocialUsers(){
        return socialUserRepository.findAll();
    }

    public SocialUser saveSocialUser(SocialUser socialUser){
        // Connect each Post with parent SocialUser
        for(Post post : socialUser.getPosts()){
            post.setSocialUser(socialUser);
        }
        return socialUserRepository.save(socialUser);
    }
}
