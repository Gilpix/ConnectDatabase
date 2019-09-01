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

    /**
     * @param args the command line arguments
     */
    
    
   
   public static void main(String[] args) throws IOException {        
        
          
        
        try {
            Class.forName("oracle.jdbc.OracleDriver");
                        
            
   
      anjkulkam.dao.Clients c =new anjkulkam.dao.Clients();
     //c.insertClient(109,"Kamlaaaa","99508655214");
     // c.deleteClient(111);
      //c.updateClient(111, "00005000");
       //c.listClient();
    //c.anyClient(110);
        
      
       Date dte=Date.valueOf("2019-03-01");
       anjkulkam.dao.Order ord=new anjkulkam.dao.Order();
       //ord.insertOrder(16, dte, 1000);
       //ord.deleteOrder(11);
      // ord.updateOrder(11, dte);
      // ord.listOrder();
       //ord.anyOrder(11);
       
      
       
        anjkulkam.dao.DetailOrder detOrd=new anjkulkam.dao.DetailOrder();
        //detOrd.insertOrderDetail(16, 10, 15);
        //detOrd.deleteOrderDetail(16, 10);
       // detOrd.updateOrderDetail(16, 10, 100);
       // detOrd.listOrderDetail();
        //detOrd.anyOrderDetail(16, 10);
     
        
         anjkulkam.dao.Items itm= new anjkulkam.dao.Items();
         //itm.insertItems(con, 12, "KK", 7,9);
         //itm.deleteItems(con, 10001);
         //itm.updateItems(con, 11,5.0);
         //itm.listItems(con);
         //itm.anyItems(con, 11);
         
          anjkulkam.dao.Delivery del =new  anjkulkam.dao.Delivery();
          //del.insertDelivery( 10, dte);
          //del.deleteDelivery(8);
          //del.updateDelivery(8, dte);
          del.listDelivery();
          //del.anyDelivery(8);
       
          
          anjkulkam.dao.DetailDelivery dd = new anjkulkam.dao.DetailDelivery();
          //dd.insertDetailDelivery(con, 8, 16, 10, 20);
          //dd.deleteDetailDelivery(con, 8, 16, 10);
         //dd.updateDetailDelivery(con, 8, 16, 10, 10);
         // dd.listDetailDelivery(con);
          //dd.anyDetailDelivery(con, 8, 16, 10);
      
            
          
     
          
         
       
     
           
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Anjkulkam.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Anjkulkam.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

   
   
    public void writeJsonObject(String fileName, JSONObject jsn) throws IOException
          {
               try {
           FileWriter.saveStringIntoFile("json/"+fileName+".json", jsn.toString());
           } catch (IOException ex) {
            //Logger.getLogger(Anjkulkam.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println( " Error : "+ex.getMessage());
        }
               
               } 
    
     public void writeJsonArray(String fileName, JSONArray jsn) 
          {
        try {
            FileWriter.saveStringIntoFile("json/"+fileName+".json", jsn.toString());
        } catch (IOException ex) {
            //Logger.getLogger(Anjkulkam.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println( " Error : "+ex.getMessage());
        }
          } 
     
     
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


