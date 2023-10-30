package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory travel = Util.getCon();


    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try {
            Session session = travel.openSession();
            session.getTransaction().begin();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS Users" +
                    "(id SERIAL primary key, name varchar(20), lastName varchar(20), age INT)").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица создана");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = travel.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createSQLQuery("Drop table if exists Users").executeUpdate();
            transaction.commit();
            System.out.println("Таблица дропнута");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = travel.openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("FROM User WHERE name = :name");
        query.setParameter("name", name);
        User existingUser = (User) query.uniqueResult();
        if (existingUser == null) {
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            System.out.println("пользователь создан");
        } else {
            System.out.println("пользователь с таким именем уже существует");
        }
        session.close();
    }


    @Override
    public void removeUserById(long id) {
        try (Session session = travel.openSession()) {
            session.getTransaction().begin();

            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Session session = travel.openSession();
        session.getTransaction().begin();
        list = session.createCriteria(User.class).list();
        session.getTransaction().commit();
        session.close();
        System.out.println(list);
        return list;
    }


    @Override
    public void cleanUsersTable() {
        Session session = null;
        try {
            session = travel.openSession();
            session.getTransaction().begin();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица очищена");
        } catch (Exception e) {
            System.out.println("Ошибка при очистке таблицы: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
