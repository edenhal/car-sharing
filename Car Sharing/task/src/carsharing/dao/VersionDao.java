package carsharing.dao;

import java.util.List;

public interface VersionDao {

    List<String> getVersions();
    int insertNewVersion(String versionName);

}
