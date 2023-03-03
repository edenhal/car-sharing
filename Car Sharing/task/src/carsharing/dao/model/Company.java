package carsharing.dao.model;

public class Company implements Comparable<Company> {

    private int ID;
    private String name;

    public Company(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Company anotherCompany) {
        if (this.ID == anotherCompany.getID()) {
            return this.name.compareTo(anotherCompany.name);
        }
        return this.ID - anotherCompany.getID();
    }

    @Override
    public String toString() {
        return name;
    }
}
