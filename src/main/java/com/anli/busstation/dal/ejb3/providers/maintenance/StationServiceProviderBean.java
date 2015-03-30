package com.anli.busstation.dal.ejb3.providers.maintenance;

import com.anli.busstation.dal.interfaces.entities.maintenance.StationService;
import com.anli.busstation.dal.interfaces.providers.maintenance.StationServiceProvider;
import com.anli.busstation.dal.jpa.entities.maintenance.StationServiceImpl;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
@TransactionAttribute(REQUIRED)
public class StationServiceProviderBean
        extends AbstractTechnicalAssignmentProviderBean<StationService, StationServiceImpl>
        implements StationServiceProvider {

    @Override
    protected StationServiceImpl getEntityInstance() {
        return new StationServiceImpl();
    }

    @Override
    protected Class<StationServiceImpl> getEntityClass() {
        return StationServiceImpl.class;
    }

    @Override
    public List<StationService> findByDescriptionRegexp(String descriptionRegexp) {
        return findByRegexp("description", descriptionRegexp);
    }

    @Override
    public List<BigInteger> collectIdsByDescriptionRegexp(String descriptionRegexp) {
        return collectIdsByRegexp("description", descriptionRegexp);
    }
}
