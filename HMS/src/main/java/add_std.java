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
public class add_std extends HttpServlet {

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
            
                PreparedStatement ps = con.prepareStatement("SELECT * FROM stdid where account_number=?");
                ps.setString(1, ano);
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                {
                    out.println("1");
                    return;
                }
                ps = con.prepareStatement("SELECT * FROM stdid where student_id=?");
                ps.setString(1, stdid);
                rs = ps.executeQuery();
                if(rs.next())
                {
                    out.println("0");
                    return;
                }
                ps = con.prepareStatement("insert into mess values(?,?,?)");
                ps.setString(1, ano);
                ps.setString(2, mno);
                ps.setBoolean(3,dues);
                ps.execute();
                
                
                ps = con.prepareStatement("insert into stdid values(?,?)");
                ps.setString(1, ano);
                ps.setString(2, stdid);
                ps.execute();
                
                
                
                ps = con.prepareStatement("insert into student values(?,?,?,?,?)");
                ps.setString(1, ano);
                ps.setString(2, arr[0]);
                ps.setString(3,cno);
                ps.setString(4,arr[3]);
                ps.setBoolean(5, dues);
                ps.execute();
                
                
                if(is_room)
                {
                    ps = con.prepareStatement("insert into room values(?,?)");
                    ps.setString(1, ano);
                    ps.setString(2, rno);
                    ps.execute();
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
