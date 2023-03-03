package carsharing.dao;

import carsharing.utils.DbUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VersionDaoImpl implements VersionDao {

    private final DbUtil dbUtil = DbUtil.getDbUtil();

    @Override
    public List<String> getVersions() {
        List<String> versions = new ArrayList<>();
        Optional<ResultSet> optionalResultSet = dbUtil.executeQuery("SELECT * FROM VERSION ORDER BY ID;");
        if (optionalResultSet.isPresent()) {
            ResultSet resultSet = optionalResultSet.get();
            try {
                while (resultSet.next()) {
                    versions.add(resultSet.getString("VERSION_NAME"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return versions;
    }

    @Override
    public int insertNewVersion(String versionName) {
        return dbUtil.executeUpdate("INSERT INTO VERSION (VERSION_NAME)" +
                "VALUES ('" + versionName + "');");
    }
}
