package com.animesh245.social_medium.service.implementaion;

import com.animesh245.social_medium.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsrDetails implements UserDetailsService
{
    private final UserRepo userRepo;

    public UsrDetails(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException
    {
        var user = userRepo.findByEmailId(emailId);

        if(user == null)
        {
            throw new UsernameNotFoundException(emailId);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(), user.getAuthorities());
    }
}
