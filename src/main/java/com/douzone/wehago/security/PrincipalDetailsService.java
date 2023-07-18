package com.douzone.wehago.security;

import com.douzone.wehago.domain.User;
import com.douzone.wehago.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        Optional<User> existUser = Optional.ofNullable(userRepository.findUser(userId));
        return existUser
                .map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException("회원정보를 찾을 수 없습니다."));
    }
}
