package jdbchandson.jdbchandson.dao;

import jdbchandson.jdbchandson.dto.AllEntitiesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllEntitiesDao {

    private final ShiftTypeDao shiftTypeDao;
    private final ShiftDao shiftDao;
    private final UserDao userDao;
    private final ShiftUserDao shiftUserDao;

    @Autowired
    public AllEntitiesDao(
            ShiftTypeDao shiftTypeDao,
            ShiftDao shiftDao,
            UserDao userDao,
            ShiftUserDao shiftUserDao
    ) {
        this.shiftTypeDao = shiftTypeDao;
        this.shiftDao = shiftDao;
        this.userDao = userDao;
        this.shiftUserDao = shiftUserDao;
    }

    public void saveAllEntities(AllEntitiesDto allEntitiesDTO) {
        shiftTypeDao.saveShiftType(allEntitiesDTO.getShiftTypeDto());
        shiftDao.saveShift(allEntitiesDTO.getShiftDto());
        userDao.saveUser(allEntitiesDTO.getUserDto());
        shiftUserDao.saveShiftUser(allEntitiesDTO.getShiftUserDto());
    }

}
