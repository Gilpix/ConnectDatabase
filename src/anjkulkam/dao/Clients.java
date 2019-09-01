/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anjkulkam.dao;

import anjkulkam.Anjkulkam;
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
public class Clients extends Anjkulkam{
    

       
      Connection con = null;
      PreparedStatement stm = null;
      ResultSet rs = null;
      
     private JSONObject mainObject=new JSONObject();
    private JSONObject singleClient=new JSONObject();
    private JSONArray mainArray = new JSONArray();
    
     anjkulkam.Anjkulkam akk =new anjkulkam.Anjkulkam();
    
          

    public Clients() throws SQLException
    {
      
       try {        
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@144.217.163.57:1521:XE", "sales", "anypw");
        } catch (SQLException ex) {
            System.out.println( " Error : "+ex.getMessage());
        }
        
        
    }
     
           
    
    

    
       public void insertClient(int num, String name,String phone) throws SQLException {
      try { 
           String sql;
      
        sql ="Insert into CLIENT values(?,?,?)";
        stm=con.prepareStatement(sql);
        stm.setInt(1,num);
        stm.setString(2, name);
        stm.setString(3, phone);
        int s1 = stm.executeUpdate();
        
        //System.out.println(s1 +" row(s) inserted!.");
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
       
       
       
        public void deleteClient(int id) throws SQLException {
        
            
            try {
                String sql;
                int client_id=id;
        
                 sql ="Delete from CLIENT where NOCLIENT=?";
                 stm=con.prepareStatement(sql);
                 stm.setInt(1,client_id);
        
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
    
        
        
         public void updateClient(int id,String mNum) throws SQLException {
             
             try{
        String sql;
        int client_id=id;
        
       sql ="Update CLIENT set NOTELEPHONE=? where NOCLIENT=?";
        stm=con.prepareStatement(sql);
        stm.setInt(2,client_id);
        
        
        stm.setString(1,mNum);
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
    
         
         
         
          public void listClient() throws SQLException, IOException {
               try {
                   String sql;
                   
                    
       sql ="Select * from client";
            
        
            int cid;
            String cname,rname;
            
             mainArray = new JSONArray();

                    stm = con.prepareStatement(sql);
                    rs = stm.executeQuery();
                    while(rs.next())
                    {
                         singleClient = new JSONObject();
                         cid=rs.getInt("NOCLIENT");
                cname=rs.getString("NAMECLIENT");
                rname=rs.getString("NOTELEPHONE");
                
                singleClient.clear();
                singleClient.accumulate("NOCLIENT", cid);
                singleClient.accumulate("NAMECLIENT", cname);
                singleClient.accumulate("NOTELEPHONE", rname);
                          mainArray.add(singleClient);
                          
                    }
                     mainObject.accumulate("Status", "Successfully retrived clients list"); 
                    
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
                        akk.writeJsonObject("Status",mainObject);
                        mainObject.clear();
                        //Read Status of current client list from json file
                        String json = akk.readJson("Status");
                        //FileReader.loadFileIntoString("json/client.json", "UTF-8");
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
                        System.out.println(" Error : "+ex.getMessage());
                    } catch (IOException ex) {
              Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
          }
                }
       
    }
                   

           public void anyClient(int id) throws SQLException, IOException {
               
               try{
               String sql;
                    sql = "Select * from client where NOCLIENT=?";
                       stm = con.prepareStatement(sql);
                       stm.setInt(1, id);
                    rs = stm.executeQuery();
                    
                     int cid;
                    String cname,rname;
                    while(rs.next())
                    {
                         cid=rs.getInt("NOCLIENT");
                cname=rs.getString("NAMECLIENT");
                rname=rs.getString("NOTELEPHONE");
                
                
                singleClient.accumulate("NOCLIENT", cid);
                singleClient.accumulate("NAMECLIENT", cname);
                singleClient.accumulate("NOTELEPHONE", rname);
                
                mainObject.clear();   
                        }

                                                
             if(!singleClient.toString().equals("{}"))
                  mainObject.accumulate("Status", "Successfully retrived client");
             else if(singleClient.toString().equals("{}"))
                   mainObject.accumulate("Status", "Record not found");
                           
   
               }
               
                catch(Exception e)
            {
                mainObject.accumulate("Status", "Error in in retriving client"); 
                  System.out.println(" Error : "+e.getMessage());
            }
            
            finally{
                    try {
                        //write client info into json
                        akk.writeJsonObject("client",singleClient);
                        //read client info from json
                        String json1 = akk.readJson("client");
                        JSONObject tempObj1 = JSONObject.fromObject(json1);
                        System.out.println(tempObj1);
                        
                        //Write Status of current client info into json file
                        akk.writeJsonObject("Status",mainObject);
                        mainObject.clear();
                        //Read Status of current client info from json file
                        String json = akk.readJson("Status");
                        //FileReader.loadFileIntoString("json/client.json", "UTF-8");
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


