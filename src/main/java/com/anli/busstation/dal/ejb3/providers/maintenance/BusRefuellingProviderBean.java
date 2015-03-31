package com.anli.busstation.dal.ejb3.providers.maintenance;

import com.anli.busstation.dal.interfaces.entities.maintenance.BusRefuelling;
import com.anli.busstation.dal.interfaces.providers.maintenance.BusRefuellingProvider;
import com.anli.busstation.dal.jpa.entities.maintenance.BusRefuellingImpl;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
@Remote(BusRefuellingProvider.class)
@TransactionAttribute(REQUIRED)
public class BusRefuellingProviderBean
        extends AbstractBusServiceProviderBean<BusRefuelling, BusRefuellingImpl>
        implements BusRefuellingProvider {

    @Override
    protected BusRefuellingImpl getEntityInstance() {
        return new BusRefuellingImpl();
    }

    @Override
    protected Class<BusRefuellingImpl> getEntityClass() {
        return BusRefuellingImpl.class;
    }

    @Override
    public List<BusRefuelling> findByVolumeRange(Integer volumeLeft, boolean strictLeft, Integer volumeRight,
            boolean strictRight) {
        return findByRange("volume", volumeLeft, strictLeft, volumeRight, strictRight);
    }

    @Override
    public List<BigInteger> collectIdsByVolumeRange(Integer volumeLeft, boolean strictLeft,
            Integer volumeRight, boolean strictRight) {
        return collectIdsByRange("volume", volumeLeft, strictLeft, volumeRight, strictRight);
    }
}
