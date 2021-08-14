
package campany;

import jaco.mp3.player.MP3Player;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class Tools {
    
    public static void msgBox (String message){
        JOptionPane.showMessageDialog(null, message);
    }
    
    public static boolean confirmMsg(String message){
      ImageIcon icon = new ImageIcon(Tools.class.getResource("emp1.png"));
      int msgC = JOptionPane.showConfirmDialog(null, message, "Select an Option", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, icon);
        if (msgC == JOptionPane.YES_OPTION){
            return true;
        }
        else{
            return false;
        }
    }
    
    public static void openForm(JFrame form){
        
        try {
            form.setLocationRelativeTo(null);
            Image img = ImageIO.read(Tools.class.getResource("emp.png"));
            form.setIconImage(img);
            //form.setDefaultCloseOperation(2);
            form.getContentPane().setBackground(Color.WHITE);
            form.setVisible(true);
        } catch (IOException ex) {
            msgBox(ex.getMessage());
        }
        
    }
    
    public class Table {
        
        public int columns;
        public Object[][] Items;
        
        public Table (int columns){
            this.columns = columns;
            Items =new Object[0][columns];
        }
        
        public void addNewRow (Object row []){
           Object TempItems [][] = Items;
           Items = new Object[Items.length+1][columns];
           for (int x=0 ; x< TempItems.length ; x++){
               Items[x]=TempItems[x];
           }
           Items[Items.length-1]=row;
           
        }
        
        public void printItems(){
            
            for(Object objs[]:Items){
                for (Object obj : objs){
                    System.out.print(obj + ";");
                }
                System.out.println();
                
            }
        }
        
        public void editRow(int rowIndex , int columnIndex , Object newData){
            Items[rowIndex][columnIndex]=newData;
        }
    }
    
    public static void clearText (Container form){
        for(Component c : form.getComponents()){
            if (c instanceof JTextField){
                JTextField txt = (JTextField)c;
                txt.setText("");
            }
            else if (c instanceof Container){
                clearText((Container) c);
            }
        }
    }
    
    public static Object InputBox (String title){
        ImageIcon icon = new ImageIcon(Tools.class.getResource("emp1.png"));
        Object myobj = JOptionPane.showInputDialog(null,title,"Input",JOptionPane.INFORMATION_MESSAGE,icon,null,"");
        return myobj;
    }
    
    public static void setReport(JTable table){
       table.getTableHeader().setBackground(new Color(0,0,170));
       table.getTableHeader().setForeground(Color.WHITE);
    }
    
    public static void printReprt(JTable table , String title){
        try{
            
            MessageFormat header =new MessageFormat(title+" Report");
            MessageFormat footer = new MessageFormat("Page - {0}"); // no. of page
            table.print(JTable.PrintMode.FIT_WIDTH,header,footer);
            
        }
        catch(Exception ex){
         Tools.msgBox(ex.getMessage());  
        }
    }
    
    
    public static void welcomeTone (){
     String song = "C:\\Users\\hp\\Documents\\NetBeansProjects111111111\\Campany\\welcome.mp3";
     MP3Player mp3 =new MP3Player(new File(song));
     mp3.play();
    }
    
    public static void helloTone (){
     String song = "C:\\Users\\hp\\Documents\\NetBeansProjects111111111\\Campany\\hello.mp3";
     MP3Player mp3 =new MP3Player(new File(song));
     mp3.play();
    }
    
    public static void doneTone (){
     String song = "C:\\Users\\hp\\Documents\\NetBeansProjects111111111\\Campany\\done.mp3";
     MP3Player mp3 =new MP3Player(new File(song));
     mp3.play();
    }
}
