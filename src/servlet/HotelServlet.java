package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javabean.Hotel;

@WebServlet("/hotel/hotelServlet")
public class HotelServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // 增加Servlet过滤器，统一设置form的post请求编码方式为UTF-8
        // 修改Tomcat配置文件，设置get请求的编码格式为UTF-8
        System.out.println("in HotelServlet doGet()");
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
        // 调用JavaBean对象Hotel的对象，完成获取数据库hotel表全部记录的操作
        Hotel hotel = new Hotel();
        List<Hotel> all = hotel.listAll();
        request.setAttribute("allHotels", all);
        // System.out.println(all);
        request.getRequestDispatcher("/hotel/listAll.jsp").forward(request, response);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        Hotel hotel = new Hotel();
        hotel.setId(id);

        int changedLines = hotel.delete();

        if (changedLines > 0) {
            // hotel=hotel.get(1);
            response.sendRedirect("hotelServlet?action=listAll");
        } else {
            response.sendRedirect("/hotel/deleteFail.jsp");
        }

    }

    public void toUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        Hotel hotel = new Hotel();
        hotel = hotel.getHotelById(id);
        if (hotel != null) {
            request.setAttribute("hotel", hotel);
            request.getRequestDispatcher("/hotel/update.jsp").forward(request, response);
        }
    }

    public void doUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        String name = request.getParameter("name");
        String state = request.getParameter("state");
        Hotel hotel = new Hotel();
        hotel.setId(id);
        hotel.setName(name);
        hotel.setState(state);
        int changedLines = hotel.update();
        if (changedLines > 0) {
            response.sendRedirect("hotelServlet?action=listAll");
        } else {
            response.sendRedirect("/hotel/updateFail.jsp");
        }
    }

    public void toInsert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/hotel/insert.jsp").forward(request, response);
    }

    public void doInsert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String state = request.getParameter("state");
        Hotel hotel = new Hotel();
        hotel.setName(name);
        hotel.setState(state);
        int generatedId = hotel.insert();
        if (generatedId > 0) {
            response.sendRedirect("hotelServlet?action=listAll");
        } else {
            response.sendRedirect("/hotel/insertFail.jsp");
        }
    }
}
