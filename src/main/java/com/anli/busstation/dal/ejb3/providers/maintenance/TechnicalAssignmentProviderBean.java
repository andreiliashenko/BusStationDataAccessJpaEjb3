package com.anli.busstation.dal.ejb3.providers.maintenance;

import com.anli.busstation.dal.interfaces.entities.maintenance.TechnicalAssignment;
import com.anli.busstation.dal.interfaces.providers.maintenance.TechnicalAssignmentProvider;
import com.anli.busstation.dal.jpa.entities.maintenance.TechnicalAssignmentImpl;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
@Remote(TechnicalAssignmentProvider.class)
@TransactionAttribute(REQUIRED)
public class TechnicalAssignmentProviderBean
        extends AbstractTechnicalAssignmentProviderBean<TechnicalAssignment, TechnicalAssignmentImpl>
        implements TechnicalAssignmentProvider {

    @Override
    protected TechnicalAssignmentImpl getEntityInstance() {
        return null;
    }

    @Override
    protected Class<TechnicalAssignmentImpl> getEntityClass() {
        return TechnicalAssignmentImpl.class;
    }
}
