/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anjkulkam;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 *
 * @author 1895250
 */
public class Anjkulkam {

    
    
   
   public static void main(String[] args) throws IOException {        
        
          
        
        try {
            Class.forName("oracle.jdbc.OracleDriver");
                        
            
   
      anjkulkam.dao.Clients c =new anjkulkam.dao.Clients();
      //c.insertClient(109,"Kamlaaaa","99508655214");
      //c.deleteClient(109);
      //c.updateClient(109, "00005000");
      c.listClient();
      //c.anyClient(109);
        
      
       Date dte=Date.valueOf("2019-03-01");
       anjkulkam.dao.Order ord=new anjkulkam.dao.Order();
       //ord.insertOrder(17, dte, 1000);
       //ord.deleteOrder(17);
       //ord.updateOrder(17, dte);
       //ord.listOrder();
       //ord.anyOrder(17);
       
      
       
        anjkulkam.dao.DetailOrder detOrd=new anjkulkam.dao.DetailOrder();
        //detOrd.insertOrderDetail(17, 10, 15);
        //detOrd.deleteOrderDetail(17, 10);
        //detOrd.updateOrderDetail(17, 10, 1001);
        //detOrd.listOrderDetail();
        //detOrd.anyOrderDetail(17, 10);
     
        
         anjkulkam.dao.Items itm= new anjkulkam.dao.Items();
         //itm.insertItems( 111, "KK", 7,9);
         //itm.deleteItems(111);
         //itm.updateItems( 111,5.0);
         //itm.listItems();
         //itm.anyItems(11);
         
          anjkulkam.dao.Delivery del =new  anjkulkam.dao.Delivery();
          //del.insertDelivery( 11, dte);
          //del.deleteDelivery(8);
          //del.updateDelivery(8, dte);
          //del.listDelivery();
          //del.anyDelivery(8);
       
          
          anjkulkam.dao.DetailDelivery dd = new anjkulkam.dao.DetailDelivery();
          //dd.insertDetailDeliver( 8, 16, 10, 20);
          //dd.deleteDetailDelivery( 8, 16, 10);
          //dd.updateDetailDelivery( 8, 16, 10, 10);
          //dd.listDetailDelivery();
          //dd.anyDetailDelivery( 8, 16, 10);
      
            

           
        } catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(Anjkulkam.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

   
   //Method to Write JSONObject in json file
    public void writeJsonObject(String fileName, JSONObject jsn) throws IOException
          {
               try {
           FileWriter.saveStringIntoFile("json/"+fileName+".json", jsn.toString());
           } catch (IOException ex) {
            //Logger.getLogger(Anjkulkam.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println( " Error : "+ex.getMessage());
        }
               
               } 
    
       //Method to Write JSONArray in json file
     public void writeJsonArray(String fileName, JSONArray jsn) 
          {
        try {
            FileWriter.saveStringIntoFile("json/"+fileName+".json", jsn.toString());
        } catch (IOException ex) {
            //Logger.getLogger(Anjkulkam.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println( " Error : "+ex.getMessage());
        }
          } 
     
        //Method to Read JSONObject or JSONArray from json file
     public String readJson(String fileName) 
          {
              String json="";
        try {
             json = FileReader.loadFileIntoString("json/"+fileName+".json", "UTF-8");
            return json;
        } catch (IOException ex) {
            //Logger.getLogger(Anjkulkam.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println( " Error : "+ex.getMessage());
        }
        return json;
          } 
     
     
}


