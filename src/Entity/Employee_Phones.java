
package Entity;

import campany.Tools;
import javax.swing.JTable;


public class Employee_Phones implements mainData{
    private int EmpNo;
    private String Phone;

    public int getEmpNo() {
        return EmpNo;
    }

    public void setEmpNo(int EmpNo) {
        this.EmpNo = EmpNo;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    @Override
    public void add() {
        String strInsert = "insert into employee_phones values ("+ EmpNo + ",'" +Phone + "' )";
        boolean isAdd = db.go.runNonQuery(strInsert);
        if (isAdd){
            //Tools.msgBox("Phone Is Added...");
        }
    }

    @Override
    public void update() {
        Tools.msgBox("Update Method In Employee_Phones Class Not Working!");
    }

    @Override
    public void delete() {
        String delete = "delete from employee_phones"+" where EmpNo = " + EmpNo + "and phone = '" + Phone + "'";
        boolean isDeleted = db.go.runNonQuery(delete);
        if (isDeleted){
            //Tools.msgBox("Phone Is Deleted...");
        }
    }
    public void deleteByEmp(){
       String strDelete = "delete from employee_phones where empno = "+ EmpNo;
       boolean isDeleted = db.go.runNonQuery(strDelete);
        if (isDeleted){
            //Tools.msgBox("Employee's Phones Is Deleted...");
        }
    }
    
    @Override
    public String getAutoNumber() {
        Tools.msgBox("getAutoNumber Method In Employee_Phones Class Not Working!");
        return "";
    }

    @Override
    public void getAllRows(JTable table) {
        String strSelect = "select phone from employee_phones where empno =  " + EmpNo;
        db.go.fillToJTable(strSelect , table);
    }

    @Override
    public void getOneRow(JTable table) {
        Tools.msgBox("getOneRow Method In Employee_Phones Class Not Working!");
    }

    @Override
    public void getCustomRows(String statement, JTable table) {
        Tools.msgBox("getCustomRows Method In Employee_Phones Class Not Working!");
    }

    @Override
    public String getValueByName(String name) {
        Tools.msgBox("getValueByName Method In Employee_Phones Class Not Working!");
        return "";
    }

    @Override
    public String getNameByValue(String value) {
        Tools.msgBox("getNameByValue Method In Employee_Phones Class Not Working!");
        return "";
    }
    
    public String getEmpNoByPhone (String reqPhone){
        String StrSelect = "select empno from employee_phones where phone = '" + reqPhone + "'"; 
        String strEmpNo;
        if (db.go.getTableData(StrSelect).Items.length >0){
           strEmpNo = (String)db.go.getTableData(StrSelect).Items[0][0];
        }
        else {
           strEmpNo = "0"; 
        }
        return strEmpNo;
    }
}
