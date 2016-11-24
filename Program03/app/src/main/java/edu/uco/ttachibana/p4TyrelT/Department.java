package edu.uco.ttachibana.p4TyrelT;


public class Department
{
    private String departmentName;
    private String departmentPhone;
    private String departmentUrl;

    public Department(String name, String phone, String url)
    {
        this.departmentName = name;
        this.departmentPhone = phone;
        this.departmentUrl = url;
    }

    public String get_name() {
        return this.departmentName;
    }
    public String get_phone(){
        return this.departmentPhone;
    }
    public String get_Url(){
        return this.departmentUrl;
    }
    @Override public String toString() { return this.departmentName; }
}
