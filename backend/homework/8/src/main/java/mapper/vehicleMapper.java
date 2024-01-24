package mapper;

import com.example.HandsOn.dto.vehicleDto;
import com.example.HandsOn.entity.vehicle;

public class vehicleMapper {
    vehicleMapper() {}

    public static vehicleDto mapTovehicleDto(vehicle vehicle){
        return new vehicleDto(
                vehicle.getName(),
                vehicle.getPrice()
        );
    }
    public static vehicle mapDtoToVehicle(vehicleDto vehicledto){
        return new vehicle(
                vehicledto.getName(),
                vehicledto.getPrice()
        );
    }

}
