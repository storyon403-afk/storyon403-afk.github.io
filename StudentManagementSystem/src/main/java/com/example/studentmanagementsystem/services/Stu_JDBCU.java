package com.example.studentmanagementsystem.services;

import com.example.studentmanagementsystem.utils.JDBCUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Stu_JDBCU {
    public static Connection connection;
    public static PreparedStatement preparedstatement;
    public static ResultSet resultSet;

    // 添加学生
    public boolean addStudent(Student stu) {
        String sql = "INSERT INTO students (id, name, age, sex, address) VALUES (?, ?, ?, ?, ?)";
        try {
            connection = JDBCUtils.getConnection();
            preparedstatement = connection.prepareStatement(sql);
            preparedstatement.setInt(1, stu.getId());
            preparedstatement.setString(2, stu.getName());
            preparedstatement.setInt(3, stu.getAge());
            preparedstatement.setBoolean(4, stu.getSex());
            preparedstatement.setString(5, stu.getAddress());
            return preparedstatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                JDBCUtils.close(connection,preparedstatement);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 更新学生
    public boolean updateStudent(Student stu) {
        String sql = "UPDATE students SET name=?, age=?, sex=?, address=? WHERE id=?";
        try  {
            connection = JDBCUtils.getConnection();
            preparedstatement = connection.prepareStatement(sql);
            preparedstatement.setString(1, stu.getName());
            preparedstatement.setInt(2, stu.getAge());
            preparedstatement.setBoolean(3, stu.getSex());
            preparedstatement.setString(4, stu.getAddress());
            preparedstatement.setInt(5, stu.getId());
            return preparedstatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                JDBCUtils.close(connection,preparedstatement);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 删除学生
    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id=?";
        try  {
            connection = JDBCUtils.getConnection();
            preparedstatement = connection.prepareStatement(sql);
            preparedstatement.setInt(1, id);
            return preparedstatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                JDBCUtils.close(connection,preparedstatement);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 查询全部
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try  {
            connection = JDBCUtils.getConnection();
            preparedstatement = connection.prepareStatement(sql);
            resultSet = preparedstatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getBoolean("sex"),
                        resultSet.getString("address")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                JDBCUtils.close(connection,preparedstatement,resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    // 按学号查询
    public Student getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE id=?";
        try {
            connection = JDBCUtils.getConnection();
            preparedstatement = connection.prepareStatement(sql);
            preparedstatement.setInt(1, id);
            try {
                resultSet = preparedstatement.executeQuery();
                if (resultSet.next()) {
                    return new Student(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getBoolean("sex"),
                            resultSet.getString("address")
                    );
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                JDBCUtils.close(connection,preparedstatement,resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    // 按姓名模糊查询
    public List<Student> searchStudentsByName(String name) {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE name LIKE ?";
        try  {
            connection = JDBCUtils.getConnection();
            preparedstatement = connection.prepareStatement(sql);
            preparedstatement.setString(1, "%" + name + "%");
            try  {
                resultSet = preparedstatement.executeQuery();
                while (resultSet.next()) {
                    list.add(new Student(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getBoolean("sex"),
                            resultSet.getString("address")
                    ));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                JDBCUtils.close(connection,preparedstatement,resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }
}
