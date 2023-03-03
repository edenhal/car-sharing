package carsharing.dao;

import carsharing.dao.model.Customer;
import carsharing.utils.DbUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDaoImpl implements CustomerDao {

    private final DbUtil dbUtil = DbUtil.getDbUtil();

    @Override
    public int insertCustomer(String name) {
        return dbUtil.executeUpdate("INSERT INTO CUSTOMER (NAME)" +
                "VALUES ('" + name + "');");
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        Optional<ResultSet> optionalResultSet = dbUtil.executeQuery("SELECT * FROM CUSTOMER ORDER BY ID");
        if (optionalResultSet.isPresent()) {
            ResultSet resultSet = optionalResultSet.get();
            try {
                while (resultSet.next()) {
                    customerList.add(new Customer(resultSet.getInt("ID"), resultSet.getString("NAME"),
                            resultSet.getInt("RENTED_CAR_ID")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return List.copyOf(customerList);
    }

    @Override
    public int setRentedCarIdToNull(int customerId) {
        return dbUtil.executeUpdate("UPDATE CUSTOMER SET RENTED_CAR_ID = NULL WHERE ID = " + customerId + ";");
    }

    @Override
    public int setRentedCarId(int customerId, int carId) {
        return dbUtil.executeUpdate("UPDATE CUSTOMER SET RENTED_CAR_ID = " + carId + " WHERE ID = "
                + customerId + ";");
    }
}
