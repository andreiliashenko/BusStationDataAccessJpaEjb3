package com.anli.busstation.dal.jpa.entities.maintenance;

import com.anli.busstation.dal.interfaces.entities.BSEntity;
import com.anli.busstation.dal.interfaces.entities.maintenance.BusRefuelling;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity(name = "BusRefuelling")
@Table(name = "bus_refuellings")
@PrimaryKeyJoinColumn(name = "assignment_id", referencedColumnName = "assignment_id")
public class BusRefuellingImpl extends BusServiceImpl implements BusRefuelling {

    @Column(name = "volume")
    protected Integer volume;

    @Override
    public Integer getVolume() {
        return volume;
    }

    @Override
    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    @Override
    public boolean deepEquals(BSEntity comparee) {
        if (!super.deepEquals(comparee)) {
            return false;
        }
        BusRefuellingImpl brComparee = (BusRefuellingImpl) comparee;
        return nullableEquals(this.volume, brComparee.volume);
    }

    @Override
    public String toString() {
        return super.toString() + " volume = " + volume;
    }
}
