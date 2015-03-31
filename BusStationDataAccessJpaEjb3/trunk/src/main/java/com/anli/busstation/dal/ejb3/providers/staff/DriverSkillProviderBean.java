package com.anli.busstation.dal.ejb3.providers.staff;

import com.anli.busstation.dal.ejb3.providers.AbstractBSProviderBean;
import com.anli.busstation.dal.interfaces.entities.staff.DriverSkill;
import com.anli.busstation.dal.interfaces.providers.staff.DriverSkillProvider;
import com.anli.busstation.dal.jpa.entities.staff.DriverSkillImpl;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
@Remote(DriverSkillProvider.class)
@TransactionAttribute(REQUIRED)
public class DriverSkillProviderBean extends AbstractBSProviderBean<DriverSkill, DriverSkillImpl>
        implements DriverSkillProvider {

    @Override
    protected DriverSkillImpl getEntityInstance() {
        return new DriverSkillImpl();
    }

    @Override
    protected Class<DriverSkillImpl> getEntityClass() {
        return DriverSkillImpl.class;
    }

    @Override
    public List<DriverSkill> findByName(String name) {
        return findByEquals("name", name);
    }

    @Override
    public List<DriverSkill> findByNameRegexp(String nameRegexp) {
        return findByRegexp("name", nameRegexp);
    }

    @Override
    public List<DriverSkill> findByMaxRideLengthRange(Integer maxRideLengthLeft, boolean strictLeft,
            Integer maxRideLengthRight, boolean strictRight) {
        return findByRange("maxRideLength", maxRideLengthLeft, strictLeft, maxRideLengthRight, strictRight);
    }

    @Override
    public List<DriverSkill> findByMaxPassengersRange(Integer maxPassengersLeft, boolean strictLeft,
            Integer maxPassengersRight, boolean strictRight) {
        return findByRange("maxPassengers", maxPassengersLeft, strictLeft, maxPassengersRight, strictRight);
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
    public List<BigInteger> collectIdsByMaxRideLengthRange(Integer maxRideLengthLeft, boolean strictLeft,
            Integer maxRideLengthRight, boolean strictRight) {
        return collectIdsByRange("maxRideLength", maxRideLengthLeft, strictLeft, maxRideLengthRight,
                strictRight);
    }

    @Override
    public List<BigInteger> collectIdsByMaxPassengersRange(Integer maxPassengersLeft, boolean strictLeft,
            Integer maxPassengersRight, boolean strictRight) {
        return collectIdsByRange("maxPassengers", maxPassengersLeft, strictLeft, maxPassengersRight,
                strictRight);
    }
}
