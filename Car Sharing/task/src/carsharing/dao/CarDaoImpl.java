package carsharing.dao;

import carsharing.dao.model.Car;
import carsharing.dao.model.Company;
import carsharing.utils.DbUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDaoImpl implements CarDao{

    private final DbUtil dbUtil = DbUtil.getDbUtil();

    @Override
    public List<Car> getCarsByCompany(Company company) {
        Optional<ResultSet> optionalResultSet = dbUtil.executeQuery("SELECT * FROM CAR " +
                "WHERE COMPANY_ID = '" + company.getID() + "'" +
                "ORDER BY ID;");
        return optionalResultSet.map(this::carBuilder).orElse(new ArrayList<>());
    }

    @Override
    public Optional<Car> getCarById(int id) {
        String query = "SELECT * FROM CAR WHERE ID = %d;".formatted(id);
        Optional<ResultSet> optionalResultSet = dbUtil.executeQuery(query);
        if (optionalResultSet.isEmpty()) return Optional.empty();
        ResultSet resultSet = optionalResultSet.get();
        Car car = null;
        try {
            while (resultSet.next()) {
                car = new Car(resultSet.getInt("ID"), resultSet.getString("NAME"),
                        resultSet.getInt("COMPANY_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(car);
    }

    @Override
    public int insertCar(Car car) {
        return dbUtil.executeUpdate("INSERT INTO CAR (NAME, COMPANY_ID)" +
                "VALUES ('" + car.getName() + "', '" + car.getCompanyId() + "');");
    }

    @Override
    public List<Car> getCompanyAvailableCars(Company company) {
        Optional<ResultSet> optionalResultSet = dbUtil.executeQuery("SELECT * FROM CAR " +
                "LEFT JOIN CUSTOMER ON CAR.ID = CUSTOMER.RENTED_CAR_ID " +
                "WHERE CAR.COMPANY_ID = '" + company.getID() + "' " +
                "AND CUSTOMER.ID IS NULL " +
                "ORDER BY CAR.ID;");
        return optionalResultSet.map(this::carBuilder).orElse(new ArrayList<>());
    }

    private List<Car> carBuilder(ResultSet resultSet) {
        List<Car> cars = new ArrayList<>();
        try {
            while (resultSet.next()) {
                cars.add(new Car(resultSet.getInt("ID"), resultSet.getString("NAME"),
                        resultSet.getInt("COMPANY_ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.copyOf(cars);
    }
}
