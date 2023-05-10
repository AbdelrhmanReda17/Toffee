import ControllerClasses.ApplicationController;
import ControllerClasses.OrderController;
import ControllerClasses.UserController;

public class Main {
    public static void main(String[] args) {
        OrderController orderController = new OrderController();
        UserController userController = new UserController();
        ApplicationController app = new ApplicationController(orderController,userController);
        app.StartApplication();
    }
}
