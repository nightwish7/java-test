import java.sql.*;
import java.util.Scanner;

public class jdbc_test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

//        Class.forName("com.mysql.cj.jdbc.Driver");
//        //获取数据库连接对象
//        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/asd?useUnicode=true&characterEncoding=utf-8","root","123456");
//        //sql语句
//        String sql = "update user set username='恋恋' where id = 41";
//        //获取执行sql对象
//        Statement stmt = conn.createStatement();
//        //执行sql，并接受返回结果
//        int count = stmt.executeUpdate(sql);
//        //处理结果
//        System.out.println(count);
//        //释放资源
//        stmt.close();
//        conn.close();

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = sc.nextLine();
        System.out.println("密码");
        String password = sc.nextLine();
        boolean loginFlag = new jdbc_test().login(username,password);
        if(loginFlag){
            System.out.println("登录成功");
        }else {System.out.println("登录失败");}
    }
    public boolean login(String username,String password) {
        if(username==null||password==null){
            return false;
        }
        Connection conn = null;
        Statement stmt =null;
        ResultSet rs =null;
        try{
            conn=JDBCUtils.getconnection();
            String sql = "select * from user where name = '"+username+"'and password ='"+password+"'";
            stmt = conn.createStatement();
            rs=stmt.executeQuery(sql);
            return rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(rs,stmt,conn);
        }
        return false;
    }
}
