
package com.flipkart.dao;

import com.flipkart.bean.FlipFitUser;
import java.util.List;

public interface FlipFitUserDAOInterface {
    void addUser(FlipFitUser user);
    FlipFitUser getUserById(int id);
    List<FlipFitUser> getAllUsers();
    void updateUser(FlipFitUser user);
    void deleteUser(int id);
}
