package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getCon().openSession();) {
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS Users" +
                    "(id SERIAL primary key, name varchar(20), lastName varchar(20), age INT)").executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Таблица создана");
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getCon().openSession();) {
            transaction = session.beginTransaction();
            session.createSQLQuery("Drop table if exists Users").executeUpdate();
            transaction.commit();
            session.close();
            System.out.println("Таблица дропнута");
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getCon().openSession();) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM User WHERE name = :name");
            query.setParameter("name", name);
            User existingUser = (User) query.uniqueResult();
            if (existingUser == null) {
                session.save(new User(name, lastName, age));
                transaction.commit();
                System.out.println("пользователь создан");
            } else {
                System.out.println("пользователь с таким именем уже существует");
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getCon().openSession();) {
            session.getTransaction().begin();

            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        try (Session session = Util.getCon().openSession();) {
            List<User> list = new ArrayList<>();
            transaction = session.beginTransaction();
            list = session.createCriteria(User.class).list();
            transaction.commit();
            System.out.println(list);
            return list;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getCon().openSession();) {
            transaction = session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            transaction.commit();
            System.out.println("Таблица очищена");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Ошибка при очистке таблицы: " + e.getMessage());
        }
    }
}
