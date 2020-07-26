import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    public static String url;
    public static String user;
    public static String password;
    public static String drive;
    //获取配置文件，只需要读取一次
    static{
        try{
            Properties pro = new Properties();
            ClassLoader classLoader = JDBCUtils.class.getClassLoader();
            URL res = classLoader.getResource("jdbc.properties");
            String path = res.getPath();
            System.out.println(path);
            pro.load(new FileReader(path));
            url = pro.getProperty("url");
            user= pro.getProperty("user");
            password = pro.getProperty("password");
            drive = pro.getProperty("drive");

            Class.forName(drive);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //获取连接
    public static Connection getconnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

    //释放资源
    public static void close(ResultSet re, Statement stmt,Connection conn){
        if(re!=null){
            try{
                re.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(stmt!=null){
            try{
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(conn!=null){
            try{
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
