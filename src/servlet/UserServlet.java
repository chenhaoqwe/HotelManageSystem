package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javabean.User;

@WebServlet("/user/userServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // 增加Servlet过滤器，统一设置form的post请求编码方式为UTF-8
        // 修改Tomcat配置文件，设置get请求的编码格式为UTF-8
        System.out.println("in UserServlet doGet()");
        String action = request.getParameter("action");
        System.out.println(action);
        if (action.equals("listAll")) {
            listAll(request, response);
        } else if (action.equals("doDelete")) {
            doDelete(request, response);
        } else if (action.equals("toUpdate")) {
            toUpdate(request, response);
        } else if (action.equals("doUpdate")) {
            doUpdate(request, response);
        } else if (action.equals("toInsert")) {
            toInsert(request, response);
        } else if (action.equals("doInsert")) {
            doInsert(request, response);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 调用JavaBean对象User的对象，完成获取数据库user表全部记录的操作
        User user = new User();
        List<User> all = user.listAll();
        request.setAttribute("allUsers", all);
        // System.out.println(all);
        request.getRequestDispatcher("/user/listAll.jsp").forward(request, response);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        User user = new User();
        user.setId(id);

        int changedLines = user.delete();

        if (changedLines > 0) {
            // user=user.get(1);
            response.sendRedirect("userServlet?action=listAll");
        } else {
            response.sendRedirect("/user/deleteFail.jsp");
        }

    }

    public void toUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        User user = new User();
        user = user.getUserById(id);
        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/user/update.jsp").forward(request, response);
        }
    }

    public void doUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        int age = Integer.parseInt(request.getParameter("age"));
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        user.setAge(age);
        int changedLines = user.update();
        if (changedLines > 0) {
            response.sendRedirect("userServlet?action=listAll");
        } else {
            response.sendRedirect("user/updateFail.jsp");
        }
    }

    public void toInsert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/user/insert.jsp").forward(request, response);
    }

    public void doInsert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        int age = Integer.parseInt(request.getParameter("age"));
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setAge(age);
        int generatedId = user.insert();
        if (generatedId > 0) {
            response.sendRedirect("userServlet?action=listAll");
        } else {
            response.sendRedirect("/user/insertFail.jsp");
        }
    }
}
