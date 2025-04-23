package com.example.demo.security;

import com.example.demo.entity.BoardUser;
import com.example.demo.repository.BoardUserRepository;
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
    private BoardUserRepository boardUserRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);

        Map<String,Object> map = user.getAttributes();
        System.out.println("request=====> " + map);

        Map<String,Object> resp = (Map<String,Object>)map.get("response");
        String email=(String)resp.get("email");
        String phone=resp.get("mobile").toString();
        String provider = userRequest.getClientRegistration().getClientName();  //프로바이더
        System.out.println("provider ===> " + provider);

        String username= email + "_" + provider;
        String role="MEMBER";

        BoardUser member = boardUserRepository.findById(username);
        if(member==null){
            member = BoardUser.builder()
                    .id(username)
                    .email(email)
                    //비밀번호는 실제로 사용되지 않지만, 엔티티의 필수값이니까 "1111"을 암호화해서 대체로 넣음
                    .pwd(new BCryptPasswordEncoder().encode("1111"))
                    .role(role)
                    .phone(phone)
                    .provider(provider)
                    .build();

            System.out.println("member===> " + member);
            boardUserRepository.save(member);
        }

        return new CustomUserDetails(member,user.getAttributes());
    }


}
