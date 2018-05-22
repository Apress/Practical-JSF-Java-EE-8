package de.muellerbruehl.alumni.data.entities;

import de.muellerbruehl.alumni.util.UuidUtil;
import java.io.Serializable;
import java.util.Arrays;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author mmueller
 */
@Entity
@Table(name = "FinalYear")
public class FinalYear implements Serializable {

  private static final long serialVersionUID = 1L;

  // <editor-fold defaultstate="collapsed" desc="Property Id">
  @Id
  @Column(name = "id")
  private byte[] _id = UuidUtil.toBytes(UUID.randomUUID());

  public byte[] getId() {
    return _id;
  }

  public void setId(byte[] id) {
    _id = id;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property CollegeId">
  @Column(name = "collegeId")
  private byte[] _collegeId;

  public byte[] getCollegeId() {
    return _collegeId;
  }

  public void setCollegeId(byte[] collegeId) {
    this._collegeId = collegeId;
  }
  // </editor-fold>    

  // <editor-fold defaultstate="collapsed" desc="Property Year">
  @Column(name = "year")
  private int _year = -1;

  public int getYear() {
    return _year;
  }

  public void setYear(int year) {
    this._year = year;
  }
  // </editor-fold>    

  // <editor-fold defaultstate="collapsed" desc="Property Name">
  @Column(name = "name")
  private String _name = "";

  public String getName() {
    return _name;
  }

  public void setName(String name) {
    _name = name;
  }
  // </editor-fold>    

//  @ManyToMany(mappedBy = "_finalYears")
//  private List<Account> accounts;

  // <editor-fold defaultstate="collapsed" desc="hashCode / equals / toString">
  @Override
  public int hashCode() {
    int hash = 5;
    hash = 71 * hash + Arrays.hashCode(this._id);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final FinalYear other = (FinalYear) obj;
    if (!Arrays.equals(this._id, other._id)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "FinalYear[ id=" + _year + " ]";
  }
  // </editor-fold>    

}
