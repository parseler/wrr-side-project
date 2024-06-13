package com.gruns.wrr.auth.service;

import com.gruns.wrr.auth.dto.GoogleOAuth2Response;
import com.gruns.wrr.auth.dto.OAuth2Response;
import com.gruns.wrr.user.entity.UserEntity;
import com.gruns.wrr.user.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("oAuth2User = " + oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("google")) {
            oAuth2Response = new GoogleOAuth2Response(oAuth2User.getAttributes());
        } else {
            return null;
        }

        String username = oAuth2Response.getProvider() + "_" + oAuth2Response.getProviderId();

        UserEntity user = userRepository.findByUsername(username);
        String role = "ROLE_USER";

        if (user == null) {
            user = UserEntity.builder()
                    .email(oAuth2Response.getEmail())
                    .name(oAuth2Response.getName())
                    .profileImageUrl(oAuth2Response.getProfileImageUrl())
                    .role("ROLE_USER")
                    .createdDate(LocalDate.now())
                    .username(username)
                    .build();
        } else {
            user.updateProfile(oAuth2Response.getProfileImageUrl(), oAuth2Response.getName());
        }
        userRepository.save(user);

        return oAuth2User;
    }
}
