package com.anli.busstation.dal.jpa.entities.maintenance;

import com.anli.busstation.dal.jpa.entities.vehicles.BusImpl;
import com.anli.busstation.dal.interfaces.entities.BSEntity;
import com.anli.busstation.dal.interfaces.entities.vehicles.Bus;
import com.anli.busstation.dal.interfaces.entities.maintenance.BusService;
import com.anli.busstation.dal.jpa.extractors.BusServiceExtractor;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.ClassExtractor;

import static javax.persistence.InheritanceType.JOINED;

@Entity(name = "BusService")
@Table(name = "bus_services")
@Inheritance(strategy = JOINED)
@ClassExtractor(BusServiceExtractor.class)
@PrimaryKeyJoinColumn(name = "assignment_id", referencedColumnName = "assignment_id")
public abstract class BusServiceImpl extends TechnicalAssignmentImpl implements BusService {

    @OneToOne
    @JoinColumn(name = "bus", referencedColumnName = "bus_id")
    protected BusImpl bus;

    @Override
    public Bus getBus() {
        return bus;
    }

    @Override
    public void setBus(Bus bus) {
        this.bus = (BusImpl) bus;
    }

    @Override
    public boolean deepEquals(BSEntity comparee) {
        if (!super.deepEquals(comparee)) {
            return false;
        }
        BusServiceImpl bsComparee = (BusServiceImpl) comparee;
        return nullableDeepEquals(this.bus, bsComparee.bus);
    }

    @Override
    public String toString() {
        return super.toString() + " bus = {" + bus + "}";
    }
}
