import core.model.Employee;
import core.RequestHandler;
import core.TestHooks;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Serializer;

import java.util.ArrayList;
import java.util.List;


public class SerializeEmployeesTest extends TestHooks{

    @Test()
    public void serializeEmployeeTest() {
        Response response = RequestHandler.getEmployeesRequest();
        Assert.assertEquals(response.getStatusCode(), 200,"Wrong response status code");
        Assert.assertTrue(response.jsonPath().getString("message").contains("All records has been fetched"), "Wrong message shown");
        System.out.println(response.jsonPath().getList("data").size());
        Assert.assertFalse(response.jsonPath().getList("data").isEmpty());

        String employeesResponse = response.getBody().asString();
        JSONParser parser = new JSONParser();
        List<Employee> employees = new ArrayList<>();

        try {
            JSONObject jsonData = (JSONObject) parser.parse(employeesResponse);
            JSONArray dataArray = (JSONArray) jsonData.get("data");
            for (Object obj : dataArray) {
                JSONObject employeeObj = (JSONObject) obj;
                Employee employee = new Employee((String) employeeObj.get("profile_image"),
                        (String) employeeObj.get("employee_name"),
                        (long) employeeObj.get("employee_salary"),
                        (long) employeeObj.get("employee_age"),
                        (long) employeeObj.get("id"));
                employees.add(employee);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

            Serializer.serializeObjectList(employees, ".\\server.txt");


    }
}
