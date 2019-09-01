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
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author 1895250
 */
public class DetailOrder {
    
     
      Connection con = null;
      PreparedStatement stm = null;
      ResultSet rs = null;
      
     private JSONObject mainObject=new JSONObject();
    private JSONObject singleOrderDetail=new JSONObject();
    private JSONArray mainArray = new JSONArray();
    
     anjkulkam.Anjkulkam akk =new anjkulkam.Anjkulkam();
    
          

    public DetailOrder() throws SQLException
    {
      
       try {        
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@144.217.163.57:1521:XE", "sales", "anypw");
        } catch (SQLException ex) {
            System.out.println( " Error : "+ex.getMessage());
        }
        
        
    }
     
           
    
    

    
    
       public void insertOrderDetail( int numOrder, int numItem,int quantity) throws SQLException {
        try { 
           String sql;
       
        
        sql ="Insert into DETAILORDER values(?,?,?)";
        stm=con.prepareStatement(sql);
        stm.setInt(1,numOrder);
        stm.setInt(2,numItem);
        stm.setInt(3, quantity);
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
       
       
      
       
       
       
        public void deleteOrderDetail(int ord_id,int it_id) throws SQLException {
         try {
                String sql;
       
        
        sql ="Delete from DETAILORDER where NOORDER=? and NOITEM=?";
        stm=con.prepareStatement(sql);
        stm.setInt(1,ord_id);
        stm.setInt(2,it_id);
        
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
                        //FileReader.loadFileIntoString("json/client.json", "UTF-8");
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
    
        
        
         public void updateOrderDetail(int ord_id,int it_id,int quant) throws SQLException {
        try{
        String sql;
        
       sql ="Update DETAILORDER set QUANTITY=? where NOORDER=? and NOITEM=?";
        stm=con.prepareStatement(sql);
        stm.setInt(1,quant);
           stm.setInt(2,ord_id);
        stm.setInt(3,it_id);
        
        
      
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
    
         
         
         
          public void listOrderDetail( ) throws SQLException {
         try {
                   String sql;
                   
        
       sql ="Select * from DETAILORDER";
       
            
            int oid,cid,quant;
          mainArray = new JSONArray();

                    stm = con.prepareStatement(sql);
                    rs = stm.executeQuery();
           
            
            while(rs.next())
            {
                 singleOrderDetail = new JSONObject();
                oid=rs.getInt("NOORDER");
                 cid=rs.getInt("NOITEM");
                quant=rs.getInt("QUANTITY");
               
                singleOrderDetail.clear();
                singleOrderDetail.accumulate("NOORDER", oid);
                singleOrderDetail.accumulate("NOITEM", cid);
                singleOrderDetail.accumulate("QUANTITY", quant);
                          mainArray.add(singleOrderDetail);
            }
             mainObject.accumulate("Status", "Successfully retrived Orders Detail list"); 
                    
               }
                     catch(Exception e)
            {
                mainObject.accumulate("Status", "Error in retriving Orders Detail list"); 
                  System.out.println(" Error : "+e.getMessage());
            }
            
            finally{
                    try {
                        //write list of orders detail into json
                        akk.writeJsonArray("client",mainArray);
                        //read list of orders detail from json
                        String json1 = akk.readJson("client");
                        JSONArray tempObj1 = JSONArray.fromObject(json1);
                        System.out.println(tempObj1);
                        
                        //Write Status of current orders detail list into json file
                        akk.writeJsonObject("Status",mainObject);
                        mainObject.clear();
                        //Read Status of current orders detail list from json file
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
                   
                   
                   
       
    
          
          
           public void anyOrderDetail( int ord_id,int it_id) throws SQLException {
       try{
               String sql;
        
        
       sql ="Select * from DETAILORDER where NOORDER=? and NOITEM=?";
       stm = con.prepareStatement(sql);
                       stm.setInt(1, ord_id);
                       stm.setInt(2, it_id);
                    rs = stm.executeQuery();
            
            int oid,cid,quant;
          
         
            
            while(rs.next())
            {
                oid=rs.getInt("NOORDER");
                 cid=rs.getInt("NOITEM");
                quant=rs.getInt("QUANTITY");
               
               
                singleOrderDetail.clear();
                singleOrderDetail.accumulate("NOORDER", oid);
                singleOrderDetail.accumulate("NOITEM", cid);
                singleOrderDetail.accumulate("QUANTITY", quant);
            mainObject.clear();   
                
                    }
              if(!singleOrderDetail.toString().equals("{}"))
                  mainObject.accumulate("Status", "Successfully retrived Order Detail");
             else if(singleOrderDetail.toString().equals("{}"))
                   mainObject.accumulate("Status", "Record not found");
                           
                    
                  
               }
               
                catch(Exception e)
            {
                mainObject.accumulate("Status", "Error in retriving Order Detail"); 
                  System.out.println(" Error : "+e.getMessage());
            }
            
            finally{
                    try {
                        //write order detail into json
                        akk.writeJsonObject("client",singleOrderDetail);
                        //read order detail from json
                        String json1 = akk.readJson("client");
                        JSONObject tempObj1 = JSONObject.fromObject(json1);
                        System.out.println(tempObj1);
                        
                        //Write Status of current order detail info into json file
                        akk.writeJsonObject("Status",mainObject);
                        mainObject.clear();
                        //Read Status of current order detail info from json file
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


