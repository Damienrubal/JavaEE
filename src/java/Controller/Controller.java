package Controller;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dams
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

   
    private boolean result = false;
    private String login,name,value,id;
    private List<String>message;
    
    //@EJB
    //private StatelessApplication stateLessBean;
 
    @Override
    public void init(){  
    }

    public void destroy(){
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        init();
        StatefullApplication stateFullBean;
       //HttpSession session = request.getSession(false);
        HttpSession session = request.getSession();       
            if (session.isNew()) {
         
            System.out.println("NEW session: " + session.getId() + "<br/>");
            stateFullBean = getNewAccountBean();
            System.out.println("NEW bean: " + stateFullBean.getBeanName() + "<br/>");
            session.setAttribute("fullBean", stateFullBean);
            } else {
         
            System.out.println("Session: " + session.getId() + "<br/>");
            stateFullBean = (StatefullApplication) session.getAttribute("fullBean");
            System.out.println("Bean:" + stateFullBean.getBeanName() + "<br/>");
            }

            //for a new session , start on  LogIN.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("LogIn.jsp");
            
            // Get the active button. "value" of the name "button"
            String button_param = request.getParameter("button");
      
       
            //Connecting
            if ("Connect".equals(button_param)) {
                login = request.getParameter("login");
                String password = request.getParameter("password");
                if (!("".equals(login) || "".equals(password))) { 
                     // order to use it in Process.jsp
                     session.setAttribute("Login",login);
                    //result=stateLessBean.Connected(login, password);
                    result=stateFullBean.Connected(login, password);
                    
                    // result= true , if  login and password work
                    if (result) {
                        //List<String>message = stateLessBean.SetUp();
                        message = stateFullBean.SetUp();
                        // order to use it in process.jsp
                        request.setAttribute("sqlData",message);
                        dispatcher = request.getRequestDispatcher("Process.jsp");
                    } else {
                        dispatcher = request.getRequestDispatcher("LogOut.jsp");
                      } 
                }
            }
            
            //To connect
            if ("Login".equals(button_param)) {
                dispatcher = request.getRequestDispatcher("LogIn.jsp");
            }
            
            //To disconnect
            if ("Logout".equals(button_param)) {
               // session.invalidate();
                dispatcher = request.getRequestDispatcher("LogOut.jsp");
            }
            
            //To insert data in the  mysql Database
            if("Insert".equals(button_param)){
                    name = request.getParameter("name");
                    value = request.getParameter("val"); 
                    //result =stateLessBean.Insert(login,name,value);
                     
                    //result=true,if the insertion works
                    result =stateFullBean.Insert(login,name,value);
                   
                    if (result){                   
                   // List<String>message = stateLessBean.SetUp();
                    message = stateFullBean.SetUp();                    
                    request.setAttribute("sqlData",message);
                    }
                 
                dispatcher = request.getRequestDispatcher("Process.jsp");
            }
            
            //To delete data in the mysql Database
            if("Delete".equals(button_param)){
                    //login=(String)session.getAttribute("Login");
                    name = request.getParameter("name");
                    value = request.getParameter("val");
                     
                   //result =stateLessBean.RemoveAttribute(name, value);
                   
                    //result=true if remove works
                    result =stateFullBean.RemoveAttribute(name, value);
                 
                    if (result){
                    //List<String>message= stateLessBean.SetUp();
                    message= stateFullBean.SetUp();
                    request.setAttribute("sqlData",message);
                    }
                
                dispatcher = request.getRequestDispatcher("Process.jsp");
            }
            
            //To Update data in the mysql Database
            if("Update".equals(button_param)){
                    name = request.getParameter("name");
                    value = request.getParameter("val");
                    id=request.getParameter("id");                  
                   // result=stateLessBean.Update(id, name, value);
                   
                   //result=true , if update works
                    result=stateFullBean.Update(id,name,value);
                    
                    if (result){                        
                   // List<String>message= stateLessBean.SetUp(); 
                    message= stateFullBean.SetUp();                    
                    request.setAttribute("sqlData",message);                    
                    }
                dispatcher = request.getRequestDispatcher("Process.jsp");
            }
            
            if ("Continue".equals(button_param)) {
                 name = request.getParameter("name");
               
                 
                 value = request.getParameter("val");
                 
                 
                if (value.isEmpty()) {
                    if (!("Login".equals(name) || "Password".equals(name))) {
                        try {
                            session.removeAttribute(name);
                        } catch (Exception e) {
                            out.println("Error :" + e.toString());
                        }
                    }
                } else if (!name.isEmpty()) {
                    session.setAttribute(name, value);
                }
                dispatcher = request.getRequestDispatcher("Process.jsp");
            }
            
            
            dispatcher.forward(request, response);
        
    }
}
    
    
    private StatefullApplication getNewAccountBean() throws ServletException {
    // return new AccountBean();
    try {
      InitialContext context = new InitialContext();
      return (StatefullApplication) context.lookup("java:global/JeeLabs_Projet/StatefullApplicationBean");

    } catch (NamingException e) {
      throw new ServletException(e);
      }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
