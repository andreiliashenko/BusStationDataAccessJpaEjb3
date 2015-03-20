package com.anli.busstation.dal.ejb3.providers.vehicles;

import com.anli.busstation.dal.ejb3.providers.AbstractBSProviderBean;
import com.anli.busstation.dal.interfaces.entities.vehicles.GasLabel;
import com.anli.busstation.dal.interfaces.entities.vehicles.Model;
import com.anli.busstation.dal.interfaces.providers.vehicles.ModelProvider;
import com.anli.busstation.dal.jpa.entities.vehicles.ModelImpl;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ModelProviderBean extends AbstractBSProviderBean<Model, ModelImpl>
        implements ModelProvider {

    @Override
    protected ModelImpl getEntityInstance() {
        return new ModelImpl();
    }

    @Override
    protected Class<ModelImpl> getEntityClass() {
        return ModelImpl.class;
    }

    @Override
    public List<Model> findByName(String name) {
        return findByEquals("name", name);
    }

    @Override
    public List<Model> findByNameRegexp(String nameRegexp) {
        return findByRegexp("name", nameRegexp);
    }

    @Override
    public List<Model> findBySeatsNumberRange(Integer seatsLeft, boolean strictLeft,
            Integer seatsRight, boolean strictRight) {
        return findByRange("seatsNumber", seatsLeft, strictLeft, seatsRight, strictRight);
    }

    @Override
    public List<Model> findByTankVolumeRange(Integer tvLeft, boolean strictLeft,
            Integer tvRight, boolean strictRight) {
        return findByRange("tankVolume", tvLeft, strictLeft, tvRight, strictRight);
    }

    @Override
    public List<Model> findByGasLabel(GasLabel label) {
        return findByEquals("gasLabel", label);
    }

    @Override
    public List<Model> findByAnyGasLabel(Collection<GasLabel> labelList) {
        return findByAny("gasLabel", labelList);
    }

    @Override
    public List<Model> findByGasRateRange(BigDecimal rateLeft, boolean strictLeft,
            BigDecimal rateRight, boolean strictRight) {
        return findByRange("gasRate", rateLeft, strictLeft, rateRight, strictRight);
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
    public List<BigInteger> collectIdsBySeatsNumberRange(Integer seatsLeft, boolean strictLeft,
            Integer seatsRight, boolean strictRight) {
        return collectIdsByRange("seatsNumber", seatsLeft, strictLeft, seatsRight, strictRight);
    }

    @Override
    public List<BigInteger> collectIdsByGasLabel(GasLabel label) {
        return collectIdsByEquals("gasLabel", label);
    }

    @Override
    public List<BigInteger> collectIdsByAnyGasLabel(Collection<GasLabel> labelList) {
        return collectIdsByAny("gasLabel", labelList);
    }

    @Override
    public List<BigInteger> collectIdsByGasRateRange(BigDecimal rateLeft, boolean strictLeft,
            BigDecimal rateRight, boolean strictRight) {
        return collectIdsByRange("gasRate", rateLeft, strictLeft, rateRight, strictRight);
    }

    @Override
    public List<BigInteger> collectIdsByTankVolumeRange(Integer tvLeft, boolean strictLeft,
            Integer tvRight, boolean strictRight) {
        return collectIdsByRange("tankVolume", tvLeft, strictLeft, tvRight, strictRight);
    }
}
