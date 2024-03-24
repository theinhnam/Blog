package com.blog.blog.security;

import com.blog.blog.model.TaiKhoan;
import com.blog.blog.repositories.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserPasswordBlogAuthentication implements AuthenticationProvider {

    @Autowired
    TaiKhoanRepository taiKhoanRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        TaiKhoan taiKhoan = taiKhoanRepository.readByEmail(email);
        if (taiKhoan != null && taiKhoan.getId() > 0 && passwordEncoder.matches(pwd, taiKhoan.getPassword())) {
            return new UsernamePasswordAuthenticationToken(email, null, getAuthorityGranter(taiKhoan.getQuyen()));
        }else{
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    public List<GrantedAuthority> getAuthorityGranter(String role){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
