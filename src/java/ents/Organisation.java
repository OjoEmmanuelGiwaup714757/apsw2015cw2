/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ents;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author up792072
 */
@Entity
public class Organisation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String organisationname;
    private String organisationaccountdesc;
    private String organisationaddress;
    private String organisationpostcode;
    
    @OneToMany(mappedBy = "belongOrg")
    private List<Registation> user;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganisationname() {
        return organisationname;
    }

    public void setOrganisationname(String organisationname) {
        this.organisationname = organisationname;
    }

    public String getOrganisationaccountdesc() {
        return organisationaccountdesc;
    }

    public void setOrganisationaccountdesc(String organisationaccountdesc) {
        this.organisationaccountdesc = organisationaccountdesc;
    }

    public String getOrganisationaddress() {
        return organisationaddress;
    }

    public void setOrganisationaddress(String organisationaddress) {
        this.organisationaddress = organisationaddress;
    }

    public String getOrganisationpostcode() {
        return organisationpostcode;
    }

    public void setOrganisationpostcode(String organisationpostcode) {
        this.organisationpostcode = organisationpostcode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Organisation)) {
            return false;
        }
        Organisation other = (Organisation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getOrganisationname();
    }
    
}
