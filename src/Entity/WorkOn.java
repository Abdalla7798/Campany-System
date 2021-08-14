
package Entity;

import campany.Tools;
import javax.swing.JTable;


public class WorkOn implements mainData{
    
    private int EmpNo;
    private int ProjectNo;

    public int getEmpNo() {
        return EmpNo;
    }

    public void setEmpNo(int EmpNo) {
        this.EmpNo = EmpNo;
    }

    public int getProjectNo() {
        return ProjectNo;
    }

    public void setProjectNo(int ProjectNo) {
        this.ProjectNo = ProjectNo;
    }

    @Override
    public void add() {
        String strInsert = "insert into workon values (" + EmpNo + "," +ProjectNo + ")";
        boolean isAdd = db.go.runNonQuery(strInsert);
        if (isAdd){
            Tools.doneTone();
            Tools.msgBox("WorkOn Is Added...");
        }
    }

    @Override
    public void update() {
        Tools.msgBox("Update Method In WorkOn Class Not Working!");
    }

    @Override
    public void delete() {
        String delete = "delete from workon "+" where EmpNo = " + EmpNo + " and ProjectNo = " + ProjectNo;
        boolean isDeleted = db.go.runNonQuery(delete);
        if (isDeleted){
            Tools.doneTone();
            Tools.msgBox("WorkOn Is Deleted...");
        }
    }

    @Override
    public String getAutoNumber() {
        Tools.msgBox("getAutoNumber Method In WorkOn Class Not Working!");
        return "";
    }

    @Override
    public void getAllRows(JTable table) {
        db.go.fillToJTable("WorkOn_Data", table);
    }

    @Override
    public void getOneRow(JTable table) {
        String select = "select * from workon_data"+" where EMPLOYEE_NO = " + EmpNo + " and PROJECT_NO = " + ProjectNo ;
        db.go.fillToJTable(select, table);
    }

    @Override
    public void getCustomRows(String statement, JTable table) {
        db.go.fillToJTable(statement, table);
    }

    @Override
    public String getValueByName(String name) {
        Tools.msgBox("getValueByName Method In WorkOn Class Not Working!");
        return "";
    }

    @Override
    public String getNameByValue(String value) {
        Tools.msgBox("getNameByValue Method In WorkOn Class Not Working!");
        return "";
    }
    
}
