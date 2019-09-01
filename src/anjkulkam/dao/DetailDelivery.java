/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anjkulkam.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author 1895270
 */
public class DetailDelivery {
    
    
     Connection con = null;
      PreparedStatement stm = null;
      ResultSet rs = null;
      
     private JSONObject mainObject=new JSONObject();
    private JSONObject singleDeliveryDetail=new JSONObject();
    private JSONArray mainArray = new JSONArray();
    
     anjkulkam.Anjkulkam akk =new anjkulkam.Anjkulkam();
    
          

    public DetailDelivery() throws SQLException
    {
      
       try {        
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@144.217.163.57:1521:XE", "sales", "anypw");
        } catch (SQLException ex) {
            System.out.println( " Error : "+ex.getMessage());
        }
        
        
    }
     
           
    
    
    
    
    public void insertDetailDelivery( int numDel, int numOrd, int nIt,int d_quantity) throws SQLException {
         try { 
           String sql;
        
        sql ="Insert into DETAILDELIVERY values(?,?,?,?)";
        stm=con.prepareStatement(sql);
        stm.setInt(1,numDel);
        stm.setInt(2, numOrd);
        
        stm.setInt(3, nIt);
        stm.setInt(4, d_quantity);
        int s1 = stm.executeUpdate();
        
        mainObject = new JSONObject();
                    mainObject.accumulate("Status", "Successfully inserted");
        
      }
      
      catch(SQLIntegrityConstraintViolationException e) {
          mainObject.accumulate("Status", "Not inserted");
          
          System.out.println( " Error : "+e.getMessage());
      }
      catch (SQLException ex) {
          mainObject.accumulate("Status", "Not inserted");
             System.out.println( " Error : "+ex.getMessage());
      }
       finally{
                    try {
                        //Write Status of current insert into json file
                        akk.writeJsonObject("Status",mainObject);
                        mainObject.clear();
                        //Read Status of current insert from json file
                        String json = akk.readJson("Status");
                        
                        JSONObject tempObj = JSONObject.fromObject(json);
                        System.out.println(tempObj);
                        if (stm != null)
                            stm.close();
                        if (con != null)
                            con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
              Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
          }
                }
       
    }
       
       
       
       
       
        public void deleteDetailDelivery( int numDel, int numOrd, int nIt) throws SQLException {
       
             try {
                String sql;
       
        
        sql ="Delete from DETAILDELIVERY where NODELIVERY=? and NOORDER=? and NOITEM=?";
        stm=con.prepareStatement(sql);
        stm.setInt(1,numDel);
        stm.setInt(2, numOrd);
        
        stm.setInt(3, nIt);
        
        int s1 = stm.executeUpdate();
        
        mainObject = new JSONObject();
                 if(s1==1)
                   mainObject.accumulate("Status", "Successfully deleted");
                 else 
                   mainObject.accumulate("Status", "Record not found");
                  System.out.println(s1 +" row(s) deleted!.");
                }
     
                catch(Exception e)
                {
                    mainObject.accumulate("Status", "Error deleting");
                     System.out.println(" Error : "+e.getMessage());
             }
            
            finally{
                    try {
                        //Write Status of current deletion into json file
                        akk.writeJsonObject("Status",mainObject);
                        mainObject.clear();
                        //Read Status of current deletion from json file
                        String json = akk.readJson("Status");
                        JSONObject tempObj = JSONObject.fromObject(json);
                        System.out.println(tempObj);
                        if (stm != null)
                            stm.close();
                        if (con != null)
                            con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
              Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
          }
                }
       
    }
    
        
        
         public void updateDetailDelivery( int numDel, int numOrd, int nIt,int qut) throws SQLException {
        try{
        String sql;
        
        
       sql ="Update DETAILDELIVERY set QUANTITYDELIVRY=?  where NODELIVERY=? and NOORDER=? and NOITEM=?";
        stm=con.prepareStatement(sql);
     stm.setInt(1,qut);
        stm.setInt(2, numDel);
        
        stm.setInt(3, numOrd);
        stm.setInt(4, nIt);
        
        
      
         int s1 = stm.executeUpdate();
        mainObject = new JSONObject();
            
        
           
            
            if(s1==1)
                  mainObject.accumulate("Status", "Successfully Updated");
             else 
                   mainObject.accumulate("Status", "Record not found");
             System.out.println(s1 +" row(s) updated!.");
                }
            
   
     
            catch(Exception e)
            {
                mainObject.accumulate("Status", "Error Updating");
                  System.out.println(" Error : "+e.getMessage());
            }
            
            finally{
                    try {
                        //Write Status of current updation into json file
                        akk.writeJsonObject("Status",mainObject);
                        mainObject.clear();
                        //Read Status of current updation from json file
                        String json = akk.readJson("Status");

                        JSONObject tempObj = JSONObject.fromObject(json);
                        System.out.println(tempObj);
                        if (stm != null)
                            stm.close();
                        if (con != null)
                            con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
              Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
          }
                }
       
    }
    
    
         
         
         
          public void listDetailDelivery() throws SQLException {
        try {
                   String sql;
        
        
       sql ="Select * from DETAILDELIVERY";
      
             int numDel, numOrd,  nIt, qut;
              mainArray = new JSONArray();

                    stm = con.prepareStatement(sql);
                    rs = stm.executeQuery();
            
           
            
            while(rs.next())
            {
                numDel=rs.getInt("NODELIVERY");
                numOrd=rs.getInt("NOORDER");
                 nIt=rs.getInt("NOITEM");
                qut=rs.getInt("QUANTITYDELIVRY");
               
               
               singleDeliveryDetail.clear();
                singleDeliveryDetail.accumulate("NODELIVERY", numDel);
                singleDeliveryDetail.accumulate("NOORDER", numOrd);
                singleDeliveryDetail.accumulate("NOITEM", nIt);
                singleDeliveryDetail.accumulate("QUANTITYDELIVRY", qut);
                          mainArray.add(singleDeliveryDetail);
                          
            
            }
           
                mainObject.accumulate("Status", "Successfully retrived delivery list"); ;
             
                    
               }
                     catch(Exception e)
            {
                mainObject.accumulate("Status", "Error in in retriving list"); 
                  System.out.println(" Error : "+e.getMessage());
            }
            
            finally{
                    try {
                        //write list of deleviry details into json
                        akk.writeJsonArray("DetailDelivery",mainArray);
                        //read list of deleviry details from json
                        String json1 = akk.readJson("DetailDelivery");
                        JSONArray tempObj1 = JSONArray.fromObject(json1);
                        System.out.println(tempObj1);
                        
                        //Write Status of current deleviry details list into json file
                        akk.writeJsonObject("Status",mainObject);
                        mainObject.clear();
                        //Read Status of current deleviry details list from json file
                        String json = akk.readJson("Status");
                        JSONObject tempObj = JSONObject.fromObject(json);
                        System.out.println(tempObj);
                       if (rs != null)
                            rs.close();
                        if (stm != null)
                            stm.close();
                        if (con != null)
                            con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
              Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
          }
                }
       
    }
                   
                   
                   
       
    
          
          
           public void anyDetailDelivery(int numDel, int numOrd, int nIt) throws SQLException {
         try{
               String sql;
        
        
          sql ="Select * from DETAILDELIVERY where NODELIVERY=? and NOORDER=? and NOITEM=?";
      
           stm = con.prepareStatement(sql);
                       stm.setInt(1, numDel);
                        stm.setInt(2, numOrd);
                         stm.setInt(3, nIt);
                    rs = stm.executeQuery();
            
             int numDel1, numOrd1,  nIt1, qut1;
            
           
            
            while(rs.next())
            {
                numDel1=rs.getInt("NODELIVERY");
                numOrd1=rs.getInt("NOORDER");
                 nIt1=rs.getInt("NOITEM");
                qut1=rs.getInt("QUANTITYDELIVRY");
                
                 singleDeliveryDetail.clear();
                singleDeliveryDetail.accumulate("NODELIVERY", numDel);
                singleDeliveryDetail.accumulate("NOORDER", numOrd);
                singleDeliveryDetail.accumulate("NOITEM", nIt);
                singleDeliveryDetail.accumulate("QUANTITYDELIVRY", qut1);
               
               
              mainObject.clear();   
                
                    }
              if(!singleDeliveryDetail.toString().equals("{}"))
                  mainObject.accumulate("Status", "Successfully retrived Delivery detail");
             else if(singleDeliveryDetail.toString().equals("{}"))
                   mainObject.accumulate("Status", "Record not found");
                           
                    
                  
               }
               
                catch(Exception e)
            {
                mainObject.accumulate("Status", "Error in in retriving Delivery detail"); 
                  System.out.println(" Error : "+e.getMessage());
            }
            
            finally{
                    try {
                        //write deleviry detail into json
                        akk.writeJsonObject("DetailDelivery",singleDeliveryDetail);
                        //read deleviry detail from json
                        String json1 = akk.readJson("DetailDelivery");
                        JSONObject tempObj1 = JSONObject.fromObject(json1);
                        System.out.println(tempObj1);
                        
                        //Write Status of current deleviry detail info into json file
                        akk.writeJsonObject("Status",mainObject);
                        mainObject.clear();
                        //Read Status of current deleviry detail info from json file
                        String json = akk.readJson("Status");
                        JSONObject tempObj = JSONObject.fromObject(json);
                        System.out.println(tempObj);
                       if (rs != null)
                            rs.close();
                        if (stm != null)
                            stm.close();
                        if (con != null)
                            con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
              Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
          }
                }
       
    }
                   
               
       
         
         
         
         
    
           }


