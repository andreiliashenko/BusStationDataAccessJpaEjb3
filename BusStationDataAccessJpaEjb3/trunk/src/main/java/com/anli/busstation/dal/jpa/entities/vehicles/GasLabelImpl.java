package com.anli.busstation.dal.jpa.entities.vehicles;

import com.anli.busstation.dal.jpa.entities.BSEntityImpl;
import com.anli.busstation.dal.interfaces.entities.BSEntity;
import com.anli.busstation.dal.interfaces.entities.vehicles.GasLabel;
import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "GasLabel")
@Table(name = "gas_labels")
@AttributeOverride(name = "id", column = @Column(name = "label_id"))
public class GasLabelImpl extends BSEntityImpl implements GasLabel {

    @Column(name = "name")
    protected String name;
    @Column(name = "price")
    protected BigDecimal price;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean deepEquals(BSEntity comparee) {
        if (!this.equals(comparee)) {
            return false;
        }
        GasLabelImpl glComparee = (GasLabelImpl) comparee;
        return nullableEquals(this.name, glComparee.name)
                && nullableEquals(this.price, glComparee.price);
    }

    @Override
    public String toString() {
        return super.toString() + " name = " + name
                + " price = " + price;
    }
}
