package com.stussy.stussyclone20220930jugeon.security;

import com.stussy.stussyclone20220930jugeon.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private User user;

    public PrincipalDetails(User user) {
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //GrantedAuthority: 권한 관리
        Collection<GrantedAuthority> authorities = new ArrayList< GrantedAuthority>(); //ArrayList 업캐스팅?
        authorities.add(()-> user.getRole().getName());
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() { //기간 지났을 때, 휴면계정
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { //비밀번호
        return true; //ex> 비밀번호 5회 이상 오류면 false //세션말고 디비 이용해야됨
    }

    @Override
    public boolean isEnabled() { //비활성화
        return true;
    }
}
