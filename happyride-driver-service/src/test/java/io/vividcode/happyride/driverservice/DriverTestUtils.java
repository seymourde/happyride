package io.vividcode.happyride.driverservice;

import com.github.javafaker.Faker;
import io.vividcode.happyride.driverservice.api.web.CreateDriverRequest;
import io.vividcode.happyride.driverservice.api.web.CreateVehicleRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Component;

@Component
public class DriverTestUtils {

  private final Faker faker = new Faker(Locale.CHINA);

  public CreateDriverRequest buildCreateDriverRequest(int numberOfVehicles) {
    CreateDriverRequest request = new CreateDriverRequest();
    request.setName(faker.name().name());
    request.setEmail(faker.internet().emailAddress());
    request.setMobilePhoneNumber(faker.phoneNumber().phoneNumber());
    int count = Math.max(0, numberOfVehicles);
    List<CreateVehicleRequest> vehicles = new ArrayList<>(count);
    for (int i = 0; i < count; i++) {
      vehicles.add(buildCreateVehicleRequest());
    }
    request.setVehicles(vehicles);
    return request;
  }

  public CreateVehicleRequest buildCreateVehicleRequest() {
    CreateVehicleRequest request = new CreateVehicleRequest();
    request.setMake(fakerString(60));
    request.setMode(fakerString(60));
    request.setYear(vehicleYear());
    request.setRegistration(fakerString(32));
    return request;
  }

  private String fakerString(int maxChars) {
    return faker.lorem().characters(1, maxChars);
  }

  private int vehicleYear() {
    return faker.number().numberBetween(1980, 2020);
  }
}
