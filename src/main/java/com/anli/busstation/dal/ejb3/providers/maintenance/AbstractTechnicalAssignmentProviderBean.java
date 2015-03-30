package com.anli.busstation.dal.ejb3.providers.maintenance;

import com.anli.busstation.dal.ejb3.providers.AbstractBSProviderBean;
import com.anli.busstation.dal.interfaces.entities.maintenance.TechnicalAssignment;
import com.anli.busstation.dal.interfaces.entities.staff.Mechanic;
import com.anli.busstation.dal.interfaces.providers.maintenance.GenericTechnicalAssignmentProvider;
import com.anli.busstation.dal.jpa.entities.maintenance.TechnicalAssignmentImpl;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import javax.ejb.TransactionAttribute;
import org.joda.time.DateTime;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@TransactionAttribute(REQUIRED)
public abstract class AbstractTechnicalAssignmentProviderBean<I extends TechnicalAssignment, E extends TechnicalAssignmentImpl>
        extends AbstractBSProviderBean<I, E> implements GenericTechnicalAssignmentProvider<I> {

    @Override
    public List<I> findByMechanic(Mechanic mechanic) {
        return findByEquals("mechanic", mechanic);
    }

    @Override
    public List<I> findByAnyMechanic(Collection<Mechanic> mechanics) {
        return findByAny("mechanic", mechanics);
    }

    @Override
    public List<I> findByBeginTimeRange(DateTime beginTimeLeft, boolean strictLeft, DateTime beginTimeRight,
            boolean strictRight) {
        return findByRange("beginTime", beginTimeLeft, strictLeft, beginTimeRight, strictRight);
    }

    @Override
    public List<I> findByEndTimeRange(DateTime endTimeLeft, boolean strictLeft, DateTime endTimeRight,
            boolean strictRight) {
        return findByRange("endTime", endTimeLeft, strictLeft, endTimeRight, strictRight);
    }

    @Override
    public List<I> findByServiceCostRange(BigDecimal serviceCostLeft, boolean strictLeft,
            BigDecimal serviceCostRight, boolean strictRight) {
        return findByRange("serviceCost", serviceCostLeft, strictRight, serviceCostRight, strictRight);
    }

    @Override
    public List<BigInteger> collectIdsByMechanic(Mechanic mechanic) {
        return collectIdsByEquals("mechanic", mechanic);
    }

    @Override
    public List<BigInteger> collectIdsByAnyMechanic(Collection<Mechanic> mechanics) {
        return collectIdsByAny("mechanic", mechanics);
    }

    @Override
    public List<BigInteger> collectIdsByBeginTimeRange(DateTime beginTimeLeft, boolean strictLeft,
            DateTime beginTimeRight, boolean strictRight) {
        return collectIdsByRange("beginTime", beginTimeLeft, strictLeft, beginTimeRight, strictRight);
    }

    @Override
    public List<BigInteger> collectIdsByEndTimeRange(DateTime endTimeLeft, boolean strictLeft,
            DateTime endTimeRight, boolean strictRight) {
        return collectIdsByRange("endTime", endTimeLeft, strictLeft, endTimeRight, strictRight);
    }

    @Override
    public List<BigInteger> collectIdsByServiceCostRange(BigDecimal serviceCostLeft, boolean strictLeft,
            BigDecimal serviceCostRight, boolean strictRight) {
        return collectIdsByRange("serviceCost", serviceCostLeft, strictLeft, serviceCostRight, strictRight);
    }
}
