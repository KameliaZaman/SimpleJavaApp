package edu.juniv.cse;

import java.sql.*;
import java.util.Scanner;

public class PersonInfo {
    private int UserId;
    private String FName;
    private String LName;
    private String Email;
    private String DoB;
    private String Password;
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    PersonInfo() {
    }

    PersonInfo(int UserId, String FName, String LName, String Email, String DoB, String Password) {
        this.UserId = UserId;
        this.FName = FName;
        this.LName = LName;
        this.Email = Email;
        this.DoB = DoB;
        this.Password = Password;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDoB() {
        return DoB;
    }

    public void setDoB(String doB) {
        DoB = doB;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void insertData() {

        try {
            Scanner cin = new Scanner(System.in);

            System.out.print("Enter User Id: ");
            UserId = cin.nextInt();
            System.out.print("Enter First Name: ");
            FName = cin.next();
            System.out.print("Enter Last Name: ");
            LName = cin.next();
            System.out.print("Enter Email: ");
            Email = cin.next();
            System.out.print("Enter Date of Birth: ");
            DoB = cin.next();
            System.out.print("Enter Password: ");
            Password = cin.next();


            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/application";
            String userName = "root";
            String password = "";
            connect = DriverManager.getConnection(url, userName, password);
            statement = connect.createStatement();

            preparedStatement = connect.prepareStatement("INSERT INTO person (UserId,FName,LName,Email,DoB,Password) values (?,?,?,?,?,?)");
            preparedStatement.setInt(1, getUserId());
            preparedStatement.setString(2, getFName());
            preparedStatement.setString(3, getLName());
            preparedStatement.setString(4, getEmail());
            preparedStatement.setString(5, getDoB());
            preparedStatement.setString(6, getPassword());

            preparedStatement.executeUpdate();

            System.out.println("Account created.");

            connect.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public void showData() {
        try {

            Scanner cin = new Scanner(System.in);

            System.out.print("Enter User Id: ");
            UserId = cin.nextInt();
            System.out.print("Enter Email: ");
            Email = cin.next();
            System.out.print("Enter Password: ");
            Password = cin.next();

            Class.forName("com.mysql.cj.jdbc.Driver");   //LoadDriver

            String url = "jdbc:mysql://localhost:3306/application";
            String userName = "root";
            String password = "";
            connect = DriverManager.getConnection(url, userName, password);  //CreateConnection

            if (connect.isClosed()) {
                System.out.println("Not Connected");
            } else {
                System.out.println("Connected");
            }
            statement = connect.createStatement();   //CreateStatement


            preparedStatement = connect.prepareStatement("INSERT INTO Login (uid,eMail,passWord) values (?,?,?)");
            preparedStatement.setInt(1, getUserId());
            preparedStatement.setString(2, getEmail());
            preparedStatement.setString(3, getPassword());

            preparedStatement.executeUpdate();

            statement = connect.createStatement();   //CreateStatement

            String Q = "select distinct * from person WHERE UserId = '" + getUserId() + "' AND Email = '" + getEmail() + "' AND Password = '" + getPassword() + "'";
            resultSet = statement.executeQuery(Q);
            if(resultSet.next()==false){
                System.out.println("Account doesn't exist");
                System.exit(0);
            }
            else{
                System.out.println("\n");
                System.out.println("**Profile**");
                //System.out.println("First Name"+" Last Name"+" Date of Birth");
                resultSet = statement.executeQuery(Q);
                while (resultSet.next()) {
                    System.out.println("First Name: "+resultSet.getString("FName") + "\nLast Name: " + resultSet.getString("LName") + "\nDate of Birth: " + resultSet.getString("DoB")+"\nEmail: "+resultSet.getString("Email"));
                }
            }


            connect.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void ShowAll(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");   //LoadDriver

            String url = "jdbc:mysql://localhost:3306/application";
            String userName = "root";
            String password = "";
            connect = DriverManager.getConnection(url, userName, password);  //CreateConnection

            if (connect.isClosed()) {
                System.out.println("Not Connected");
            } else {
                System.out.println("Connected");
            }
            System.out.println("**All Users**");
            statement = connect.createStatement();   //CreateStatement
            resultSet = statement.executeQuery("SELECT DISTINCT * FROM person WHERE NOT UserId= "+getUserId());
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("UserId") + " " + resultSet.getString("FName") + " " + resultSet.getString("LName")+" "+resultSet.getString("Email"));
            }

            connect.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
