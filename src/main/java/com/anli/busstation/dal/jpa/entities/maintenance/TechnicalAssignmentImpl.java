package com.anli.busstation.dal.jpa.entities.maintenance;

import com.anli.busstation.dal.jpa.entities.BSEntityImpl;
import com.anli.busstation.dal.jpa.entities.staff.MechanicImpl;
import com.anli.busstation.dal.interfaces.entities.BSEntity;
import com.anli.busstation.dal.interfaces.entities.staff.Mechanic;
import com.anli.busstation.dal.interfaces.entities.maintenance.TechnicalAssignment;
import com.anli.busstation.dal.jpa.converters.DateTimeConverter;
import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.joda.time.DateTime;

@Entity(name = "TechnicalAssignment")
@Table(name = "technical_assignments")
@Inheritance(strategy = InheritanceType.JOINED)
@AttributeOverride(name = "id", column = @Column(name = "assignment_id"))
public abstract class TechnicalAssignmentImpl extends BSEntityImpl implements TechnicalAssignment {

    @OneToOne
    @JoinColumn(name = "mechanic", referencedColumnName = "employee_id")
    protected MechanicImpl mechanic;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "begin_time")
    @Convert(converter = DateTimeConverter.class)
    protected DateTime beginTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time")
    @Convert(converter = DateTimeConverter.class)
    protected DateTime endTime;
    @Column(name = "service_cost")
    protected BigDecimal serviceCost;

    @Override
    public Mechanic getMechanic() {
        return mechanic;
    }

    @Override
    public void setMechanic(Mechanic mechanic) {
        this.mechanic = (MechanicImpl) mechanic;
    }

    @Override
    public DateTime getBeginTime() {
        return beginTime;
    }

    @Override
    public void setBeginTime(DateTime beginTime) {
        this.beginTime = beginTime;
    }

    @Override
    public DateTime getEndTime() {
        return endTime;
    }

    @Override
    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public BigDecimal getServiceCost() {
        return serviceCost;
    }

    @Override
    public void setServiceCost(BigDecimal serviceCost) {
        this.serviceCost = serviceCost;
    }

    @Override
    public boolean deepEquals(BSEntity comparee) {
        if (!this.equals(comparee)) {
            return false;
        }
        TechnicalAssignmentImpl taComparee = (TechnicalAssignmentImpl) comparee;
        return nullableDeepEquals(this.mechanic, taComparee.mechanic)
                && nullableEquals(this.beginTime, taComparee.beginTime)
                && nullableEquals(this.endTime, taComparee.endTime)
                && nullableEquals(this.serviceCost, taComparee.serviceCost);
    }

    @Override
    public String toString() {
        return super.toString() + " mechanic = {" + mechanic + "} beginTime = "
                + beginTime + " endTime = " + endTime + " serviceCost = " + serviceCost;
    }
}
