package jm.task.core.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static Connection connect = null;


    public Util() {

    }
    private static Properties getAplication(){
        Properties properties = new Properties();
        try(InputStream in = Util.class.getClassLoader().getResourceAsStream("application.properties")){
            properties.load(in);

        }catch (IOException e){
            e.printStackTrace();
        }
        return properties;
    }

    public static Connection getconnect() {
        Properties properties = getAplication();
        try {
            connect = DriverManager.getConnection(properties.getProperty("db.url"),
                    properties.getProperty("db.name"),
                    properties.getProperty("db.password"));
            if (connect != null) {
                System.out.println("Connect");// подключие синглтоном посмотреть

            } else {
                System.out.println("Disconnect");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return  connect;
    }
}
/*public class Util {
    // реализуйте настройку соеденения с БД
    public static Connection connection = null;
    public static Connection getConnection() throws SQLException {

        try {

            Driver myDrive = new org.postgresql.Driver();
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String username = "postgres";
            String password = "7212";
            Connection connection = DriverManager.getConnection(url, username, password);
            if (connection != null){
                System.out.println("вввв");

            }
else System.out.println("аа");
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return connection;
    }

}*/
