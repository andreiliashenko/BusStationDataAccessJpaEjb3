package com.anli.busstation.dal.jpa.entities.maintenance;

import com.anli.busstation.dal.interfaces.entities.BSEntity;
import com.anli.busstation.dal.interfaces.entities.maintenance.BusRepairment;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity(name = "BusRepairment")
@Table(name = "bus_repairments")
@PrimaryKeyJoinColumn(name = "assignment_id", referencedColumnName = "assignment_id")
public class BusRepairmentImpl extends BusServiceImpl implements BusRepairment {

    @Column(name = "expendables_price")
    protected BigDecimal expendablesPrice;

    @Override
    public BigDecimal getExpendablesPrice() {
        return expendablesPrice;
    }

    @Override
    public void setExpendablesPrice(BigDecimal expendablesPrice) {
        this.expendablesPrice = expendablesPrice;
    }

    @Override
    public boolean deepEquals(BSEntity comparee) {
        if (!super.deepEquals(comparee)) {
            return false;
        }
        BusRepairmentImpl brComparee = (BusRepairmentImpl) comparee;
        return nullableEquals(this.expendablesPrice, brComparee.expendablesPrice);
    }

    @Override
    public String toString() {
        return super.toString() + " expendablesPrice = " + expendablesPrice;
    }
}
