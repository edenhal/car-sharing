package carsharing.dao.model;

public class Car {

    private int ID;
    private String name;
    private int companyId;

    public Car(int ID, String name, int companyId) {
        this.ID = ID;
        this.name = name;
        this.companyId = companyId;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return name;
    }
}
