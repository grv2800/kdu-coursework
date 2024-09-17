package mapper;

import com.example.handson.dto.VehicleDto;
import com.example.handson.entity.Vehicle;

public class VehicleMapper {
    VehicleMapper() {}

    public static VehicleDto mapTovehicleDto(Vehicle vehicle){
        return new VehicleDto(
                vehicle.getName(),
                vehicle.getPrice()
        );
    }
    public static Vehicle mapDtoToVehicle(VehicleDto vehicledto){
        return new Vehicle(
                vehicledto.getName(),
                vehicledto.getPrice()
        );
    }

}
