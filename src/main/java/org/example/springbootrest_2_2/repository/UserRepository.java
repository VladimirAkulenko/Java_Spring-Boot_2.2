package org.example.springbootrest_2_2.repository;

import org.example.springbootrest_2_2.model.Authorities;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class UserRepository {
    public List<Authorities> getUserAuthorities(String user, String password) {
        List<Authorities> authorities = new ArrayList<>();
        if (user.equals("admin") && password.equals("0000")) {
            Collections.addAll(authorities, Authorities.READ, Authorities.WRITE, Authorities.DELETE);
        } else if (user.equals("user1") && password.equals("1111")) {
            Collections.addAll(authorities, Authorities.READ, Authorities.WRITE);
        } else if (user.equals("user2") && password.equals("2222")) {
            Collections.addAll(authorities, Authorities.READ);
        }
        return authorities;
    }
}
