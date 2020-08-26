package edu.juniv.cse;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public static void main(String[] args) {
        // write your code here
        Scanner cin = new Scanner(System.in);
        int ch;
        do {

            Friends ff = new Friends();
            //PersonInfo obj=new PersonInfo();
            int op;
            System.out.print("Enter 1 to Login\n" + "Enter 2 to Sign Up\nEnter 3 to exit\n" + "Enter a choice: ");
            op = cin.nextInt();
            System.out.println(" ");
            switch (op) {
                case 1:
                    ff.showData();
                    System.out.println(" ");
                    ff.ShowAll();
                    System.out.println(" ");
                    int str;
                    do {
                        int p=0;
                        System.out.print("Enter 1 to view friends\n" + "Enter 2 to send friend request\n" + "Enter 3 to view pending requests\n" + "Enter 4 to view received requests\nEnter 5 to LogOut\n" + "Enter a choice: ");
                        int op1;
                        op1 = cin.nextInt();
                        System.out.println(" ");
                        switch (op1) {
                            case 1:
                                int ll;
                                ll=ff.ShowFriend();
                                System.out.println(" ");
                                if(ll==1){


                                    System.out.print("Enter 1 to send message\nEnter 2 to view sent message\nEnter 3 to view received messages\nEnter a choice: ");
                                    int dd;
                                    dd=cin.nextInt();
                                    switch (dd){
                                        case 1:
                                            ff.sendmessage();
                                            break;
                                        case 2:
                                            ff.sentmessage();
                                            break;
                                        case 3:
                                            ff.viewmessage();
                                            break;
                                        default:
                                            break;
                                    }
                                }





                                break;
                            case 2:
                                ff.sendReq();
                                break;
                            case 3:
                                ff.ShowReq();
                                break;
                            case 4:
                                ff.getReq();
                                //ff.dup();
                                break;
                            case 5:
                                ff.logout();
                                p++;
                                break;
                            default:
                                ff.logout();
                                System.exit(0);
                                break;
                        }

                        if(p==0){
                            System.out.println(" ");
                            System.out.print("Continue?(1.Yes/2.No): ");
                            str = cin.nextInt();
                            System.out.println(" ");
                        }
                        else{
                            break;
                        }
                    } while (str != 2);

                    break;
                case 2:
                    ff.insertData();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.exit(0);
                    break;
            }

            System.out.print("Login/Sign Up again?(1.Yes/2.No): ");
            ch = cin.nextInt();
            System.out.println(" ");
        } while (ch != 2);

    }
}
