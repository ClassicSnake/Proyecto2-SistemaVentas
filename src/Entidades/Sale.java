/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author George
 */
@Entity
@Table(name = "sale")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sale.findAll", query = "SELECT s FROM Sale s")
    , @NamedQuery(name = "Sale.findByIdSale", query = "SELECT s FROM Sale s WHERE s.idSale = :idSale")
    , @NamedQuery(name = "Sale.findByDate", query = "SELECT s FROM Sale s WHERE s.date = :date")
    , @NamedQuery(name = "Sale.findByDiscount", query = "SELECT s FROM Sale s WHERE s.discount = :discount")
    , @NamedQuery(name = "Sale.findByTotal", query = "SELECT s FROM Sale s WHERE s.total = :total")})
public class Sale implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSale")
    private Integer idSale;
    @Column(name = "date")
    private String date;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "discount")
    private Float discount;
    @Column(name = "total")
    private Float total;

    
    @OneToMany(mappedBy = "sale")
    private List <Saleitem> Saleitems;
    

    public List<Saleitem> getSaleitems() {
        return Saleitems;
    }

    public void setSaleitems(List<Saleitem> Saleitems) {
        this.Saleitems = Saleitems;
    }

    
  @ManyToOne
 @JoinColumn(name = "idcustomer")
 private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

  
  
    
    public Sale() {
    }

    public Sale(Integer idSale) {
        this.idSale = idSale;
    }

    public Integer getIdSale() {
        return idSale;
    }

    public void setIdSale(Integer idSale) {
        this.idSale = idSale;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
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
        hash += (idSale != null ? idSale.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sale)) {
            return false;
        }
        Sale other = (Sale) object;
        if ((this.idSale == null && other.idSale != null) || (this.idSale != null && !this.idSale.equals(other.idSale))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Sale[ idSale=" + idSale + " ]";
    }
    
}
