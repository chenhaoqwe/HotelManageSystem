package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javabean.Notific;

@WebServlet("/notific/notificServlet")
public class NotificServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // 增加Servlet过滤器，统一设置form的post请求编码方式为UTF-8
        // 修改Tomcat配置文件，设置get请求的编码格式为UTF-8
        System.out.println("in NotificServlet doGet()");
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
        // 调用JavaBean对象Notific的对象，完成获取数据库notific表全部记录的操作
        Notific notific = new Notific();
        List<Notific> all = notific.listAll();
        request.setAttribute("allNotifics", all);
        // System.out.println(all);
        request.getRequestDispatcher("/notific/listAll.jsp").forward(request, response);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        Notific notific = new Notific();
        notific.setId(id);

        int changedLines = notific.delete();

        if (changedLines > 0) {
            // notific=notific.get(1);
            response.sendRedirect("notificServlet?action=listAll");
        } else {
            response.sendRedirect("/notific/deleteFail.jsp");
        }

    }

    public void toUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        Notific notific = new Notific();
        notific = notific.getNotificById(id);
        if (notific != null) {
            request.setAttribute("notific", notific);
            request.getRequestDispatcher("/notific/update.jsp").forward(request, response);
        }
    }

    public void doUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        String newscontent = request.getParameter("newscontent");
        String writer = request.getParameter("writer");
        Notific notific = new Notific();
        notific.setId(id);
        notific.setNewscontent(newscontent);
        notific.setWriter(writer);
        int changedLines = notific.update();
        if (changedLines > 0) {
            response.sendRedirect("notificServlet?action=listAll");
        } else {
            response.sendRedirect("/notific/updateFail.jsp");
        }
    }

    public void toInsert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/notific/insert.jsp").forward(request, response);
    }

    public void doInsert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String newscontent = request.getParameter("newscontent");
        String writer = request.getParameter("writer");
        Notific notific = new Notific();
        notific.setNewscontent(newscontent);
        notific.setWriter(writer);
        int generatedId = notific.insert();
        if (generatedId > 0) {
            response.sendRedirect("notificServlet?action=listAll");
        } else {
            response.sendRedirect("/notific/insertFail.jsp");
        }
    }
}
