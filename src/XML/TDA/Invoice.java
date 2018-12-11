package XML.TDA;

import java.sql.Date;

/**
 * Created by usuario on 28/05/17.
 */
public class Invoice {
    private Integer invoice_id;
    private Integer customer_id;
    private Date invoice_date;
    private String billing_address;
    private String billing_city;
    private String billing_state;
    private String billing_country;
    private String billing_postal_code;
    private Double total;

    public Invoice(Integer invoice_id, Integer customer_id, Date invoice_date, String billing_address, String billing_city, String billing_state, String billing_country, String billing_postal_code, Double total) {
        this.invoice_id = invoice_id;
        this.customer_id = customer_id;
        this.invoice_date = invoice_date;
        this.billing_address = billing_address;
        this.billing_city = billing_city;
        this.billing_state = billing_state;
        this.billing_country = billing_country;
        this.billing_postal_code = billing_postal_code;
        this.total = total;
    }

    public Invoice(Integer customer_id, Date invoice_date, String billing_address, String billing_city, String billing_state, String billing_country, String billing_postal_code, Double total) {
        this.customer_id = customer_id;
        this.invoice_date = invoice_date;
        this.billing_address = billing_address;
        this.billing_city = billing_city;
        this.billing_state = billing_state;
        this.billing_country = billing_country;
        this.billing_postal_code = billing_postal_code;
        this.total = total;
    }

    public void setInvoice_id(Integer invoice_id) {
        this.invoice_id = invoice_id;
    }

    public Integer getInvoice_id() {
        return invoice_id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public Date getInvoice_date() {
        return invoice_date;
    }

    public String getBilling_address() {
        return billing_address;
    }

    public String getBilling_city() {
        return billing_city;
    }

    public String getBilling_state() {
        return billing_state;
    }

    public String getBilling_country() {
        return billing_country;
    }

    public String getBilling_postal_code() {
        return billing_postal_code;
    }

    public Double getTotal() {
        return total;
    }
}
