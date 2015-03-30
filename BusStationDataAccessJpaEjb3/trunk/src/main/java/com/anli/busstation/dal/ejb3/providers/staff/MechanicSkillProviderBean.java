package com.anli.busstation.dal.ejb3.providers.staff;

import com.anli.busstation.dal.ejb3.providers.AbstractBSProviderBean;
import com.anli.busstation.dal.interfaces.entities.staff.MechanicSkill;
import com.anli.busstation.dal.interfaces.providers.staff.MechanicSkillProvider;
import com.anli.busstation.dal.jpa.entities.staff.MechanicSkillImpl;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
@TransactionAttribute(REQUIRED)
public class MechanicSkillProviderBean extends AbstractBSProviderBean<MechanicSkill, MechanicSkillImpl>
        implements MechanicSkillProvider {

    @Override
    protected MechanicSkillImpl getEntityInstance() {
        return new MechanicSkillImpl();
    }

    @Override
    protected Class<MechanicSkillImpl> getEntityClass() {
        return MechanicSkillImpl.class;
    }

    @Override
    public List<MechanicSkill> findByName(String name) {
        return findByEquals("name", name);
    }

    @Override
    public List<MechanicSkill> findByNameRegexp(String nameRegexp) {
        return findByRegexp("name", nameRegexp);
    }

    @Override
    public List<MechanicSkill> findByMaxDiffLevelRange(Integer maxDiffLevelLeft, boolean strictLeft,
            Integer maxDiffLevelRight, boolean strictRight) {
        return findByRange("maxDiffLevel", maxDiffLevelLeft, strictLeft, maxDiffLevelRight, strictRight);
    }

    @Override
    public List<BigInteger> collectIdsByName(String name) {
        return collectIdsByEquals("name", name);
    }

    @Override
    public List<BigInteger> collectIdsByNameRegexp(String nameRegexp) {
        return collectIdsByRegexp("name", nameRegexp);
    }

    @Override
    public List<BigInteger> collectIdsByMaxDiffLevelRange(Integer maxDiffLevelLeft, boolean strictLeft,
            Integer maxDiffLevelRight, boolean strictRight) {
        return collectIdsByRange("maxDiffLevel", maxDiffLevelLeft, strictLeft, maxDiffLevelRight,
                strictRight);
    }
}
