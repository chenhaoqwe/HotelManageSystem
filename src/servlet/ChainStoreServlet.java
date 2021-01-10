package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.CsrfPreventionFilter;

import javabean.ChainStore;

@WebServlet("/chainStore/chainStoreServlet")
public class ChainStoreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // 增加Servlet过滤器，统一设置form的post请求编码方式为UTF-8
        // 修改Tomcat配置文件，设置get请求的编码格式为UTF-8
        System.out.println("in ChainStoreServlet doGet()");
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

    /**
     * 执行列出所有信息操作
     * 
     * @param request
     * @param response
     */
    public void listAll(HttpServletRequest request, HttpServletResponse response) {
        // 调用JavaBean对象ChainStore的对象，完成获取数据库chainStore表全部记录的操作
        ChainStore chainStore = new ChainStore();
        List<ChainStore> all = chainStore.listAll();
        request.setAttribute("allChainStores", all);
        // System.out.println(all);
        try {
            request.getRequestDispatcher("/chainStore/listAll.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行删除记录操作
     * 
     * @param request
     * @param response
     */
    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        ChainStore chainStore = new ChainStore();
        chainStore.setId(id);

        int changedLines = chainStore.delete();

        if (changedLines > 0) {
            // chainStore=chainStore.get(1);
            response.sendRedirect("chainStoreServlet?action=listAll");
        } else {
            response.sendRedirect("/chainStore/deleteFail.jsp");
        }

    }

    /**
     * 跳转到提供更新记录数据的页面
     * 
     * @param request
     * @param response
     */
    public void toUpdate(HttpServletRequest request, HttpServletResponse response) {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        ChainStore chainStore = new ChainStore();
        chainStore = chainStore.getChainStoreById(id);
        if (chainStore != null) {
            request.setAttribute("chainStore", chainStore);
            try {
                request.getRequestDispatcher("/chainStore/update.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 执行更新记录操作
     * 
     * @param request
     * @param response
     */
    public void doUpdate(HttpServletRequest request, HttpServletResponse response) {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        String name = request.getParameter("name");
        String location = request.getParameter("location");
        int roomcount = Integer.parseInt(request.getParameter("roomcount"));
        ChainStore chainStore = new ChainStore();
        chainStore.setId(id);
        chainStore.setName(name);
        chainStore.setLocation(location);
        chainStore.setRoomcount(roomcount);
        int changedLines = chainStore.update();
        if (changedLines > 0) {
            try {
                response.sendRedirect("chainStoreServlet?action=listAll");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                response.sendRedirect("chainStore/updateFail.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 跳转到提供插入记录数据的页面
     * 
     * @param request
     * @param response
     */
    public void toInsert(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/chainStore/insert.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行插入记录操作
     * 
     * @param request
     * @param response
     */
    public void doInsert(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String location = request.getParameter("location");
        int roomcount = Integer.parseInt(request.getParameter("roomcount"));
        ChainStore chainStore = new ChainStore();
        chainStore.setName(name);
        chainStore.setLocation(location);
        chainStore.setRoomcount(roomcount);
        int generatedId = chainStore.insert();
        if (generatedId > 0) {
            try {
                response.sendRedirect("chainStoreServlet?action=listAll");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                response.sendRedirect("/chainStore/insertFail.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
