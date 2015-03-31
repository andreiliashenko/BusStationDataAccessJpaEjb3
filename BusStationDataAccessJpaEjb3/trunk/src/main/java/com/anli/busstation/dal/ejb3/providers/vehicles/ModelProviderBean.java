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
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
@Remote(ModelProvider.class)
@TransactionAttribute(REQUIRED)
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
    public List<Model> findBySeatsNumberRange(Integer seatsNumberLeft, boolean strictLeft,
            Integer seatsNumberRight, boolean strictRight) {
        return findByRange("seatsNumber", seatsNumberLeft, strictLeft, seatsNumberRight, strictRight);
    }

    @Override
    public List<Model> findByTankVolumeRange(Integer tankVolumeLeft, boolean strictLeft,
            Integer tankVolumeRight, boolean strictRight) {
        return findByRange("tankVolume", tankVolumeLeft, strictLeft, tankVolumeRight, strictRight);
    }

    @Override
    public List<Model> findByGasLabel(GasLabel gasLabel) {
        return findByEquals("gasLabel", gasLabel);
    }

    @Override
    public List<Model> findByAnyGasLabel(Collection<GasLabel> gasLabels) {
        return findByAny("gasLabel", gasLabels);
    }

    @Override
    public List<Model> findByGasRateRange(BigDecimal gasRateLeft, boolean strictLeft,
            BigDecimal gasRateRight, boolean strictRight) {
        return findByRange("gasRate", gasRateLeft, strictLeft, gasRateRight, strictRight);
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
    public List<BigInteger> collectIdsBySeatsNumberRange(Integer seatsNumberLeft, boolean strictLeft,
            Integer seatsNumberRight, boolean strictRight) {
        return collectIdsByRange("seatsNumber", seatsNumberLeft, strictLeft, seatsNumberRight, strictRight);
    }

    @Override
    public List<BigInteger> collectIdsByGasLabel(GasLabel gasLabel) {
        return collectIdsByEquals("gasLabel", gasLabel);
    }

    @Override
    public List<BigInteger> collectIdsByAnyGasLabel(Collection<GasLabel> gasLabels) {
        return collectIdsByAny("gasLabel", gasLabels);
    }

    @Override
    public List<BigInteger> collectIdsByGasRateRange(BigDecimal gasRateLeft, boolean strictLeft,
            BigDecimal gasRateRight, boolean strictRight) {
        return collectIdsByRange("gasRate", gasRateLeft, strictLeft, gasRateRight, strictRight);
    }

    @Override
    public List<BigInteger> collectIdsByTankVolumeRange(Integer tankVolumeLeft, boolean strictLeft,
            Integer tankVolumeRight, boolean strictRight) {
        return collectIdsByRange("tankVolume", tankVolumeLeft, strictLeft, tankVolumeRight, strictRight);
    }
}
