package ua.hryshko.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hryshko.dao.UserDAO;
import ua.hryshko.model.User;


@Service("userDetailsServiceImpl")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDAO userDAO;

    @Autowired
    public UserDetailsServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) {
        User user = userDAO.findByEmail(userEmail);
        if (user == null){
            throw new UsernameNotFoundException("User with given username not found");
        } else {
            return SecurityUser.create(user);
        }

    }
}