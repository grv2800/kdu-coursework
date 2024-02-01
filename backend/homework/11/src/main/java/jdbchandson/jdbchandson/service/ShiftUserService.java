package jdbchandson.jdbchandson.service;

import jdbchandson.jdbchandson.dao.ShiftUserDao;
import jdbchandson.jdbchandson.dto.ShiftUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiftUserService {

    private final ShiftUserDao shiftUserDao;

    @Autowired
    public ShiftUserService(ShiftUserDao shiftUserDao) {
        this.shiftUserDao = shiftUserDao;
    }

    public void saveShiftUser(ShiftUserDto shiftUserDto) {
        shiftUserDao.saveShiftUser(shiftUserDto);
    }
}
