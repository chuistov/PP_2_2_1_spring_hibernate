package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Ivan", "Ivanov", "ivanov@gmail.com");
      User user2 = new User("Peter", "Sidorov", "sidorov@gmail.com");
      User user3 = new User("Maria", "Petrova", "petrova@gmail.com");

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);

      Car car1 = new Car("Tesla", 455, user1);
      Car car2 = new Car("Tesla", 455, user2);
      Car car3 = new Car("Lada", 5, user3);

      userService.add(car1);
      userService.add(car2);
      userService.add(car3);

      List<User> users = userService.getUsersByCarDetails("Tesla", 455);

      System.out.println("\nUsers owning the specified car model:");
      for (User user : users) {
         System.out.println(user);
      }
      System.out.println();

      context.close();
   }
}
