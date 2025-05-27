package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BBDDConfiguration {
    //Attributes
    private String url = "jdbc:mariadb://localhost:3306/programatrionExam3TBanco";
    private String user = "root";
    private String password = "";

    //Constructor
    public BBDDConfiguration(){};

    //Methods
    // getter & setters
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Probar conexión
    public int testConnection(){
        try (Connection connection = DriverManager.getConnection(getUrl(),getUser(),getPassword())){
            return 1;
        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        return 0;
    }
}
