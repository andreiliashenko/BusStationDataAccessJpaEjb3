package com.anli.busstation.dal.ejb3.providers.maintenance;

import com.anli.busstation.dal.interfaces.entities.maintenance.BusRepairment;
import com.anli.busstation.dal.interfaces.providers.maintenance.BusRepairmentProvider;
import com.anli.busstation.dal.jpa.entities.maintenance.BusRepairmentImpl;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
@TransactionAttribute(REQUIRED)
public class BusRepairmentProviderBean
        extends AbstractBusServiceProviderBean<BusRepairment, BusRepairmentImpl>
        implements BusRepairmentProvider {

    @Override
    protected BusRepairmentImpl getEntityInstance() {
        return new BusRepairmentImpl();
    }

    @Override
    protected Class<BusRepairmentImpl> getEntityClass() {
        return BusRepairmentImpl.class;
    }

    @Override
    public List<BusRepairment> findByExpendablesPriceRange(BigDecimal expendablesPriceLeft,
            boolean strictLeft, BigDecimal expendablesPriceRight, boolean strictRight) {
        return findByRange("expendablesPrice", expendablesPriceLeft, strictLeft,
                expendablesPriceRight, strictRight);
    }

    @Override
    public List<BigInteger> collectIdsByExpendablesPriceRange(BigDecimal expendablesPriceLeft,
            boolean strictLeft, BigDecimal expendablesPriceRight, boolean strictRight) {
        return collectIdsByRange("expendablesPrice", expendablesPriceLeft, strictLeft,
                expendablesPriceRight, strictRight);
    }
}
