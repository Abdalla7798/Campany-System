
package Entity;

import campany.Tools;
import javax.swing.JTable;


public class Project implements mainData{
   
    private int ProjectNo;
    private String ProjectName; 
    private String Location ;
    private int DeptNo;

    public int getProjectNo() {
        return ProjectNo;
    }

    public void setProjectNo(int ProjectNo) {
        this.ProjectNo = ProjectNo;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public int getDeptNo() {
        return DeptNo;
    }

    public void setDeptNo(int DeptNo) {
        this.DeptNo = DeptNo;
    }

    @Override
    public void add() {
        String strInsert = "insert into project values (" + ProjectNo + ",'" + ProjectName + "','" +Location + "'," + DeptNo + ")";
        boolean isAdd = db.go.runNonQuery(strInsert);
        if (isAdd){
            Tools.doneTone();
            Tools.msgBox("Project Is Added...");
        }
    }

    @Override
    public void update() {
        String strUpdate = "update project set ProjectName = '" + ProjectName + "', Location = '" + Location + "', DeptNo = " + DeptNo + " where projectno = " + ProjectNo ;
        boolean isUpdate = db.go.runNonQuery(strUpdate);
        if (isUpdate){
            Tools.doneTone();
            Tools.msgBox("Project Is Update...");
        }
    }

    @Override
    public void delete() {
        String delete = "delete from project "+" where projectno = " + ProjectNo;
        boolean isDeleted = db.go.runNonQuery(delete);
        if (isDeleted){
            Tools.doneTone();
            Tools.msgBox("Project Is Deleted...");
        }
    }

    @Override
    public String getAutoNumber() {
        String Auto = db.go.getAutoNumber("project", "projectno");
        return Auto;
    }

    @Override
    public void getAllRows(JTable table) {
     db.go.fillToJTable("project_data", table);
    }

    @Override
    public void getOneRow(JTable table) {
     String select = "select * from project_data"+" where Project_No = " + ProjectNo;
     db.go.fillToJTable(select, table);  
    }

    @Override
    public void getCustomRows(String statement, JTable table) {
     db.go.fillToJTable(statement, table);
    }

    @Override
    public String getValueByName(String name) {
        String select = "select projectno from project " + "where projectName = '" + name + "'";
        String val = (String)db.go.getTableData(select).Items[0][0];// unique number
        return val;
    }

    @Override
    public String getNameByValue(String value) {
        String select = "select projectname from project " + "where projectno = " + value;
        String name = (String)db.go.getTableData(select).Items[0][0];
        return name;
    }
    
    
}
