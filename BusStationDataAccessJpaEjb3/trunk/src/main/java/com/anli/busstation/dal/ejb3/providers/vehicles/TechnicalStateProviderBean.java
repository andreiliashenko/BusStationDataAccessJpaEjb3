package com.anli.busstation.dal.ejb3.providers.vehicles;

import com.anli.busstation.dal.ejb3.providers.AbstractBSProviderBean;
import com.anli.busstation.dal.interfaces.entities.vehicles.TechnicalState;
import com.anli.busstation.dal.interfaces.providers.vehicles.TechnicalStateProvider;
import com.anli.busstation.dal.jpa.entities.vehicles.TechnicalStateImpl;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
@TransactionAttribute(REQUIRED)
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
    public List<TechnicalState> findByDescriptionRegexp(String descriptionRegexp) {
        return findByRegexp("description", descriptionRegexp);
    }

    @Override
    public List<TechnicalState> findByDifficultyLevel(Integer difficultyLevel) {
        return findByEquals("difficultyLevel", difficultyLevel);
    }

    @Override
    public List<TechnicalState> findByDifficultyLevelRange(Integer difficultyLevelLeft, boolean strictLeft,
            Integer difficultyLevelRight, boolean strictRight) {
        return findByRange("difficultyLevel", difficultyLevelLeft, strictLeft, difficultyLevelRight,
                strictRight);
    }

    @Override
    public List<BigInteger> collectIdsByDescriptionRegexp(String descriptionRegexp) {
        return collectIdsByRegexp("description", descriptionRegexp);
    }

    @Override
    public List<BigInteger> collectIdsByDifficultyLevel(Integer difficultyLevel) {
        return collectIdsByEquals("difficultyLevel", difficultyLevel);
    }

    @Override
    public List<BigInteger> collectIdsByDifficultyLevelRange(Integer difficultyLevelLeft, boolean strictLeft,
            Integer difficultyLevelRight, boolean strictRight) {
        return collectIdsByRange("difficultyLevel", difficultyLevelLeft, strictLeft, difficultyLevelRight,
                strictRight);
    }
}
