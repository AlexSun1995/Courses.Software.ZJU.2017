package cst.zju.edu.alexsun.Dao;

import cst.zju.edu.alexsun.model.User;

public interface IUserDao {
    void addUser(User user);
    User getUserByName(String userName);
    void delete(User user);
}
