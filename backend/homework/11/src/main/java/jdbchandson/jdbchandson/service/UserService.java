package jdbchandson.jdbchandson.service;

import jdbchandson.jdbchandson.dao.UserDao;
import jdbchandson.jdbchandson.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void saveUser(UserDto userDTO) {
        userDao.saveUser(userDTO);
    }
}
