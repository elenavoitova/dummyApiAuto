package data;


import core.model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;


import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public abstract class Provider {

    @DataProvider(name = "users-data")
    public static Object[][] getUsers() {
        Object[][] data = null;
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonData = (JSONObject) parser.parse(new FileReader("src/main/java/data/users.json"));
            JSONArray usersJsonArr = (JSONArray) jsonData.get("users");
            List<User> users = new ArrayList<>();
            for (Object obj : usersJsonArr) {
                JSONObject userObj = (JSONObject) obj;
                User userItem = new User(userObj.get("name").toString(), userObj.get("age").toString(), userObj.get("salary").toString(), null);
                users.add(userItem);
            }
            data = new Object[users.size()][1];
            int index = 0;
            for (Object[] o: data) {
                o[0] = users.get(index++);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        checkData(data);

        return data;
    }

    @DataProvider(name = "created-employees")
    public static Object[][] getCreated(){
        Object[][] data = new Object[RuntimeData.getUsersPool().size()][1];
        int index = 0;
        for (Object[] o: data) {
            o[0] = RuntimeData.getUsersPool().get(index++);
        }

        checkData(data);

        return data;
    }


    public static void checkData(Object[][] d){
        if(d == null || d.length == 0) throw new RuntimeException ("No input data from " + Provider.class.getName());
    }



}
