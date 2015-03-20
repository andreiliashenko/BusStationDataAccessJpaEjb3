package com.anli.busstation.dal.ejb3.providers.vehicles;

import com.anli.busstation.dal.ejb3.providers.AbstractBSProviderBean;
import com.anli.busstation.dal.interfaces.entities.vehicles.TechnicalState;
import com.anli.busstation.dal.interfaces.providers.vehicles.TechnicalStateProvider;
import com.anli.busstation.dal.jpa.entities.vehicles.TechnicalStateImpl;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class TechnicalStateProviderBean
        extends AbstractBSProviderBean<TechnicalState, TechnicalStateImpl>
        implements TechnicalStateProvider {

    @Override
    protected TechnicalStateImpl getEntityInstance() {
        return new TechnicalStateImpl();
    }

    @Override
    protected Class<TechnicalStateImpl> getEntityClass() {
        return TechnicalStateImpl.class;
    }

    @Override
    public List<TechnicalState> findByDescriptionRegexp(String descrRegexp) {
        return findByRegexp("description", descrRegexp);
    }

    @Override
    public List<TechnicalState> findByDifficultyLevel(Integer diffLevel) {
        return findByEquals("difficultyLevel", diffLevel);
    }

    @Override
    public List<TechnicalState> findByDifficultyLevelRange(Integer dLLeft, boolean strictLeft,
            Integer dLRight, boolean strictRight) {
        return findByRange("difficultyLevel", dLLeft, strictLeft, dLRight, strictRight);
    }

    @Override
    public List<BigInteger> collectIdsByDescriptionRegexp(String descrRegexp) {
        return collectIdsByRegexp("description", descrRegexp);
    }

    @Override
    public List<BigInteger> collectIdsByDifficultyLevel(Integer diffLevel) {
        return collectIdsByEquals("difficultyLevel", diffLevel);
    }

    @Override
    public List<BigInteger> collectIdsByDifficultyLevelRange(Integer dLLeft, boolean strictLeft,
            Integer dLRight, boolean strictRight) {
        return collectIdsByRange("difficultyLevel", dLLeft, strictLeft, dLRight, strictRight);
    }
}
