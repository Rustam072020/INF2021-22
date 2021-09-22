package Models;

public class Account {
    private Integer id;
    private String firstName;
    private String lastname;
    private Integer age;

    public Account(Integer id, String firstName, String lastname, Integer age) {
        this.id = id;
        this.firstName = firstName;
        this.lastname = lastname;
        this.age = age;
    }

    public Account(String firstName, String lastname, Integer age) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.age = age;
    }

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) {this.lastname = lastname;}

    public Integer getAge() {return age;}

    public void setAge(Integer age) {this.age = age;}

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}
    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                '}';
    }
}

