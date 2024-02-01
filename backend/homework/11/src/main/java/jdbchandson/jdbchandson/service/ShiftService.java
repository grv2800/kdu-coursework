package jdbchandson.jdbchandson.service;

import jdbchandson.jdbchandson.dao.ShiftDao;
import jdbchandson.jdbchandson.dto.ShiftDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShiftService {

    private final ShiftDao shiftDao;


    @Autowired
    public ShiftService(ShiftDao shiftDao) {
        this.shiftDao = shiftDao;
    }

    public void saveShift(ShiftDto shiftDto) {
        shiftDao.saveShift(shiftDto);
    }

    public List<ShiftDto> getShift(UUID tenantID)
    {
        return shiftDao.getShift(tenantID);
    }

}
