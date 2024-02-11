package jdbchandson.jdbchandson.dto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AllEntitiesDto {
    private ShiftTypeDto shiftTypeDto;
    private ShiftDto shiftDto;
    private UserDto userDto;
    private ShiftUserDto shiftUserDto;
}
