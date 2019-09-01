/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anjkulkam.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author 1895250
 */
public class Order {
    
    
    
       public void insertOrder(Connection con, int num, Date dte,int noOfClent) throws SQLException {
         String sql;
        PreparedStatement stm;
        
       
        
        sql ="Insert into ORDERS values(?,?,?)";
        stm=con.prepareStatement(sql);
        stm.setInt(1,num);
        stm.setDate(2,dte);
        stm.setInt(3, noOfClent);
        int s1 = stm.executeUpdate();
        
        System.out.println(s1);
      
      
    }
       
       
       
        public void deleteOrder(Connection con,int id) throws SQLException {
        String sql;
        PreparedStatement stm;
       
        
        sql ="Delete from ORDERS where NOORDER=?";
        stm=con.prepareStatement(sql);
        stm.setInt(1,id);
        
        int s1 = stm.executeUpdate();
        
        System.out.println(s1+"  -");
    }
    
        
        
         public void updateOrder(Connection con,int id,Date dte) throws SQLException {
        String sql;
        PreparedStatement stm;
        int client_id=id;
        
       sql ="Update ORDERS set dateorder=? where NOORDER=?";
        stm=con.prepareStatement(sql);
        stm.setInt(2,client_id);
        
        
        stm.setDate(1,dte);
         int s1 = stm.executeUpdate();
        System.out.println(s1+"  -");
    }
    
         
         
         
          public void listOrder(Connection con) throws SQLException {
        String sql;
        Statement stm;
        
        
       sql ="Select * from ORDERS";
       stm=con.createStatement();
            
            ResultSet rs=stm.executeQuery(sql);
            
            int oid,cid;
          
            Date date1; 
            
            while(rs.next())
            {
                oid=rs.getInt("NOORDER");
                date1=rs.getDate("DATEORDER");
                cid=rs.getInt("NOCLIENT");
               
                
                System.out.println(oid+" - "+date1.toString()+" - "+cid);
            }
            
           
    }
    
          
          
           public void anyOrder(Connection con, int id) throws SQLException {
        String sql;
        Statement stm;
        
        
       sql ="Select * from ORDERS where NOORDER= "+id;
       stm=con.createStatement();
            
            ResultSet rs=stm.executeQuery(sql);
            
            int oid,cid;
          
            Date date1; 
            
            while(rs.next())
            {
                oid=rs.getInt("NOORDER");
                date1=rs.getDate("DATEORDER");
                cid=rs.getInt("NOCLIENT");
               
                
                System.out.println(oid+" - "+date1.toString()+" - "+cid);
            }
         
         
         
         
    
           }
}
