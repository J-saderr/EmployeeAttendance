package Connection;

import javafx.scene.chart.PieChart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {


    public static Connection connectDb(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "TamNguyencute"); // address, database username, database password
            return connect;
        }catch(Exception e){e.printStackTrace();}
        return null;
    }
}
