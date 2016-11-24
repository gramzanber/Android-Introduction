package edu.uco.ttachibana.p5TyrelT;

public class Department
{
    public String departmentName;
    public String departmentUrl;


    public Department(String name, String Url)
    {
        this.departmentName = name;
        this.departmentUrl = Url;
    }

    public String get_name() {
        return departmentName;
    }
    public String get_Url() {
        return departmentUrl;
    }
    @Override public String toString() {
        return this.departmentName;
    }
}
