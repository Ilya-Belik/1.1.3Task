package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import net.bytebuddy.implementation.bytecode.Throw;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class Util {
    private static SessionFactory factory;
    public Util() {
    }
    public static SessionFactory getCon(){
        try {
            factory = new Configuration()
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        }catch (Throwable e){
            e.printStackTrace();
        }

        return factory;

    }
    public static void closeConnection() {
        if (factory != null)
            factory.close();
    }

}