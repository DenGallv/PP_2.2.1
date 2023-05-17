package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        Query<User> query = sessionFactory.getCurrentSession().createQuery("FROM User", User.class);
        return query.list();
    }

    @Override
    public User getUserByCarModelAndSeries(String model, int series) {

        String HQL = "FROM User WHERE userCar.model = :carModel AND userCar.series = :carSeries";

        Query<User> query = sessionFactory.getCurrentSession().createQuery(HQL, User.class);
        query.setParameter("carModel", model);
        query.setParameter("carSeries", series);

        return query.uniqueResult();
    }

}
