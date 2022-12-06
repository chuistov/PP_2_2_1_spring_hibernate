package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      Query query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void add(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   public List<User> getUserByCarDetails(String model, int series) {
      String hql = "from User as user " +
                   "join Car as car on user.id = car.user " +
                   "where car.model = :model and car.series = :series";
      Query query = sessionFactory.getCurrentSession().createQuery(hql);
      query.setParameter("model", model);
      query.setParameter("series", series);
      return query.list();
   }
}
