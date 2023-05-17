package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");

        user1.setUserCar(new Car("BMW", 111));
        userService.add(user1);

        user2.setUserCar(new Car("MB", 222));
        userService.add(user2);

        user3.setUserCar(new Car("Audi", 333));
        userService.add(user3);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user.getId().toString());
            System.out.println(user.getFirstName().toString());
            System.out.println(user.getLastName().toString());
            System.out.println(user.getEmail().toString());
            System.out.println(user.getUserCar().toString());
        }

        System.out.println("Пользователи из БД: ");
        User userFromDB1 = userService.getUserByCarModelAndSeries("BMW", 111);
        System.out.println(userFromDB1);

        User userFromDB2 = userService.getUserByCarModelAndSeries("Audi", 333);
        System.out.println(userFromDB2);

        User userFromDB3 = userService.getUserByCarModelAndSeries("MB", 222);
        System.out.println(userFromDB3);

        context.close();
    }
}
