/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anjkulkam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author 1895250
 */
public class Items {
    
      public void insertItems(Connection con, int numItem, String desc, double price,int s_quantity) throws SQLException {
         String sql;
        PreparedStatement stm;
        
        
       
        
        sql ="Insert into ITEM values(?,?,?,?)";
        stm=con.prepareStatement(sql);
        stm.setInt(1,numItem);
        stm.setString(2, desc);
        
        stm.setDouble(3, price);
        stm.setInt(4, s_quantity);
        int s1 = stm.executeUpdate();
        
        System.out.println(s1);

      
    }
       
       
       
        public void deleteItems(Connection con,int it_id) throws SQLException {
        String sql;
        PreparedStatement stm;
       
        
        sql ="Delete from ITEM where NOITEM=?";
        stm=con.prepareStatement(sql);
        stm.setInt(1,it_id);
       
        
        int s1 = stm.executeUpdate();
        
        System.out.println(s1+"  -");
    }
    
        
        
         public void updateItems(Connection con,int it_id,double price) throws SQLException {
        String sql;
        PreparedStatement stm;
        
        
       sql ="Update ITEM set STOCKQUANTITY=? where NOITEM=? ";
        stm=con.prepareStatement(sql);
        stm.setInt(2,it_id);
           stm.setDouble(1,price);
      
        
        
      
         int s1 = stm.executeUpdate();
        System.out.println(s1+"  -");
    }
    
         
         
         
          public void listItems(Connection con) throws SQLException {
        String sql;
        Statement stm;
        
        
       sql ="Select * from ITEM";
       stm=con.createStatement();
            
            ResultSet rs=stm.executeQuery(sql);
            
            int oid,price,quant;
            String desc;
          
           
            
            while(rs.next())
            {
                oid=rs.getInt("NOITEM");
                desc=rs.getString("DESCRIPTION");
                 price=rs.getInt("UNITPRICE");
                quant=rs.getInt("STOCKQUANTITY");
               
               
                
                System.out.println(oid+" - "+desc+" - "+quant+" - "+price);
            }
            
           
    }
    
          
          
           public void anyItems(Connection con,int it_id) throws SQLException {
        String sql;
        Statement stm;
        
        
        sql ="Select * from ITEM where NOITEM= "+it_id;
       stm=con.createStatement();
            
            ResultSet rs=stm.executeQuery(sql);
            
            int oid,price,quant;
            String desc;
          
           
            
            while(rs.next())
            {
                oid=rs.getInt("NOITEM");
                desc=rs.getString("DESCRIPTION");
                 price=rs.getInt("UNITPRICE");
                quant=rs.getInt("STOCKQUANTITY");
               
               
                
                System.out.println(oid+" - "+desc+" - "+quant+" - "+price);
            }
         
    
           }   }