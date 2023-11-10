import core.RequestHandler;
import core.TestHooks;
import core.model.User;
import data.Provider;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Randomizer;


public class CRUD_EmployeesTests extends TestHooks {


    @Test(priority = 1, dataProvider = "users-data", dataProviderClass = Provider.class)
    public void createEmployeesTest(User user) {
        Response response = RequestHandler.createEmployeeRequest(user);
        if (response.getStatusCode() == 200) {

            String employeeID = response.jsonPath().getString("data.id");
            user.setId(employeeID);

            Assert.assertEquals(response.jsonPath().getString("data.name"), user.getName(), "Wrong name is set");
            Assert.assertEquals(response.jsonPath().getString("data.salary"), user.getSalary(), "Wrong salary is set");
            Assert.assertEquals(response.jsonPath().getString("data.age"), user.getAge(), "Wrong age is set");
            Assert.assertTrue(response.jsonPath().getString("message").contains("Record has been added"), "Wrong message shown");

        } else Assert.fail("User isn't created. Status " + response.getStatusCode());
    }

    @Test(priority = 2, dependsOnMethods = "createEmployeesTest", dataProvider = "created-employees", dataProviderClass = Provider.class)
    public void verifyEmployeeTest(User employee) {
        Response response = RequestHandler.getEmployeeRequest(employee);
        Assert.assertEquals(response.getStatusCode(), 200,"Wrong response status code");
        Assert.assertEquals(response.jsonPath().getString("data.employee_name"), employee.getName(), "Name does not match the created one");
        Assert.assertEquals(response.jsonPath().getString("data.employee_salary"), employee.getSalary(), "Salary does not match the created one");
        Assert.assertEquals(response.jsonPath().getString("data.employee_age"), employee.getAge(), "Age does not match the created one");
    }

    @Test(priority = 4)
    public void updateEmployeeTest() {
        User user = new Randomizer().randomUserFromPool();
        Response response = RequestHandler.updateEmployeeRequest(user);
        Assert.assertEquals(response.getStatusCode(), 200,"Wrong response status code");
        Assert.assertEquals(response.jsonPath().getString("data.name"), user.getName(), "Name is not updated");
        Assert.assertEquals(response.jsonPath().getString("data.salary"), user.getSalary(), "Salary is not updated");
        Assert.assertEquals(response.jsonPath().getString("data.age"), user.getAge(), "Age is not updated");
        Assert.assertTrue(response.jsonPath().getString("message").contains("Record has been updated"), "Wrong message shown");
    }

    @Test(priority = 5)
    public void deleteEmployeeTest() {
        Response response = RequestHandler.deleteEmployeeRequest(new Randomizer().randomUserFromPool());
        Assert.assertEquals(response.getStatusCode(), 200,"Wrong response status code");
        Assert.assertTrue(response.jsonPath().getString("message").contains("Record has been deleted"), "Wrong message shown");
    }

    @Test(priority = 6)
    public void deleteEmployeeErrorTest() {
        Response response = RequestHandler.deleteEmployeeRequest(new Randomizer().randomString(5));;
        Assert.assertEquals(response.getStatusCode(), 400,"Wrong response status code");
        Assert.assertTrue(response.jsonPath().getString("message").contains("Page Not found"), "Wrong message shown");
    }
}
