
package Entity;

import campany.Tools;
import javax.swing.JTable;


public class Department implements mainData{
    
    private int DeptNo;
    private String DeptName;
    private String Location;

    public int getDeptNo() {
        return DeptNo;
    }

    public void setDeptNo(int DeptNo) {
        this.DeptNo = DeptNo;
    }

    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String DeptName) {
        this.DeptName = DeptName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    @Override
    public void add() {
        String insert = "insert into department values (" + DeptNo + ",'" +DeptName + "','" + Location + "')";
        boolean isAdd = db.go.runNonQuery(insert);
        if (isAdd){
            Tools.doneTone();
            Tools.msgBox("Department Is Added...");
        }
    }

    @Override
    public void update() {
       String update = "update department set "+ "DeptName='"+DeptName+"',"+ "Location='"+Location + "' "+ "where DeptNo = " + DeptNo;
       boolean isUpdate = db.go.runNonQuery(update);
       if (isUpdate){
           Tools.doneTone();
           Tools.msgBox("Department Is Updated...");
       }
    }

    @Override
    public void delete() {
        String delete = "delete from department"+" where DeptNo = " + DeptNo;
        boolean isDeleted = db.go.runNonQuery(delete);
        if (isDeleted){
            Tools.doneTone();
            Tools.msgBox("Department Is Deleted...");
        }
    }

    @Override
    public String getAutoNumber() {
     String Auto = db.go.getAutoNumber("department", "DeptNo");
     return Auto;
    }

    @Override
    public void getAllRows(JTable table) {
     db.go.fillToJTable("department_data", table);
    }

    @Override
    public void getOneRow(JTable table) {
     String select = "select * from department_data"+" where Department_No = " + DeptNo;
     db.go.fillToJTable(select, table);
    }

    @Override
    public void getCustomRows(String statement, JTable table) {
        db.go.fillToJTable(statement, table);
    }

    @Override
    public String getValueByName(String name) {
        String select = "select deptno from department " + "where deptname = '" + name + "'";
        String val = (String)db.go.getTableData(select).Items[0][0];// unique number
        return val;
    }

    @Override
    public String getNameByValue(String value) {
        String select = "select deptname from department " + "where deptno = " + value;
        String name = (String)db.go.getTableData(select).Items[0][0];
        return name;         
    }
    
}
