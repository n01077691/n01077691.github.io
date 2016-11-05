/*
name: Denis Stepanov
student ID: n01077691
assignment #: 2
*/

package denis.stepanov;

public class DataProvider {

    private String firstname;
    private String lastName;
    private String healthID;
    private String gender;
    private String department;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHealthID(){
        return healthID;
    }

    public void setHealthID(String healthID){
        this.healthID = healthID;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender){this.gender = gender;}

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public DataProvider(String firstname, String lastName, String gender, String healthID, String department)
    {
        this.firstname = firstname;
        this.lastName = lastName;
        this.gender = gender;
        this.healthID = healthID;
        this.department = department;
    }

}
