
package com.flipkart.dao;

import com.flipkart.bean.FlipFitUser;
import java.util.List;

public interface FlipFitUserDAOInterface {
    public void addUser(FlipFitUser user);
    public FlipFitUser getUserById(int id);
    public List<FlipFitUser> getAllUsers();
    public void updateUser(FlipFitUser user);
    public void deleteUser(int id);
}
