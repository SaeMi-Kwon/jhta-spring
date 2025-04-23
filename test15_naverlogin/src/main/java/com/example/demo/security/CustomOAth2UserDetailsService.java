package com.example.demo.security;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomOAth2UserDetailsService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);

        Map<String,Object> map = user.getAttributes();
        System.out.println("request=====> " + map);

        Map<String,Object> resp = (Map<String,Object>)map.get("response");
        String email=(String)resp.get("email");
        String name=resp.get("name").toString();
        String provider = userRequest.getClientRegistration().getClientName();  //프로바이더
        System.out.println("provider ===> " + provider);

        String username= email + "_" + provider;
        String role="MEMBER";

        User member = userRepository.findByUsername(username);
        if(member==null){
            member = User.builder()
                    .username(username)
                    .email(email)
                    //비밀번호는 실제로 사용되지 않지만, 엔티티의 필수값이니까 "1111"을 암호화해서 대체로 넣음
                    .password(new BCryptPasswordEncoder().encode("1111"))
                    .role(role)
                    .provider(provider)
                    .build();

            System.out.println("member===> " + member);
            userRepository.save(member);
        }

        return new CustomUserDetails(member,user.getAttributes());
    }


}
