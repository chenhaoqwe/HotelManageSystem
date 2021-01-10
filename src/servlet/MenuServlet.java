package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javabean.Menu;

@WebServlet("/menu/menuServlet")
public class MenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // 增加Servlet过滤器，统一设置form的post请求编码方式为UTF-8
        // 修改Tomcat配置文件，设置get请求的编码格式为UTF-8
        System.out.println("in MenuServlet doGet()");
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
        // 调用JavaBean对象Menu的对象，完成获取数据库menu表全部记录的操作
        Menu menu = new Menu();
        List<Menu> all = menu.listAll();
        request.setAttribute("allMenus", all);
        // System.out.println(all);
        request.getRequestDispatcher("/menu/listAll.jsp").forward(request, response);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        Menu menu = new Menu();
        menu.setId(id);

        int changedLines = menu.delete();

        if (changedLines > 0) {
            // menu=menu.get(1);
            response.sendRedirect("menuServlet?action=listAll");
        } else {
            response.sendRedirect("/menu/deleteFail.jsp");
        }

    }

    public void toUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        Menu menu = new Menu();
        menu = menu.getMenuById(id);
        if (menu != null) {
            request.setAttribute("menu", menu);
            request.getRequestDispatcher("/menu/update.jsp").forward(request, response);
        }
    }

    public void doUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        String name = request.getParameter("name");
        String info = request.getParameter("info");
        Menu menu = new Menu();
        menu.setId(id);
        menu.setName(name);
        menu.setInfo(info);
        int changedLines = menu.update();
        if (changedLines > 0) {
            response.sendRedirect("menuServlet?action=listAll");
        } else {
            response.sendRedirect("/menu/updateFail.jsp");
        }
    }

    public void toInsert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/menu/insert.jsp").forward(request, response);
    }

    public void doInsert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String info = request.getParameter("info");
        Menu menu = new Menu();
        menu.setName(name);
        menu.setInfo(info);
        int generatedId = menu.insert();
        if (generatedId > 0) {
            response.sendRedirect("menuServlet?action=listAll");
        } else {
            response.sendRedirect("/menu/insertFail.jsp");
        }
    }
}
