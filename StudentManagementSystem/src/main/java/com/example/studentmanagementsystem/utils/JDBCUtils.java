package com.example.studentmanagementsystem.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {

    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    //通过静态代码块，来预先执行读取配置文件的配置项，做预处理。
    static {
        try{
            //类加载器:JDBCUtils.class.getClassLoader();
            //InputStream inputStream = ClassLoader.getSystemResourceAsStream("com/example/studentmanagementsystem/stu_db.properties");

            Properties properties = new Properties();
            try (InputStream inputStream = JDBCUtils.class.getResourceAsStream("/com/example/studentmanagementsystem/stu_db.properties")) {
                if (inputStream == null) {
                    throw new RuntimeException("jdbc.properties 文件未找到，请检查路径！");
                }
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //properties.load(JDBCUtils.class.getResourceAsStream("com/example/studentmanagementsystem/stu_db.properties"));

            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");

           // System.out.println("url:" + url + "user:" + user + "password:" + password + "driver:"  + driver);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void test(){
        System.out.println("使用！");
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void close(Connection connection, PreparedStatement preparedStatement) throws SQLException {
        if(preparedStatement != null){
            preparedStatement.close();
            preparedStatement = null;
        }
        if(connection != null){
            connection = null;
        }
    }

    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet ) throws SQLException {
        if(preparedStatement != null){
            preparedStatement.close();
            preparedStatement = null;
        }
        if(connection != null){
            connection.close();
            connection = null;
        }
        if(resultSet != null){
            resultSet.close();
            resultSet = null;
        }
    }
}
