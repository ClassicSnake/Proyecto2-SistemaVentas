/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author George
 */
@Entity
@Table(name = "saleitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Saleitem.findAll", query = "SELECT s FROM Saleitem s")
    , @NamedQuery(name = "Saleitem.findByIdSaleItem", query = "SELECT s FROM Saleitem s WHERE s.idSaleItem = :idSaleItem")
    , @NamedQuery(name = "Saleitem.findByPrice", query = "SELECT s FROM Saleitem s WHERE s.price = :price")
    , @NamedQuery(name = "Saleitem.findByQuantity", query = "SELECT s FROM Saleitem s WHERE s.quantity = :quantity")
    , @NamedQuery(name = "Saleitem.findByTotal", query = "SELECT s FROM Saleitem s WHERE s.total = :total")})
public class Saleitem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSaleItem")
    private Integer idSaleItem;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Float price;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "total")
    private Float total;

 @ManyToOne
@JoinColumn(name = "idproduct")
private Product product;

 public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
 
    
    @ManyToOne
    @JoinColumn(name = "idSale")
    private Sale sale;

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

 
    
    
    public Saleitem() {
    }

    public Saleitem(Integer idSaleItem) {
        this.idSaleItem = idSaleItem;
    }

    public Integer getIdSaleItem() {
        return idSaleItem;
    }

    public void setIdSaleItem(Integer idSaleItem) {
        this.idSaleItem = idSaleItem;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSaleItem != null ? idSaleItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Saleitem)) {
            return false;
        }
        Saleitem other = (Saleitem) object;
        if ((this.idSaleItem == null && other.idSaleItem != null) || (this.idSaleItem != null && !this.idSaleItem.equals(other.idSaleItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Saleitem[ idSaleItem=" + idSaleItem + " ]";
    }
    
}
