package com.anli.busstation.dal.ejb3.providers.staff;

import com.anli.busstation.dal.interfaces.entities.staff.Driver;
import com.anli.busstation.dal.interfaces.entities.staff.DriverSkill;
import com.anli.busstation.dal.interfaces.providers.staff.DriverProvider;
import com.anli.busstation.dal.jpa.entities.staff.DriverImpl;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
@Remote(DriverProvider.class)
@TransactionAttribute(REQUIRED)
public class DriverProviderBean extends AbstractEmployeeProviderBean<Driver, DriverImpl>
        implements DriverProvider {

    @Override
    protected DriverImpl getEntityInstance() {
        return new DriverImpl();
    }

    @Override
    protected Class<DriverImpl> getEntityClass() {
        return DriverImpl.class;
    }

    @Override
    public List<Driver> findBySkill(DriverSkill skill) {
        return findByEquals("skill", skill);
    }

    @Override
    public List<Driver> findByAnySkill(Collection<DriverSkill> skills) {
        return findByAny("skill", skills);
    }

    @Override
    public List<BigInteger> collectIdsBySkill(DriverSkill skill) {
        return collectIdsByEquals("skill", skill);
    }

    @Override
    public List<BigInteger> collectIdsByAnySkill(Collection<DriverSkill> skills) {
        return collectIdsByAny("skill", skills);
    }
}
