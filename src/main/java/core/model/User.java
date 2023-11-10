package core.model;

import data.RuntimeData;
import org.json.simple.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Field;

public class User implements Serializable {
    private String id;
    private String name;
    private String age;
    private String salary;

    public User(String name, String age, String salary, String id) {
        this.name = name;
        this.age = age;
        this.salary = salary;

        RuntimeData.addUser(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }

    public String toJsonString() {
        JSONObject json = new JSONObject();
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for(Field field: fields) {
                if (field.get(this) == null ) continue;
                    json.put(field.getName(), field.get(this));
            }
        } catch (IllegalAccessException e){
                e.printStackTrace();
            }
        return json.toJSONString();
    }
}
