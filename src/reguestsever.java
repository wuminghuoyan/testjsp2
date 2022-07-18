
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/request")
public class reguestsever extends HttpServlet {
     DataSource dataSource=DruidUtil.getDataSource();
     QueryRunner qr=new QueryRunner(dataSource);


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map map=req.getParameterMap();
        user user=new user();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        String sql="insert into `user`(`user`,`password`)VALUES(?,?);";
        try {
            qr.update(sql,user.username,user.password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
