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
 * @author 1895270
 */
public class Items {
    
       
      Connection con = null;
      PreparedStatement stm = null;
      ResultSet rs = null;
      
     private JSONObject mainObject=new JSONObject();
    private JSONObject singleItem=new JSONObject();
    private JSONArray mainArray = new JSONArray();
    
     anjkulkam.Anjkulkam akk =new anjkulkam.Anjkulkam();
    
          

    public Items() throws SQLException
    {
      
       try {        
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@144.217.163.57:1521:XE", "sales", "anypw");
        } catch (SQLException ex) {
            System.out.println( " Error : "+ex.getMessage());
        }
        
        
    }
    
    
    
      public void insertItems(int numItem, String desc, double price,int s_quantity) throws SQLException {
          try { 
           String sql;
        
        
        sql ="Insert into ITEM values(?,?,?,?)";
        stm=con.prepareStatement(sql);
        stm.setInt(1,numItem);
        stm.setString(2, desc);
        
        stm.setDouble(3, price);
        stm.setInt(4, s_quantity);
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
       
       
       
       
       
        public void deleteItems(int it_id) throws SQLException {
        try {
                String sql;
       
        
        sql ="Delete from ITEM where NOITEM=?";
        stm=con.prepareStatement(sql);
        stm.setInt(1,it_id);
       
        
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
    
    
        
        
         public void updateItems(int it_id,double price) throws SQLException {
         try{
        String sql;
        
       sql ="Update ITEM set STOCKQUANTITY=? where NOITEM=? ";
        stm=con.prepareStatement(sql);
        stm.setInt(2,it_id);
           stm.setDouble(1,price);
     
      
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
    
         
    
         
         
         
          public void listItems() throws SQLException {
        try {
                   String sql;
                   
        
       sql ="Select * from ITEM";
        mainArray = new JSONArray();

                    stm = con.prepareStatement(sql);
                    rs = stm.executeQuery();
            
            int oid,price,quant;
            String desc;
          
           
            
            while(rs.next())
            {
                oid=rs.getInt("NOITEM");
                desc=rs.getString("DESCRIPTION");
                 price=rs.getInt("UNITPRICE");
                quant=rs.getInt("STOCKQUANTITY");
                
                singleItem=new JSONObject();
                singleItem.clear();
                singleItem.accumulate("NOITEM", oid);
                singleItem.accumulate("DESCRIPTION", desc);
                singleItem.accumulate("UNITPRICE", price);
                singleItem.accumulate("STOCKQUANTITY", quant);
                          mainArray.add(singleItem);
               
               }
                     mainObject.accumulate("Status", "Successfully retrived Items list"); 
                    
               }
                     catch(Exception e)
            {
                mainObject.accumulate("Status", "Error in in retriving Items list"); 
                  System.out.println(" Error : "+e.getMessage());
            }
            
            finally{
                    try {
                        //write list of Items into json
                        akk.writeJsonArray("Items",mainArray);
                        //read list of Items from json
                        String json1 = akk.readJson("Items");
                        JSONArray tempObj1 = JSONArray.fromObject(json1);
                        System.out.println(tempObj1);
                        
                        //Write Status of current Items list into json file
                        akk.writeJsonObject("Status",mainObject);
                        mainObject.clear();
                        //Read Status of current Items list from json file
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
                   
                   
             
           
    
    
          
          
           public void anyItems(int it_id) throws SQLException {
          try{
               String sql;
        
        
        sql ="Select * from ITEM where NOITEM=?";
             stm = con.prepareStatement(sql);
                       stm.setInt(1, it_id);
                    rs = stm.executeQuery();
                    
            
            int oid,price,quant;
            String desc;
          
           
            
            while(rs.next())
            {
                oid=rs.getInt("NOITEM");
                desc=rs.getString("DESCRIPTION");
                 price=rs.getInt("UNITPRICE");
                quant=rs.getInt("STOCKQUANTITY");
                
                 singleItem=new JSONObject();
                singleItem.clear();
                singleItem.accumulate("NOITEM", oid);
                singleItem.accumulate("DESCRIPTION", desc);
                singleItem.accumulate("UNITPRICE", price);
                singleItem.accumulate("STOCKQUANTITY", quant);
               
               mainObject.clear();   
                
                    }
              if(!singleItem.toString().equals("{}"))
                  mainObject.accumulate("Status", "Successfully retrived Item");
             else if(singleItem.toString().equals("{}"))
                   mainObject.accumulate("Status", "Record not found");
                           
                    
                  
               }
               
                catch(Exception e)
            {
                mainObject.accumulate("Status", "Error in in retriving Item"); 
                  System.out.println(" Error : "+e.getMessage());
            }
            
            finally{
                    try {
                        //write Item into json
                        akk.writeJsonObject("Items",singleItem);
                        //read Item from json
                        String json1 = akk.readJson("Items");
                        JSONObject tempObj1 = JSONObject.fromObject(json1);
                        System.out.println(tempObj1);
                        
                        //Write Status of current Item info into json file
                        akk.writeJsonObject("Status",mainObject);
                        mainObject.clear();
                        //Read Status of current Item info from json file
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


