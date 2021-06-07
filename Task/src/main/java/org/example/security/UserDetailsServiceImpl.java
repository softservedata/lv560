package org.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.example.model.User;
import org.example.dao.UserDao;

import javax.transaction.Transactional;

@Service("myUserService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    @Autowired
    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        User user = userDao.findByName(string);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        } else {
            return SecurityUserDetails.fromUser(user);
        }
    }
}