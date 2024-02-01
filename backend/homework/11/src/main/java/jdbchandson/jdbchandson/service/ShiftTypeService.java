package jdbchandson.jdbchandson.service;

import jdbchandson.jdbchandson.dao.ShiftTypeDao;
import jdbchandson.jdbchandson.dto.ShiftTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShiftTypeService {

    private final ShiftTypeDao shiftTypeDao;

    @Autowired
    public ShiftTypeService(ShiftTypeDao shiftTypeDao) {
        this.shiftTypeDao = shiftTypeDao;
    }

    public void saveShiftType(ShiftTypeDto shiftTypeDto) {
        shiftTypeDao.saveShiftType(shiftTypeDto);
    }

}
