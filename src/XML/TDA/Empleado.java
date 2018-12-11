package XML.TDA;

import java.sql.Date;

/**
 * Created by usuario on 28/05/17.
 */
public class Empleado {
    private Integer emp_no;
    private String last_name;
    private String first_name;
    private String title;
    private Integer reports_to;
    private Date birth_date;
    private Date hire_date;
    private String adress;
    private String city;
    private String state;
    private String country;
    private String postal_code;
    private String phone;
    private String fax;
    private String email;
    private String boss;

    public Empleado(Integer emp_no, String last_name, String first_name, String title, Integer reports_to, Date birth_date, Date hire_date, String adress, String city, String state, String country, String postal_code, String phone, String fax, String email, String boss) {
        this.emp_no = emp_no;
        this.last_name = last_name;
        this.first_name = first_name;
        this.title = title;
        this.reports_to = reports_to;
        this.birth_date = birth_date;
        this.hire_date = hire_date;
        this.adress = adress;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postal_code = postal_code;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.boss = boss;
    }

    public Empleado(String last_name, String first_name, String title, Integer reports_to, Date birth_date, Date hire_date, String adress, String city, String state, String country, String postal_code, String phone, String fax, String email, String boss) {
        this.last_name = last_name;
        this.first_name = first_name;
        this.title = title;
        this.reports_to = reports_to;
        this.birth_date = birth_date;
        this.hire_date = hire_date;
        this.adress = adress;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postal_code = postal_code;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.boss = boss;
    }

    public Integer getEmp_no() {

        return emp_no;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getTitle() {
        return title;
    }

    public Integer getReports_to() {
        return reports_to;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public String getAdress() {
        return adress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public String getPhone() {
        return phone;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail() {
        return email;
    }

    public String getBoss() {
        return boss;
    }

    public String toString() {
        return first_name+" "+last_name;
    }
}