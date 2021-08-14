
package Entity;

import campany.Tools;
import javax.swing.JTable;


public class Employee implements mainData{
    
    private int EmpNo;
    private String EmpName;
    private String Address;
    private double Salary;
    private String HiringDate;
    private String BirthDate;
    private int DeptNo;

    public int getEmpNo() {
        return EmpNo;
    }

    public void setEmpNo(int EmpNo) {
        this.EmpNo = EmpNo;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String EmpName) {
        this.EmpName = EmpName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double Salary) {
        this.Salary = Salary;
    }

    public String getHiringDate() {
        return HiringDate;
    }

    public void setHiringDate(String HiringDate) {
        this.HiringDate = HiringDate;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String BirthDate) {
        this.BirthDate = BirthDate;
    }

    public int getDeptNo() {
        return DeptNo;
    }

    public void setDeptNo(int DeptNo) {
        this.DeptNo = DeptNo;
    }

    @Override
    public void add() {
        String strInsert = "insert into employee values ("+ EmpNo + ",'" + EmpName + "','" +Address + "'," + Salary + ",'" +HiringDate + "','" + BirthDate + "'," + DeptNo + ")";
        boolean isAdd = db.go.runNonQuery(strInsert);
        if (isAdd){
            Tools.doneTone();
            Tools.msgBox("Employee Is Added...");
        }
    }

    @Override
    public void update() {
        String strUpdate = "update employee set " + "empname = '" + EmpName + "', address = '" + Address + "', Salary = " + Salary + ", HiringDate = '" + HiringDate + "' , BirthDate = '" + BirthDate + "' , DeptNo = " + DeptNo + " where EmpNo = " + EmpNo ;
        boolean isUpdate = db.go.runNonQuery(strUpdate);
        if (isUpdate){
            Tools.doneTone();
            Tools.msgBox("Employee Is Update...");
        }
    }

    @Override
    public void delete() {
        String delete = "delete from employee"+" where EmpNo = " + EmpNo;
        boolean isDeleted = db.go.runNonQuery(delete);
        if (isDeleted){
            Tools.doneTone();
            Tools.msgBox("Employee Is Deleted...");
        }
    }

    @Override
    public String getAutoNumber() {
        String Auto = db.go.getAutoNumber("employee", "EmpNo");
        return Auto;
    }

    @Override
    public void getAllRows(JTable table) {
        db.go.fillToJTable("employee_data", table);
    }

    @Override
    public void getOneRow(JTable table) {
     String select = "select * from employee_data"+" where Number = " + EmpNo;
     db.go.fillToJTable(select, table);
    }

    @Override
    public void getCustomRows(String statement, JTable table) {
     db.go.fillToJTable(statement, table);
    }

    @Override
    public String getValueByName(String name) {
        String select = "select empno from employee " + "where EmpName = '" + name + "'";
        String val = (String)db.go.getTableData(select).Items[0][0];// unique number
        return val;
    }

    @Override
    public String getNameByValue(String value) {
        String select = "select empname from employee " + "where empno = " + value;
        String name = (String)db.go.getTableData(select).Items[0][0];
        return name;
    }
    
}
