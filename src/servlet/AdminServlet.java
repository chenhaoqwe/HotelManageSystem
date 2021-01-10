package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javabean.Admin;

@WebServlet("/admin/adminServlet")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // 增加Servlet过滤器，统一设置form的post请求编码方式为UTF-8
        // 修改Tomcat配置文件，设置get请求的编码格式为UTF-8
        System.out.println("in AdminServlet doGet()");
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
        // 调用JavaBean对象Admin的对象，完成获取数据库admin表全部记录的操作
        Admin admin = new Admin();
        List<Admin> all = admin.listAll();
        request.setAttribute("allAdmins", all);
        // System.out.println(all);
        request.getRequestDispatcher("/admin/listAll.jsp").forward(request, response);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        Admin admin = new Admin();
        admin.setId(id);

        int changedLines = admin.delete();

        if (changedLines > 0) {
            // admin=admin.get(1);
            response.sendRedirect("adminServlet?action=listAll");
        } else {
            response.sendRedirect("/admin/deleteFail.jsp");
        }

    }

    public void toUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        Admin admin = new Admin();
        admin = admin.getAdminById(id);
        if (admin != null) {
            request.setAttribute("admin", admin);
            request.getRequestDispatcher("/admin/update.jsp").forward(request, response);
        }
    }

    public void doUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        int age = Integer.parseInt(request.getParameter("age"));
        Admin admin = new Admin();
        admin.setId(id);
        admin.setName(name);
        admin.setPassword(password);
        admin.setAge(age);
        int changedLines = admin.update();
        if (changedLines > 0) {
            response.sendRedirect("adminServlet?action=listAll");
        } else {
            response.sendRedirect("admin/updateFail.jsp");
        }
    }

    public void toInsert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/admin/insert.jsp").forward(request, response);
    }

    public void doInsert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        int age = Integer.parseInt(request.getParameter("age"));
        Admin admin = new Admin();
        admin.setName(name);
        admin.setPassword(password);
        admin.setAge(age);
        int generatedId = admin.insert();
        if (generatedId > 0) {
            response.sendRedirect("adminServlet?action=listAll");
        } else {
            response.sendRedirect("/admin/insertFail.jsp");
        }
    }
}
