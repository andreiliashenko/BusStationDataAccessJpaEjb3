package com.anli.busstation.dal.ejb3.providers.vehicles;

import com.anli.busstation.dal.ejb3.providers.AbstractBSProviderBean;
import com.anli.busstation.dal.interfaces.entities.vehicles.Bus;
import com.anli.busstation.dal.interfaces.entities.vehicles.Model;
import com.anli.busstation.dal.interfaces.entities.vehicles.TechnicalState;
import com.anli.busstation.dal.interfaces.providers.vehicles.BusProvider;
import com.anli.busstation.dal.jpa.entities.vehicles.BusImpl;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BusProviderBean extends AbstractBSProviderBean<Bus, BusImpl>
        implements BusProvider {

    @Override
    protected BusImpl getEntityInstance() {
        return new BusImpl();
    }

    @Override
    protected Class<BusImpl> getEntityClass() {
        return BusImpl.class;
    }

    @Override
    public List<Bus> findByModel(Model model) {
        return findByEquals("model", model);
    }

    @Override
    public List<Bus> findByAnyModel(Collection<Model> models) {
        return findByAny("model", models);
    }

    @Override
    public List<Bus> findByState(TechnicalState state) {
        return findByEquals("state", state);
    }

    @Override
    public List<Bus> findByAnyState(Collection<TechnicalState> states) {
        return findByAny("state", states);
    }

    @Override
    public List<Bus> findByPlate(String plate) {
        return findByEquals("plate", plate);
    }

    @Override
    public List<BigInteger> collectIdsByModel(Model model) {
        return collectIdsByEquals("model", model);
    }

    @Override
    public List<BigInteger> collectIdsByAnyModel(Collection<Model> models) {
        return collectIdsByAny("model", models);
    }

    @Override
    public List<BigInteger> collectIdsByState(TechnicalState state) {
        return collectIdsByEquals("state", state);
    }

    @Override
    public List<BigInteger> collectIdsByAnyState(Collection<TechnicalState> states) {
        return collectIdsByAny("state", states);
    }

    @Override
    public List<BigInteger> collectIdsByPlate(String plate) {
        return collectIdsByEquals("plate", plate);
    }
}
