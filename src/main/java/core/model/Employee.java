package core.model;

import java.io.Serializable;

public class Employee implements Serializable {

    static final long serialVersionUID = 42L;

    private String profile_image;
    private String employee_name;
    private long employee_salary;
    private long employee_age;
    private long id;

    public Employee(String profile_image, String employee_name, long employee_salary, long employee_age, long id) {
        this.profile_image = profile_image;
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "profile_image='" + profile_image + '\'' +
                ", employee_name='" + employee_name + '\'' +
                ", employee_salary='" + employee_salary + '\'' +
                ", employee_age='" + employee_age + '\'' +
                ", id=" + id +
                '}';
    }
}
