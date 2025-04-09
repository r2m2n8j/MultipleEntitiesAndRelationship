package com.social.media;

import com.social.media.model.Post;
import com.social.media.model.SocialGroup;
import com.social.media.model.SocialProfile;
import com.social.media.model.SocialUser;
import com.social.media.repositories.PostRepository;
import com.social.media.repositories.SocialProfileRepository;
import com.social.media.repositories.SocialUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataInitializer {

    private final SocialUserRepository socialUserRepository;
    private final SocialProfileRepository socialProfileRepository;
    private final PostRepository postRepository;

    public DataInitializer(SocialUserRepository socialUserRepository, SocialProfileRepository socialProfileRepository, PostRepository postRepository) {
        this.socialUserRepository = socialUserRepository;
        this.socialProfileRepository = socialProfileRepository;
        this.postRepository = postRepository;
    }

    @Bean
    public CommandLineRunner initializeData(){
        return args -> {
            // Create some SocialUsers
            SocialUser user = new SocialUser();
            user.setName("Ramanuj");

            // Create some SocialProfiles
            SocialProfile profile = new SocialProfile();
            profile.setPhone("123456789");
            profile.setAddress("Ladugama Madhubani Bihar");
            profile.setSocialUser(user);

            // Create some Posts
            Post post1 = new Post();
            post1.setName("First Post");
            post1.setSocialUser(user);

            Post post2 = new Post();
            post2.setName("Second Post");
            post2.setSocialUser(user);

            user.setSocialProfile(profile);
            user.setPosts(Arrays.asList(post1, post2));

            // Create Groups
            SocialGroup socialGroup1 = new SocialGroup();
            SocialGroup socialGroup2 = new SocialGroup();
            socialGroup1.setGroupName("Java");
            socialGroup2.setGroupName("Spring boot");

            // connect user and groups
            user.getGroups().add(socialGroup1);
            user.getGroups().add(socialGroup2);

            //Save data inside the DB
            socialUserRepository.save(user); // Only this line save Everything
            /** Why only save(user) not save(profile) and save(post)?
             * Because we have already set cascade = CascadeType.All
             * So Profile and posts will also be saved automatically.
             * */

        };
    }
}
