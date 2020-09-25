package dat3.jpademo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author claes
 */
@Entity
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long p_id;
    private String name;
    private int born;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;
    
    @OneToMany(mappedBy = "person" ,cascade = CascadeType.PERSIST)
    private List<Fee> fees;
    
    @ManyToMany(mappedBy = "persons" ,cascade = CascadeType.PERSIST)
    private List<SwimStyle> styles;
    

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
        if (address != null) {
            address.setPerson(this);
        }
    }
    

    public Person() {
    }

    public Person(String name, int born) {
        this.name = name;
        this.born = born;
        this.fees = new ArrayList<>();
        this.styles = new ArrayList<>();
    }
    
    public void addSwimStyle(SwimStyle style) {
        if (style != null)
        this.styles.add(style);
        style.getPersons().add(this);
    }
    
    public void removeStyle(SwimStyle swimStyle) {
        if (swimStyle != null) {
            styles.remove(swimStyle);
            swimStyle.getPersons().remove(this);
        }
    }
    public List<Fee> getFees() {
        return fees;
    }
    
    public void addFee(Fee fee) {
        this.fees.add(fee);
        if (fee != null) {
            fee.setPerson(this);
        }
    }

    public void setFees(List<Fee> fees) {
        this.fees = fees;
    }
    
    
    public Long getP_id() {
        return p_id;
    }

    public void setP_id(Long p_id) {
        this.p_id = p_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBorn() {
        return born;
    }

    public void setBorn(int born) {
        this.born = born;
    }

    public List<SwimStyle> getStyles() {
        return styles;
    }

    public void setStyles(List<SwimStyle> styles) {
        this.styles = styles;
    }

    


}
