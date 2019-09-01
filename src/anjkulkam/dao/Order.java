/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anjkulkam.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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
 * @author 1895250
 */
public class Order {
    
    
      
      Connection con = null;
      PreparedStatement stm = null;
      ResultSet rs = null;
      
     private JSONObject mainObject=new JSONObject();
    private JSONObject singleOrder=new JSONObject();
    private JSONArray mainArray = new JSONArray();
    
     anjkulkam.Anjkulkam akk =new anjkulkam.Anjkulkam();
    
          

    public Order() throws SQLException
    {
      
       try {        
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@144.217.163.57:1521:XE", "sales", "anypw");
        } catch (SQLException ex) {
            System.out.println("In catch of constructor");
            //Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println( " Error : "+ex.getMessage());
        }
        
        
    }
    
    
    
       public void insertOrder( int num, Date dte,int noOfClent) throws SQLException {
         try {
           String sql;
        
       
        
        sql ="Insert into ORDERS values(?,?,?)";
        stm=con.prepareStatement(sql);
        stm.setInt(1,num);
        stm.setDate(2,dte);
        stm.setInt(3, noOfClent);
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
                        con.close();
                        stm.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
              Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
          }
                }
       }
       
       
       
        public void deleteOrder(int id) throws SQLException {
        
             try {
            String sql;       
        
        sql ="Delete from ORDERS where NOORDER=?";
        stm=con.prepareStatement(sql);
        stm.setInt(1,id);
        
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
                        con.close();
                        stm.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
              Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
          }
                }
        }
    
        
        
         public void updateOrder(int id,Date dte) throws SQLException {
        
              try{
             String sql;
        int client_id=id;
        
       sql ="Update ORDERS set dateorder=? where NOORDER=?";
        stm=con.prepareStatement(sql);
        stm.setInt(2,client_id);
        
        
        stm.setDate(1,dte);
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
                        con.close();
                        stm.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
              Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
          }
                }
       
    }
    
         
         
         
          public void listOrder() throws SQLException {
       
              try{
              String sql;
        
        
       sql ="Select * from ORDERS";
            
            
            int oid,cid;
          
            Date date1; 
            mainArray = new JSONArray();

                    stm = con.prepareStatement(sql);
                    rs = stm.executeQuery();
            
            while(rs.next())
            {
                oid=rs.getInt("NOORDER");
                date1=rs.getDate("DATEORDER");
                cid=rs.getInt("NOCLIENT");
               
            singleOrder.clear();
                singleOrder.accumulate("NOORDER", oid);
                singleOrder.accumulate("DATEORDER", date1.toString());
                singleOrder.accumulate("NOCLIENT", cid);
                          mainArray.add(singleOrder);
                          
                    }
                     mainObject.accumulate("Status", "Successfully retrived orders list"); 
                    
               }
                     catch(Exception e)
            {
                mainObject.accumulate("Status", "Error in in retriving list"); 
                  System.out.println(" Error : "+e.getMessage());
            }
            
            finally{
                    try {
                        //write list of clients into json
                        akk.writeJsonArray("client",mainArray);
                        //read list of clients from json
                        String json1 = akk.readJson("client");
                        JSONArray tempObj1 = JSONArray.fromObject(json1);
                        System.out.println(tempObj1);
                        
                        //Write Status of current client list into json file
                        akk.writeJsonObject("clientStatus",mainObject);
                        mainObject.clear();
                        //Read Status of current client list from json file
                        String json = akk.readJson("clientStatus");
                        //FileReader.loadFileIntoString("json/client.json", "UTF-8");
                        JSONObject tempObj = JSONObject.fromObject(json);
                        System.out.println(tempObj);
                        rs.close();
                        con.close();
                        stm.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
              Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
          }
                }
       
    }
    
          
          
           public void anyOrder(int id) throws SQLException {
        
               try{
               String sql;
        
       sql ="Select * from ORDERS where NOORDER=?";
            
            int oid,cid;
          
            Date date1; 
            mainArray = new JSONArray();

                    stm = con.prepareStatement(sql);
                    stm.setInt(1, id);
                    rs = stm.executeQuery();
            
            
            while(rs.next())
            {
                oid=rs.getInt("NOORDER");
                date1=rs.getDate("DATEORDER");
                cid=rs.getInt("NOCLIENT");
               
                
                 singleOrder.clear();
                singleOrder.accumulate("NOORDER", oid);
                singleOrder.accumulate("DATEORDER", date1.toString());
                singleOrder.accumulate("NOCLIENT", cid);
                mainArray.add(singleOrder);
                
                mainObject.clear();          
            }
         
          }
               
                catch(Exception e)
            {
                mainObject.accumulate("Status", "Error in in retriving client"); 
                  System.out.println(" Error : "+e.getMessage());
            }
            
            finally{
                    try {
                        //write client into json
                        akk.writeJsonArray("client",mainArray);
                        //read client from json
                        String json1 = akk.readJson("client");
                        JSONArray tempObj1 = JSONArray.fromObject(json1);
                        System.out.println(tempObj1);
                        
                        //Write Status of current client info into json file
                        akk.writeJsonObject("clientStatus",mainObject);
                        mainObject.clear();
                        //Read Status of current client info from json file
                        String json = akk.readJson("clientStatus");
                        //FileReader.loadFileIntoString("json/client.json", "UTF-8");
                        JSONObject tempObj = JSONObject.fromObject(json);
                        System.out.println(tempObj);
                        rs.close();
                        con.close();
                        stm.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
              Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
          }
                }
       
    }
                   
               
       
         
         
         
         
    
           }


