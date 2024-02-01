package jdbchandson.jdbchandson.service;

import jdbchandson.jdbchandson.dto.AllEntitiesDto;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Data
public class AllEntitiesService {

    private final ShiftTypeService shiftTypeService;
    private final ShiftService shiftService;
    private final UserService userService;
    private final ShiftUserService shiftUserService;
    private final TenantService tenantService;

    @Autowired
    public AllEntitiesService(
            ShiftTypeService shiftTypeService,
            ShiftService shiftService,
            UserService userService,
            ShiftUserService shiftUserService,
            TenantService tenantService) {
        this.shiftTypeService = shiftTypeService;
        this.shiftService = shiftService;
        this.userService = userService;
        this.shiftUserService = shiftUserService;
        this.tenantService = tenantService;
    }

    @Transactional
    public void saveAllEntities(AllEntitiesDto allEntitiesDTO) {
        shiftTypeService.saveShiftType(allEntitiesDTO.getShiftTypeDto());
        shiftService.saveShift(allEntitiesDTO.getShiftDto());
        userService.saveUser(allEntitiesDTO.getUserDto());
        shiftUserService.saveShiftUser(allEntitiesDTO.getShiftUserDto());
    }
}
