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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author satvi
 */
public class up_det extends HttpServlet {

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
            String inp=request.getParameter("data");
            String arr[]=inp.split(" ");

            boolean dues=true,is_room=true;
            if(arr[6]=="No")dues=false;
            if(arr[7]=="No")is_room=false;
            
            String stdid,cno,ano,mno,rno="";
            stdid=arr[1];
            cno=arr[2];
            ano=arr[4];
            mno=arr[5];
            
            if(is_room){
                rno=arr[8];
            }
            

            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms","root","Cordinjack@35");
                PreparedStatement ps = con.prepareStatement("Update mess set mess_no=?,dues=? where account_number=?");
                ps.setString(1, mno);
                ps.setBoolean(2,dues);
                ps.setString(3,ano);
                ps.executeUpdate();
                
                
           
                ps = con.prepareStatement("Update student set stname=?,c_no=?,address=?,is_room=? where account_number=?");
                ps.setString(1, arr[0]);
                ps.setString(2,cno);
                ps.setString(3,arr[3]);
                ps.setBoolean(4, is_room);
                ps.setString(5,ano);
                ps.executeUpdate();
                
                
                if(is_room){
                    ps = con.prepareStatement("Update room set room=? where account_number=?");
                    ps.setString(1, rno);
                    ps.setString(2,ano);
                    ps.executeUpdate();
                }
                else
                {
                    ps = con.prepareStatement("Delete from room where account_number=?");
                    ps.executeUpdate();
                }
                
                out.println("Done!");
            }
            catch(Exception e){out.println(e);}
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
