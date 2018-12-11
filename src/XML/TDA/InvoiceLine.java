package XML.TDA;

/**
 * Created by usuario on 28/05/17.
 */
public class InvoiceLine {
    private Integer invoice_line_id;
    private Integer invoice_id;
    private Integer track_id;
    private Double unit_price;
    private Integer quantity;

    public InvoiceLine(Integer invoice_line_id, Integer invoice_id, Integer track_id, Double unit_price, Integer quantity) {
        this.invoice_line_id = invoice_line_id;
        this.invoice_id = invoice_id;
        this.track_id = track_id;
        this.unit_price = unit_price;
        this.quantity = quantity;
    }

    public InvoiceLine(Integer invoice_id, Integer track_id, Double unit_price, Integer quantity) {
        this.invoice_id = invoice_id;
        this.track_id = track_id;
        this.unit_price = unit_price;
        this.quantity = quantity;
    }

    public Integer getInvoice_line_id() {
        return invoice_line_id;
    }

    public Integer getInvoice_id() {
        return invoice_id;
    }

    public Integer getTrack_id() {
        return track_id;
    }

    public Double getUnit_price() {
        return unit_price;
    }

    public Integer getQuantity() {
        return quantity;
    }

}
