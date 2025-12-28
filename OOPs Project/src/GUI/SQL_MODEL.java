/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.sql.*;


class UserData{
    String email;
    String fullName;
    int user_id;
    
    UserData(String email,String fullName,int user_id){
        this.email=email;
        this.fullName=fullName;
        this.user_id=user_id;
    }
}
/**
 *
 * @author Rao Hamza Tariq
 */
public class SQL_MODEL {
   Connection conn;
   Statement st;
   ResultSet rs;
   
   SQL_MODEL(){
       try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance_db","root","9211");
           st=conn.createStatement();
           System.out.println("db is connected");
       }catch(Exception e){
           System.out.println(e);
       }
   }
   
   public ResultSet matchLogin(String password,String email){
       String sql = "select * from users where password='"+password+"' and email='"+email+"'";
       try {
           rs=st.executeQuery(sql);
       }catch(Exception e){
           System.out.println(e);
       }
       return rs;
   }
   
   public int createAccount(String fullName,String email,String password){
       int result = 0;
       String sql = "INSERT INTO users (full_name, email, password) VALUES (?, ?, ?)";
       try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fullName);
            stmt.setString(2, email);
            stmt.setString(3, password);
            result = stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
       
   }
   
   public ResultSet SQL_Query(String sql_query){
       String sql = sql_query;
       try {
           rs=st.executeQuery(sql);
       }catch(Exception e){
           System.out.println(e);
       }
       return rs;
   }
   
   public UserData getUserData(String email){
       String query = "Select * from users where email = '"+email+"'";
       UserData user_data = null;
       try {
           rs=st.executeQuery(query);
              
        while(rs.next()){
            String full_name = rs.getString("full_name");
            int user_id = rs.getInt("id");
            user_data = new UserData(email, full_name, user_id);
        }
       }catch(Exception e){
           System.out.println(e);
       }
       return user_data;
       
    }
   
   public int createCategory(String name,String type,int user_id){
       int result = 0;
       String sql = "INSERT INTO categories (user_id, name, type) VALUES (?, ?, ?)";
       try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, user_id);
            stmt.setString(2, name);
            stmt.setString(3, type);
            result = stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
   }
   
   public int createTransaction(int amount,String type,String description,boolean is_recurring,int category_id,int user_id){
       int result = 0;
       String sql = "INSERT INTO transactions (amount,type,description,is_recurring,category_id,user_id) VALUES (?, ?, ?,?,?,?)";
       try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, amount);
            stmt.setString(2, type);
            stmt.setString(3, description);
            stmt.setBoolean(4, is_recurring);
            stmt.setInt(5, category_id);
            stmt.setInt(6, user_id);
            result = stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
   }
}
   

