/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author satvi
 */
public class get_det extends HttpServlet {

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
            String inp1=request.getParameter("data");
            String stdid=inp1;
            
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms","root","Cordinjack@35");
            
                PreparedStatement ps = con.prepareStatement("SELECT * FROM stdid where student_id=?");
                ps.setString(1, stdid);
                ResultSet rs = ps.executeQuery();
                rs.next();
                String ano=rs.getString("account_number");
                
                
                
                ps = con.prepareStatement("SELECT * FROM student where account_number=?");
                ps.setString(1, ano);
                rs = ps.executeQuery();
                rs.next();
                out.print(rs.getString("stname"));
                out.print(" ");
                out.print(stdid);
                out.print(" ");
                out.print(rs.getString("c_no"));
                out.print(" ");
                out.print(rs.getString("address"));
                out.print(" ");
                out.print(ano);
                out.print(" ");
                
                boolean is_room=rs.getBoolean("is_room");
                
                
                
                
                ps = con.prepareStatement("SELECT * FROM mess where account_number=?");
                ps.setString(1, ano);
                rs = ps.executeQuery();
                rs.next();
                
                out.print(rs.getString("mess_no"));
                out.print(" ");
                
                if(rs.getBoolean("dues"))
                {
                    out.print("Yes");
                }
                else{
                    out.print("No");
                }
                
                out.print(" ");
                if(is_room)
                {
                    out.print("Yes");
                }
                else{
                    out.print("No");
                }
                out.print(" ");
                
                
                if(is_room)
                {
                    ps = con.prepareStatement("SELECT * FROM room where account_number=?");
                    ps.setString(1, ano);
                    rs = ps.executeQuery();
                    rs.next();
                    out.print(rs.getString("room"));
                }
            }
            catch(ClassNotFoundException | SQLException e){out.println(e);}
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
