
package db;

import Forms.frmLogin;
import campany.Tools.Table;
import campany.Tools;
import java.awt.Window;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class go {
    
    private static String url ="";
    public static String DBName = "a.b.abdalla305@gmail.com";
    private static Connection con ;
    
    private static void setURL (){
        url = "jdbc:mysql://localhost:3306/"+DBName+"?useUnicode=true&characterEncoding=UTF-8";// reach DB and make it support Arabic
    }
    
    private static void setConnection (){  //connect java with DB 
        
        try {
            setURL();
            con = DriverManager.getConnection(url,"root","");
        } catch (SQLException ex) {
              Window win[] = Window.getWindows();
              if (win[0] instanceof frmLogin){
              Tools.msgBox("Usernmae Or Password Incorrect...");
              }
              else {
              Tools.msgBox(ex.getMessage());
              }
          }    
    }
    
    
    
    public static boolean checkEmailAndPass(String email , String password){
        try{
            setConnection();
            Statement stmt = con.createStatement();
            String strCheck = "select email , password from users where "+"email='"+email+"'and "+"password = PASSWORD('" +password+ "')";
            stmt.executeQuery(strCheck);
            while (stmt.getResultSet().next()){
                con.close();
                return true;
            }
            //con.close();
        }
        catch (SQLException ex){
            Tools.msgBox("Password Incorrect...");
        }
        return false ;
    }
    
    public static void welcome (JLabel label){
        
    try {
            setConnection();
            Statement stmt = con.createStatement();
            String strCheck = "select username from users";
            ResultSet rs ;
            rs=stmt.executeQuery(strCheck);
            String username ="";
            while (rs.next()) {
              username = rs.getString(1);
            }
            
            label.setText("Welcome  "+ username.toUpperCase() + "  to Main Form");
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            
        } catch (SQLException ex) {
           Tools.msgBox(ex.getMessage());
        }
    }
    public static void getEmail(JLabel label){
        
        try {
            setConnection();
            Statement stmt = con.createStatement();
            String strCheck = "select email from users";
            ResultSet rs ;
            rs=stmt.executeQuery(strCheck);
            String email ="";
            while (rs.next()) {
              email = rs.getString(1);
            }
            
            label.setText("We send your code to: "+email+" ..... Sending message maybe in 10 minutes");
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            
        } catch (SQLException ex) {
           Tools.msgBox(ex.getMessage());
        }
    }
    public static boolean runNonQuery (String sqlStatment){ // run without query like insert , update, delete
        
        try {
            
            setConnection();
            Statement stmt = con.createStatement();
            stmt.execute(sqlStatment);
            con.close();
            return true;
        }
        catch(SQLException ex){
            Tools.msgBox(ex.getMessage());
            return false;
        }
    }
    
    public static boolean IsDBExist(String dbName) throws SQLException{
        
        try {
            ArrayList<String> list=new ArrayList <String> ();
            Class.forName("com.mysql.jdbc.Driver");
            Connection co = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
            Statement st = co.createStatement();
            DatabaseMetaData meta = co.getMetaData();
            ResultSet rs = meta.getCatalogs();
            while (rs.next()) {
                String listofDatabases=rs.getString("TABLE_CAT");
                list.add(listofDatabases);
            }
            if(list.contains(dbName)){
                return true ;
            }
            co.close();
        } catch (ClassNotFoundException ex) {
           ex.getMessage();
        }
        return false;
    }
    
    public static boolean createDataBaseAndInformation (String dbName){
        
        try {
            
      Class.forName("com.mysql.jdbc.Driver");
      Connection co = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
      Statement statement = co.createStatement();
      String dbCreate = "CREATE DATABASE `"+dbName+"` DEFAULT CHARACTER SET UTF8 DEFAULT COLLATE UTF8_GENERAL_CI" ;
      statement.executeUpdate(dbCreate);
      co.close();
      
      Connection co1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName+"?useUnicode=true&characterEncoding=UTF-8","root","");
      
      String TableInfo1 = "CREATE TABLE DEPARTMENT (DEPTNO INT PRIMARY KEY,DEPTNAME VARCHAR(150) UNIQUE,LOCATION VARCHAR(100))";
      Statement statement1 = co1.createStatement();
      statement1.executeUpdate(TableInfo1);
      
      String TableInfo2 = "CREATE TABLE PROJECT (PROJECTNO INT PRIMARY KEY ,PROJECTNAME VARCHAR (150)UNIQUE,LOCATION VARCHAR (100),DEPTNO INT ,CONSTRAINT FOREIGN KEY (DEPTNO) REFERENCES DEPARTMENT(DEPTNO))";
      Statement statement2 = co1.createStatement();
      statement2.executeUpdate(TableInfo2);
      
      String TableInfo3 = "CREATE TABLE EMPLOYEE (EMPNO INT PRIMARY KEY ,EMPNAME VARCHAR (120) UNIQUE,ADDRESS VARCHAR (200) ,SALARY DOUBLE ,HIRINGDATE DATE ,BIRTHDATE DATE,DEPTNO INT ,CONSTRAINT FOREIGN KEY (DEPTNO) REFERENCES DEPARTMENT(DEPTNO))";
      Statement statement3 = co1.createStatement();
      statement3.executeUpdate(TableInfo3);
      
      String TableInfo4 = "CREATE TABLE EMPLOYEE_PHONES (EMPNO INT ,PHONE VARCHAR (55),PRIMARY KEY (EMPNO,PHONE),CONSTRAINT FOREIGN KEY (EMPNO) REFERENCES EMPLOYEE(EMPNO))";
      Statement statement4 = co1.createStatement();
      statement4.executeUpdate(TableInfo4);
      
      String TableInfo5 = "CREATE TABLE WORKON (EMPNO INT ,PROJECTNO INT,PRIMARY KEY (EMPNO,PROJECTNO),CONSTRAINT FOREIGN KEY (EMPNO) REFERENCES EMPLOYEE(EMPNO),CONSTRAINT FOREIGN KEY (PROJECTNO) REFERENCES PROJECT(PROJECTNO))";
      Statement statement5 = co1.createStatement();
      statement5.executeUpdate(TableInfo5);
      
      String TableInfo6 = "CREATE TABLE USERS(USERNAME VARCHAR (40) PRIMARY KEY,PASSWORD BLOB NOT NULL, EMAIL VARCHAR (100) UNIQUE , BIRTHDATE DATE , GENDER VARCHAR (10))";
      Statement statement6 = co1.createStatement();
      statement6.executeUpdate(TableInfo6);
      
      Connection co2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName+"?useUnicode=true&characterEncoding=UTF-8","root","");
      
      String ViewsInfo1 = "CREATE VIEW DEPARTMENT_DATA AS SELECT DEPTNO AS 'DEPARTMENT_NO', DEPTNAME AS 'DEPARTMENT', LOCATION FROM DEPARTMENT";
      Statement statement7 = co1.createStatement();
      statement7.executeUpdate(ViewsInfo1);
      
      String ViewsInfo2 = "CREATE VIEW EMPLOYEE_DATA AS SELECT EMPNO AS 'NUMBER',EMPNAME AS 'NAME', ADDRESS, SALARY , HIRINGDATE AS 'HIRING_DATE',BIRTHDATE AS 'DATE_OF_BIRTH', EMPLOYEE.DEPTNO AS 'DEPARTMENT_NO', DEPTNAME AS 'DEPARTMENT' FROM EMPLOYEE , DEPARTMENT WHERE EMPLOYEE.DEPTNO=DEPARTMENT.DEPTNO";
      Statement statement8 = co1.createStatement();
      statement8.executeUpdate(ViewsInfo2);
      
      String ViewsInfo3 = "CREATE VIEW PROJECT_DATA AS SELECT PROJECTNO AS 'PROJECT_NO', PROJECTNAME AS 'PROJECT_NAME', PROJECT.LOCATION , PROJECT.DEPTNO AS 'DEARTMENT_NO', DEPTNAME AS 'DEPARTMENT' FROM PROJECT , DEPARTMENT WHERE PROJECT.DEPTNO =DEPARTMENT.DEPTNO";
      Statement statement9 = co1.createStatement();
      statement9.executeUpdate(ViewsInfo3);
      
      String ViewsInfo4 = "CREATE VIEW EMPLOYEE_PHONES_DATA AS SELECT EMPLOYEE.EMPNO AS 'EMPLOYEE_NO', EMPNAME AS 'EMPLOYEE_NAME', PHONE FROM EMPLOYEE , EMPLOYEE_PHONES WHERE EMPLOYEE_PHONES.EMPNO=EMPLOYEE.EMPNO";
      Statement statement10 = co1.createStatement();
      statement10.executeUpdate(ViewsInfo4);
      
      String ViewsInfo5 = "CREATE VIEW WORKON_DATA AS SELECT WORKON.EMPNO AS 'EMPLOYEE_NO', EMPNAME AS 'EMPLOYEE_NAME', WORKON.PROJECTNO AS 'PROJECT_NO', PROJECTNAME AS 'PROJECT_NAME' FROM WORKON , EMPLOYEE , PROJECT WHERE WORKON.EMPNO=EMPLOYEE.EMPNO AND WORKON.PROJECTNO=PROJECT.PROJECTNO";
      Statement statement11 = co1.createStatement();
      statement11.executeUpdate(ViewsInfo5);
      
      co1.close();
      co2.close();
      return true;
    
    } catch (ClassNotFoundException | SQLException ex) {
        Tools.msgBox(ex.getMessage());
        return false;
    } 
}
    
//    public static boolean IsThePhoneIsExist (String phone){
//        try {
//            String check = "SELECT phone from employee_phones where phone = '" + phone + "'";
//            setConnection();
//            Statement stmt = con.createStatement();
//            
//            stmt.executeQuery(check);
//            String ph = "";
//            while (stmt.getResultSet().next()){
//                ph = stmt.getResultSet().getString(1);
//            }
//            if (ph.equals(phone)){
//               con.close();
//               return true; 
//            }
//            
//        } catch (SQLException ex) {
//            Tools.msgBox(ex.getMessage());
//        }
//        return false;
//    }
    
    public static String getAutoNumber(String tableName, String columnName){
        
       try{
           setConnection();
           Statement stmt = con.createStatement();
           String strAuto = "select max("+columnName+")+1 as autoNum"+" from "+ tableName;
           stmt.executeQuery(strAuto);
           String Num="";
           while (stmt.getResultSet().next()){
              Num = stmt.getResultSet().getString("autoNum"); // الاسم الافتراضى للعمود الى انت مسميه
           }
           con.close();
           
           if(Num == null || "".equals(Num)){
                 return "1"; 
              } 
              else {
                  return Num;
              }
       } 
       catch(SQLException ex){
           Tools.msgBox(ex.getMessage());
           return "0";
       }
    }
    
    public static Table getTableData(String statement){
        Tools t =new Tools();
        try {
            setConnection();
            Statement stmt = con.createStatement();
            ResultSet rs;
            rs=stmt.executeQuery(statement);
            ResultSetMetaData rsmd =rs.getMetaData();
            int c =rsmd.getColumnCount();
            
            Table table = t.new Table(c);
            while (rs.next()){
                Object row[] =new Object[c];
                for(int i =0 ; i < c ; i++){
                    row[i]=rs.getString(i+1);//(i+1) no.of column
                }
                table.addNewRow(row);
            }
            con.close();
            return table;
        }
        catch (SQLException ex){
            Tools.msgBox(ex.getMessage());
            return t.new Table(0);
        }
    }
    public static void fillComb(String tableName, String columnName , JComboBox combo){
     
        try {
            setConnection();
            Statement stmt = con.createStatement();
            ResultSet rs ;
            String strSelect = "select "+columnName + " from "+ tableName;
            rs=stmt.executeQuery(strSelect);
            // get the number of row
            rs.last(); //point cursor to the last row
            int c = rs.getRow();// get the current row
            rs.beforeFirst(); //put the cursor at the original location 
            
            String values []= new String [c];
            int i =0;
            while (rs.next()){// pass all the result rows
                values[i]=rs.getString(1); // 1 is the column index
                i++;
            }
            con.close();
            combo.setModel(new DefaultComboBoxModel(values));
        }
        catch(SQLException ex ){
            Tools.msgBox(ex.getMessage());
        }
    }
    
    public static void fillToJTable(String tableNameOrSelectStatement , JTable table){
        try{
            
            setConnection();
            Statement stmt = con.createStatement();
            ResultSet rs;
            String SPart = tableNameOrSelectStatement.substring(0, 7).toLowerCase() ;
            String strSelect;
            if ("select ".equals(SPart)){
                strSelect = tableNameOrSelectStatement;
            }
            else {
                strSelect = "select * from "+tableNameOrSelectStatement;
            }
            rs = stmt.executeQuery(strSelect);
            ResultSetMetaData rsmd = rs.getMetaData();
            int c = rsmd.getColumnCount();
            
            DefaultTableModel model =(DefaultTableModel)table.getModel();
            
            Vector row = new Vector();
            model.setRowCount(0);
            
            while (rs.next()){
                row = new Vector(c);
                for (int i = 1 ; i<=c;i++){
                    row.add(rs.getString(i));
                }
                model.addRow(row);
            }
            if (table.getColumnCount()!=c){
                Tools.msgBox("JTable Columns Not Equal To Query Columns Count\nJTable Columns Count Is: "+table.getColumnCount()+"\nQuery Colmuns Count Is: "+c);
            }
            con.close();
        }
        catch (SQLException ex){
            Tools.msgBox(ex.getMessage());
        }
    }
    
    public static boolean sendEmail (String message ,String RecipientEmail){
        
        try{
           
            String host ="smtp.gmail.com" ;
            String user = "company.system2018@gmail.com";
            String pass = "companysystem";
            String to = RecipientEmail ;
            String from = "comany.system2018@gmail.com";
            String subject = "Verification Code";
            String messageText = "your verification code is " + message ;
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            
            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            mailSession.getProperties().put("mail.smtp.ssl.trust", "smtp.gmail.com");
            mailSession.getProperties().put("mail.smtp.starttls.enable", "true");
            
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           //Tools.msgBox("Message Send Successfully...\n\nSending Message may be in 10 minutes...");
           return true ;
        }catch(MessagingException ex)
        {
            //Tools.msgBox(ex.toString());
            System.out.println(ex);
            return false ;
        }
    }
    
    public static boolean IsValidEmail (String email){
        //if (email.matches("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$") && !email.contains("+") && email.contains("@gmail.com")){
        if (email.matches("[a-zA-Z][0-9|a-zA-Z|.|-|-|_]+@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$") && !email.contains("+") && !email.contains("..") && !email.contains(".-") && !email.contains("-.") && !email.contains("._") && !email.contains("_.") && !email.contains("-_") && !email.contains("_-") && !email.contains("--") && !email.contains("__") && email.contains("@gmail.com")){
            return true;
        }
        return false ;
    }
//    public static String removeSpecialChar(String str){
//        String st = str.replace(".", "1"); // . replaces with 1
//        st = st.replace("-","2"); // - replaces with 2
//        //st = st.replace("_","");
//        return st;
//    }
    
    public static boolean IsPhoneExist (String phone){
        
        try {
           ArrayList<String> list=new ArrayList <String> ();
           String search = "SELECT TABLE_SCHEMA FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME LIKE 'employee_phones' and TABLE_SCHEMA != 'performance_schema'";
           Connection co = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
           Statement st = co.createStatement();
           ResultSet rs ;
           rs=st.executeQuery(search);
           
           while (rs.next()) {
              
              String listofDatabases=rs.getString(1);
              list.add(listofDatabases);
            }
           
           for (int i=0 ; i < list.size(); i++ ){
               String check = "select phone from employee_phones where phone = '"+ phone +"'";
               Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+list.get(i)+"?useUnicode=true&characterEncoding=UTF-8","root","");
               Statement st1 = con.createStatement();
               st1.executeQuery(check);
               while (st1.getResultSet().next()){
                con.close();
                return true;
               }
            }
           } catch (SQLException ex) {
            Tools.msgBox(ex.getMessage());
           }
    return false;
    }
    
}
