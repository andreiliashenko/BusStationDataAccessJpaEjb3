package com.anli.busstation.dal.ejb3.providers.vehicles;

import com.anli.busstation.dal.ejb3.providers.AbstractBSProviderBean;
import com.anli.busstation.dal.interfaces.entities.vehicles.GasLabel;
import com.anli.busstation.dal.interfaces.providers.vehicles.GasLabelProvider;
import com.anli.busstation.dal.jpa.entities.vehicles.GasLabelImpl;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class GasLabelProviderBean extends AbstractBSProviderBean<GasLabel, GasLabelImpl>
        implements GasLabelProvider {

    @Override
    protected GasLabelImpl getEntityInstance() {
        return new GasLabelImpl();
    }

    @Override
    protected Class<GasLabelImpl> getEntityClass() {
        return GasLabelImpl.class;
    }

    @Override
    public List<GasLabel> findByName(String name) {
        return findByEquals("name", name);
    }

    @Override
    public List<GasLabel> findByNameRegexp(String regexpName) {
        return findByRegexp("name", regexpName);
    }

    @Override
    public List<GasLabel> findByPriceRange(BigDecimal priceLeft, boolean leftStrict,
            BigDecimal priceRight, boolean rightStrict) {
        return findByRange("price", priceLeft, leftStrict, priceRight, rightStrict);
    }

    @Override
    public List<BigInteger> collectIdsByName(String name) {
        return collectIdsByEquals("name", name);
    }

    @Override
    public List<BigInteger> collectIdsByNameRegexp(String regexpName) {
        return collectIdsByRegexp("name", regexpName);
    }

    @Override
    public List<BigInteger> collectIdsByPriceRange(BigDecimal priceLeft, boolean leftStrict, BigDecimal priceRight, boolean rightStrict) {
        return collectIdsByRange("price", priceLeft, leftStrict, priceRight, rightStrict);
    }
}
