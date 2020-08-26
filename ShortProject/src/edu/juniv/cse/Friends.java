package edu.juniv.cse;

import java.sql.*;
import java.util.Scanner;

public class Friends extends PersonInfo {

    private int frId;
    private String newmail;
    private int whomId;
    private String sms;
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public Friends() {
    }

    public Friends(int frId,int whomId) {
        this.frId = frId;
        this.whomId=whomId;
    }



    public Friends(String newmail, String sms) {
        this.newmail = newmail;
        this.sms=sms;
    }
    public Friends(int UserId, String FName, String LName, String Email, String DoB, String Password, int frId) {
        super(UserId, FName, LName, Email, DoB, Password);
        this.frId = frId;
    }

    public int getFrId() {
        return frId;
    }

    public void setFrId(int frId) {
        this.frId = frId;
    }

    public String getNewmail() {
        return newmail;
    }

    public void setNewmail(String newmail) {
        this.newmail = newmail;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public int getWhomId() {
        return whomId;
    }

    public void setWhomId(int whomId) {
        this.whomId = whomId;
    }

    public String getEmail() {
        return super.getEmail();
    }

    public void setEmail(String email) {
        super.setEmail(email);
    }

    public int getUserId() {
        return super.getUserId();
    }

    public void setUserId(int UserId) {
        super.setUserId(UserId);
    }

    public void insertData() {
        super.insertData();
    }

    public void showData() {
        super.showData();
    }

    public void ShowAll() {
        super.ShowAll();
    }
    //ArrayList<Friends> Info= new ArrayList<>();

    public void sendReq() {
        try {
            Scanner cin = new Scanner(System.in);

            System.out.print("Enter User Id: ");
            frId = cin.nextInt();


            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/application";
            String userName = "root";
            String password = "";
            connect = DriverManager.getConnection(url, userName, password);
            statement = connect.createStatement();

            preparedStatement = connect.prepareStatement("INSERT INTO SFriend (IdEmail,SentId) values (?,?)");

            preparedStatement.setString(1, getEmail());
            preparedStatement.setInt(2, getFrId());

            preparedStatement.executeUpdate();

            System.out.println("Request Sent.");
            System.out.println(" ");

            connect.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void sendmessage(){
        try {
            Scanner cin = new Scanner(System.in);

            System.out.print("Enter User Id: ");
            whomId = cin.nextInt();

            System.out.println("Message: ");
            sms=cin.next();

            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/application";
            String userName = "root";
            String password = "";
            connect = DriverManager.getConnection(url, userName, password);
            statement = connect.createStatement();

            preparedStatement = connect.prepareStatement("INSERT INTO message (whoEmail,whomId,message,seen) values (?,?,?,false)");

            preparedStatement.setString(1, getEmail());
            preparedStatement.setInt(2, getWhomId());
            preparedStatement.setString(3,getSms());

            preparedStatement.executeUpdate();

            System.out.println("Message Sent.");
            System.out.println(" ");

            connect.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sentmessage(){
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

            statement = connect.createStatement();   //CreateStatement
            String X = "SELECT * FROM message WHERE whoEmail= '" + getEmail() + "'";
            resultSet = statement.executeQuery(X);
            if (resultSet.next() == false) {
                System.out.println("No messages.");
                //System.exit(0);
            } else {
                System.out.println("**Messages**");
                resultSet = statement.executeQuery(X);
                while (resultSet.next()) {
                    System.out.println("To: "+resultSet.getInt("whomId")+"\nMessage: "+resultSet.getString("message")+"\nUnseen(false),Seen(true): "+resultSet.getBoolean("seen"));
                }
            }

            System.out.println(" ");
            connect.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewmessage(){
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

            statement = connect.createStatement();   //CreateStatement

            String GG="update message set seen=true where whomId= "+getUserId();
            statement.executeUpdate(GG);


            statement = connect.createStatement();   //CreateStatement
            String X = "SELECT * FROM message WHERE whomId= " + getUserId();
            resultSet = statement.executeQuery(X);
            if (resultSet.next() == false) {
                System.out.println("No messages.");
                //System.exit(0);
            } else {
                System.out.println("**Messages**");
                resultSet = statement.executeQuery(X);
                while (resultSet.next()) {
                    System.out.println("\nFrom: "+resultSet.getString("whoEmail")+"\nMessage: "+resultSet.getString("message"));
                }
            }

            connect.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void ShowReq() {
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

            statement = connect.createStatement();   //CreateStatement
            String X = "SELECT DISTINCT * FROM SFriend WHERE IdEmail= '" + getEmail() + "'";
            resultSet = statement.executeQuery(X);
            if (resultSet.next() == false) {
                System.out.println("No pending Request.");
                //System.exit(0);
            } else {
                System.out.println("**Pending Requests**");
                resultSet = statement.executeQuery(X);
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt("SentId"));
                }
            }

            connect.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void getReq() {
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

            int flag = 0;

            statement = connect.createStatement();   //CreateStatement
            String X = "select distinct * from person,SFriend where person.Email=SFriend.IdEmail and SentId= " + getUserId();

            resultSet = statement.executeQuery(X);
            if (resultSet.next() == false) {
                System.out.println("No received Request.");
                //System.exit(0);
            } else {
                flag = 1;
                System.out.println("**Received Requests**");
                resultSet = statement.executeQuery(X);
                while (resultSet.next()) {
                    System.out.println("User Id: " + resultSet.getInt("UserId") + "\nName: " + resultSet.getString("FName") + " " + resultSet.getString("LName")+"\nEmail: "+resultSet.getString("Email"));
                }
            }

            if (flag != 0) {
                int ww;
                Scanner cin = new Scanner(System.in);
                System.out.println("Add friend?(1.Yes/2.No): ");
                ww = cin.nextInt();
                if (ww != 2) {

                    System.out.print("Enter User Mail: ");
                    newmail = cin.next();

                    connect = DriverManager.getConnection(url, userName, password);
                    statement = connect.createStatement();

                    String AA = "delete from SFriend where IdEmail= " + "'" + getNewmail() + "'" + " and SentId= " + getUserId();
                    //preparedStatement = connect.prepareStatement(AA);
                    //preparedStatement.execute();
                    statement.executeUpdate(AA);

                    connect = DriverManager.getConnection(url, userName, password);
                    statement = connect.createStatement();

                    preparedStatement = connect.prepareStatement("INSERT INTO Friend (UEmail,FId) values (?,?)");

                    preparedStatement.setString(1, getNewmail());
                    preparedStatement.setInt(2, getUserId());

                    preparedStatement.executeUpdate();

                    System.out.println("Request Accepted.");

                }
            }


            connect.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int ShowFriend() {
        int flag=0;
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

            statement = connect.createStatement();   //CreateStatement
            String X = "select distinct * from person,Friend where person.UserId=Friend.FId and UEmail= '" + getEmail() + "'";
            String Y = "select distinct * from person,Friend where person.Email=Friend.UEmail and FId= "+getUserId() ;
            resultSet = statement.executeQuery(X);
            if (resultSet.next() == false) {
                resultSet = statement.executeQuery(Y);
                if(resultSet.next()==false){
                    System.out.println("No Friend.");
                    //System.exit(0);
                    flag=0;
                }
                else{
                    System.out.println("**Friends**");
                    resultSet = statement.executeQuery(Y);
                    while (resultSet.next()) {
                        System.out.println(resultSet.getInt("UserId") + " " + resultSet.getString("FName") + " " + resultSet.getString("LName"));
                    }
                    flag=1;
                }

            } else {
                System.out.println("**Friends**");
                resultSet = statement.executeQuery(X);
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt("UserId") + " " + resultSet.getString("FName") + " " + resultSet.getString("LName"));
                }
                flag=1;
            }

            connect.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }


    public void logout(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");   //LoadDriver

            String url = "jdbc:mysql://localhost:3306/application";
            String userName = "root";
            String password = "";

            connect = DriverManager.getConnection(url, userName, password);
            statement = connect.createStatement();

            String AA = "delete from Login";
            //preparedStatement = connect.prepareStatement(AA);
            //preparedStatement.execute();
            statement.executeUpdate(AA);
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dup(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");   //LoadDriver

            String url = "jdbc:mysql://localhost:3306/application";
            String userName = "root";
            String password = "";

            connect = DriverManager.getConnection(url, userName, password);
            statement = connect.createStatement();

            String AA = "delete t1 from Friend t1 inner join Friend t2 where t1.UEmail=t2.UEmail and t1.FId=t2.FId";
            //preparedStatement = connect.prepareStatement(AA);
            //preparedStatement.execute();
            statement.executeUpdate(AA);
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
