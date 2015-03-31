package com.anli.busstation.dal.ejb3.providers.staff;

import com.anli.busstation.dal.interfaces.entities.staff.Mechanic;
import com.anli.busstation.dal.interfaces.entities.staff.MechanicSkill;
import com.anli.busstation.dal.interfaces.providers.staff.MechanicProvider;
import com.anli.busstation.dal.jpa.entities.staff.MechanicImpl;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
@Remote(MechanicProvider.class)
@TransactionAttribute(REQUIRED)
public class MechanicProviderBean extends AbstractEmployeeProviderBean<Mechanic, MechanicImpl>
        implements MechanicProvider {

    @Override
    protected MechanicImpl getEntityInstance() {
        return new MechanicImpl();
    }

    @Override
    protected Class<MechanicImpl> getEntityClass() {
        return MechanicImpl.class;
    }

    @Override
    public List<Mechanic> findBySkill(MechanicSkill skill) {
        return findByEquals("skill", skill);
    }

    @Override
    public List<Mechanic> findByAnySkill(Collection<MechanicSkill> skills) {
        return findByAny("skill", skills);
    }

    @Override
    public List<BigInteger> collectIdsBySkill(MechanicSkill skill) {
        return collectIdsByEquals("skill", skill);
    }

    @Override
    public List<BigInteger> collectIdsByAnySkill(Collection<MechanicSkill> skills) {
        return collectIdsByAny("skill", skills);
    }
}
