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
public class DetailDelivery {
    
    
    
    public void insertDetailDelivery(Connection con, int numDel, int numOrd, int nIt,int d_quantity) throws SQLException {
         String sql;
        PreparedStatement stm;
        
        
       
        
        sql ="Insert into DETAILDELIVERY values(?,?,?,?)";
        stm=con.prepareStatement(sql);
        stm.setInt(1,numDel);
        stm.setInt(2, numOrd);
        
        stm.setInt(3, nIt);
        stm.setInt(4, d_quantity);
        int s1 = stm.executeUpdate();
        
        System.out.println(s1);

      
    }
       
       
       
        public void deleteDetailDelivery(Connection con, int numDel, int numOrd, int nIt) throws SQLException {
        String sql;
        PreparedStatement stm;
       
        
        sql ="Delete from DETAILDELIVERY where NODELIVERY=? and NOORDER=? and NOITEM=?";
        stm=con.prepareStatement(sql);
        stm.setInt(1,numDel);
        stm.setInt(2, numOrd);
        
        stm.setInt(3, nIt);
        
        int s1 = stm.executeUpdate();
        
        System.out.println(s1+"  -");
    }
    
        
        
         public void updateDetailDelivery(Connection con, int numDel, int numOrd, int nIt,int qut) throws SQLException {
        String sql;
        PreparedStatement stm;
        
        
       sql ="Update DETAILDELIVERY set QUANTITYDELIVRY=?  where NODELIVERY=? and NOORDER=? and NOITEM=?";
        stm=con.prepareStatement(sql);
     stm.setInt(1,qut);
        stm.setInt(2, numDel);
        
        stm.setInt(3, numOrd);
        stm.setInt(4, nIt);
        
        
      
         int s1 = stm.executeUpdate();
        System.out.println(s1+"  -");
    }
    
         
         
         
          public void listDetailDelivery(Connection con) throws SQLException {
        String sql;
        Statement stm;
        
        
       sql ="Select * from DETAILDELIVERY";
       stm=con.createStatement();
            
            ResultSet rs=stm.executeQuery(sql);
            
             int numDel, numOrd,  nIt, qut;
            
           
            
            while(rs.next())
            {
                numDel=rs.getInt("NODELIVERY");
                numOrd=rs.getInt("NOORDER");
                 nIt=rs.getInt("NOITEM");
                qut=rs.getInt("QUANTITYDELIVRY");
               
               
                
                System.out.println(numDel+" - "+numOrd+" - "+nIt+" - "+qut);
            }
            
           
    }
    
          
          
           public void anyDetailDelivery(Connection con,int numDel, int numOrd, int nIt) throws SQLException {
        String sql;
        Statement stm;
        
        
          sql ="Select * from DETAILDELIVERY where NODELIVERY="+numDel+" and NOORDER="+numOrd+" and NOITEM="+nIt+"";
       stm=con.createStatement();
            
            ResultSet rs=stm.executeQuery(sql);
            
             int numDel1, numOrd1,  nIt1, qut1;
            
           
            
            while(rs.next())
            {
                numDel1=rs.getInt("NODELIVERY");
                numOrd1=rs.getInt("NOORDER");
                 nIt1=rs.getInt("NOITEM");
                qut1=rs.getInt("QUANTITYDELIVRY");
               
               
                
                System.out.println(numDel1+" - "+numOrd1+" - "+nIt1+" - "+qut1);
            }
    
           }   }