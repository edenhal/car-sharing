package carsharing.dao;

import carsharing.dao.model.Car;
import carsharing.dao.model.Company;

import java.util.List;
import java.util.Optional;

public interface CarDao {

    List<Car> getCarsByCompany(Company company);

    int insertCar(Car car);

    List<Car> getCompanyAvailableCars(Company company);

    Optional<Car> getCarById(int id);
}
