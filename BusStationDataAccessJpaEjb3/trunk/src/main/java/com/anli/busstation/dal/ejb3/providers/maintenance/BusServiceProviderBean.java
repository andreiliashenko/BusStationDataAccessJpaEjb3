package com.anli.busstation.dal.ejb3.providers.maintenance;

import com.anli.busstation.dal.interfaces.entities.maintenance.BusService;
import com.anli.busstation.dal.interfaces.providers.maintenance.BusServiceProvider;
import com.anli.busstation.dal.jpa.entities.maintenance.BusServiceImpl;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
@Remote(BusServiceProvider.class)
@TransactionAttribute(REQUIRED)
public class BusServiceProviderBean extends AbstractBusServiceProviderBean<BusService, BusServiceImpl>
        implements BusServiceProvider {

    @Override
    protected BusServiceImpl getEntityInstance() {
        return null;
    }

    @Override
    protected Class<BusServiceImpl> getEntityClass() {
        return BusServiceImpl.class;
    }
}
