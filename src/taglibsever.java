import cn.hutool.db.Session;
import com.sun.media.jfxmedia.events.NewFrameEvent;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/taglibsever")
public class taglibsever extends basesever {

     public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map map=req.getParameterMap();
       user inuser=new user();
        try {
            BeanUtils.populate(inuser,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        QueryRunner qr=new QueryRunner(DruidUtil.getDataSource());
        user user1=new user();
        String sql="select * from tbuser where username =? and password = ?";
        try {
           List list=qr.query(sql,new BeanListHandler<user>(user1.getClass()),inuser.getUsername(),inuser.getPassword());
           if(list.isEmpty())
               req.getRequestDispatcher("/error.jsp").forward(req,resp);
           else
           {
              HttpSession session=req.getSession();
              session.setAttribute("username",inuser.getUsername());
              sql="select * from student;";
              student students=new student();
              list=qr.query(sql,new BeanListHandler<student>(students.getClass()));
              session.setAttribute("students",list);
              req.getRequestDispatcher("/search.jsp").forward(req,resp);
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void deletestudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
         String sid=req.getParameter("sid");
        System.out.println(sid);
        String sql ="delete  from student where sid =?;";
        QueryRunner qr=new QueryRunner(DruidUtil.getDataSource());
        try {
            qr.update(sql,sid);
            searchstudentall(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void searchstudentall(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        student students=new student();
        QueryRunner qr=new QueryRunner(DruidUtil.getDataSource());
        String sql="select * from student;";
        List list= null;
        try {
            list = qr.query(sql,new BeanListHandler<student>(students.getClass()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HttpSession session=req.getSession();
        session.setAttribute("students",list);
        req.getRequestDispatcher("/search.jsp").forward(req,resp);
    }
    public void alterstudentstart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
         String sid=req.getParameter("sid");
         String sql="select * from student where sid=?";
         QueryRunner qr=new QueryRunner(DruidUtil.getDataSource());
         student student=new student();
        try {
            List students=qr.query(sql, new BeanListHandler<student>(student.getClass()),sid);
            HttpSession session=req.getSession();
            session.setAttribute("students",students);
            req.getRequestDispatcher("/alterstudent.jsp").forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void alterstudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Map map=new HashMap(req.getParameterMap());
        map.remove("method");
        student student=new student();
        try {
            BeanUtils.populate(student,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        String sql="update student set sname=?,sclass=?,sage=?,ssex=?where sid=?";
        QueryRunner qr=new QueryRunner(DruidUtil.getDataSource());
        try {
            qr.update(sql,student.sname,student.getSclass(),student.getSage(),student.getSsex(),student.getSid());
            searchstudentall(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addnewstudentstart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
         QueryRunner qr=new QueryRunner(DruidUtil.getDataSource());
         String sql="select * from student;";
         HttpSession session=req.getSession();
        try {
            List<String> sids=qr.query(sql,new ColumnListHandler<String>("sid"));
            session.setAttribute("sids",sids);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/addstudent.jsp").forward(req,resp);

    }
    public void addnewstudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Map map=new HashMap(req.getParameterMap());
        map.remove("method");
        student student=new student();
        try {
            BeanUtils.populate(student,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        String sql="insert into student (sid,sname,sclass,sage,ssex) values(?,?,?,?,?);";
        QueryRunner qr=new QueryRunner(DruidUtil.getDataSource());
        try {
            qr.update(sql,student.getSid(),student.getSname(),student.getSclass(),student.getSage(),student.getSsex());
            searchstudentall(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void init() throws ServletException {
        super.init();
    }
}

