package search;
import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnector {
   static Connection connection = null;

   static void getDBConnection() {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
         return;
      }

      connection = null;
      try {
         UtilProp.loadProperty();
         connection = DriverManager.getConnection(getURL(), getUserName(), getPassword());
      } catch (Exception e) {
         e.printStackTrace();
         return;
      }

   }

   static String getURL() {
      String url = UtilProp.getProp("url");
      return url;
   }

   static String getUserName() {
      String usr = UtilProp.getProp("user");
      return usr;
   }

   static String getPassword() {
      String pwd = UtilProp.getProp("password");
      return pwd;
   }
}
