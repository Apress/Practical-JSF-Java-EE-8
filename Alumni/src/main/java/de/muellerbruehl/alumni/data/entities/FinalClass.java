
package de.muellerbruehl.alumni.data.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author mmueller
 */
@Entity
public class FinalClass implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id = -1;
    private String _name = "";
    private List<Account> _alumni = new Vector<>();
// <editor-fold defaultstate="collapsed" desc="getter / setter">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name must not be null");
        }
        _name = name;
    }

    
    // </editor-fold>    
// <editor-fold defaultstate="collapsed" desc="hashCode / equals / toString">
  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinalClass)) {
            return false;
        }
        FinalClass other = (FinalClass) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.muellerbruehl.classreunion.Class[ id=" + id + " ]";
    }
  // </editor-fold>    

}
