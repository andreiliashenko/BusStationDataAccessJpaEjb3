package com.anli.busstation.dal.ejb3.providers.maintenance;

import com.anli.busstation.dal.interfaces.entities.maintenance.BusService;
import com.anli.busstation.dal.interfaces.entities.vehicles.Bus;
import com.anli.busstation.dal.interfaces.providers.maintenance.GenericBusServiceProvider;
import com.anli.busstation.dal.jpa.entities.maintenance.BusServiceImpl;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import javax.ejb.TransactionAttribute;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@TransactionAttribute(REQUIRED)
public abstract class AbstractBusServiceProviderBean<I extends BusService, E extends BusServiceImpl>
        extends AbstractTechnicalAssignmentProviderBean<I, E> implements GenericBusServiceProvider<I> {

    @Override
    public List<I> findByBus(Bus bus) {
        return findByEquals("bus", bus);
    }

    @Override
    public List<I> findByAnyBus(Collection<Bus> buses) {
        return findByAny("bus", buses);
    }

    @Override
    public List<BigInteger> collectIdsByBus(Bus bus) {
        return collectIdsByEquals("bus", bus);
    }

    @Override
    public List<BigInteger> collectIdsByAnyBus(Collection<Bus> buses) {
        return collectIdsByAny("bus", buses);
    }
}
