package org.sivakamiveerapathiran.onlinenursery.security;
/***************************
 * Author: Sivakami Veerapathiran
 * Description: This Class contains the Security UserPrincipal  for the Project.
 ***************************/

import org.sivakamiveerapathiran.onlinenursery.models.RoleTable;
import org.sivakamiveerapathiran.onlinenursery.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {

    private User user;
    private List<RoleTable> roles;

    public UserPrincipal(User user, List<RoleTable> roles) {
        super();
        this.user = user;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser(){
        return  this.user;
    }
}
