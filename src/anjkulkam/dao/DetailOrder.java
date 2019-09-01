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
public class DetailOrder {
       public void insertOrderDetail(Connection con, int numOrder, int numItem,int quantity) throws SQLException {
         String sql;
        PreparedStatement stm;
        
       
        
        sql ="Insert into DETAILORDER values(?,?,?)";
        stm=con.prepareStatement(sql);
        stm.setInt(1,numOrder);
        stm.setInt(2,numItem);
        stm.setInt(3, quantity);
        int s1 = stm.executeUpdate();
        
        System.out.println(s1);

      
    }
       
       
       
        public void deleteOrderDetail(Connection con,int ord_id,int it_id) throws SQLException {
        String sql;
        PreparedStatement stm;
       
        
        sql ="Delete from DETAILORDER where NOORDER=? and NOITEM=?";
        stm=con.prepareStatement(sql);
        stm.setInt(1,ord_id);
        stm.setInt(2,it_id);
        
        int s1 = stm.executeUpdate();
        
        System.out.println(s1+"  -");
    }
    
        
        
         public void updateOrderDetail(Connection con,int ord_id,int it_id,int quant) throws SQLException {
        String sql;
        PreparedStatement stm;
        
        
       sql ="Update DETAILORDER set QUANTITY=? where NOORDER=? and NOITEM=?";
        stm=con.prepareStatement(sql);
        stm.setInt(1,quant);
           stm.setInt(2,ord_id);
        stm.setInt(3,it_id);
        
        
      
         int s1 = stm.executeUpdate();
        System.out.println(s1+"  -");
    }
    
         
         
         
          public void listOrderDetail(Connection con) throws SQLException {
        String sql;
        Statement stm;
        
        
       sql ="Select * from DETAILORDER";
       stm=con.createStatement();
            
            ResultSet rs=stm.executeQuery(sql);
            
            int oid,cid,quant;
          
           
            
            while(rs.next())
            {
                oid=rs.getInt("NOORDER");
                 cid=rs.getInt("NOITEM");
                quant=rs.getInt("QUANTITY");
               
               
                
                System.out.println(oid+" - "+quant+" - "+cid);
            }
            
           
    }
    
          
          
           public void anyOrderDetail(Connection con, int ord_id,int it_id) throws SQLException {
        String sql;
        Statement stm;
        
        
       sql ="Select * from DETAILORDER where NOORDER="+ord_id+" and NOITEM="+it_id;
       stm=con.createStatement();
            
            ResultSet rs=stm.executeQuery(sql);
            
            int oid,cid,quant;
          
         
            
            while(rs.next())
            {
                oid=rs.getInt("NOORDER");
                 cid=rs.getInt("NOITEM");
                quant=rs.getInt("QUANTITY");
               
               
                
                System.out.println(oid+" - "+quant+" - "+cid);
            }
         
         
         
    
           }   }