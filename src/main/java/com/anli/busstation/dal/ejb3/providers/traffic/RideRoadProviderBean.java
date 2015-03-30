package com.anli.busstation.dal.ejb3.providers.traffic;

import com.anli.busstation.dal.ejb3.providers.AbstractBSProviderBean;
import com.anli.busstation.dal.interfaces.entities.geography.Road;
import com.anli.busstation.dal.interfaces.entities.staff.Driver;
import com.anli.busstation.dal.interfaces.entities.traffic.RideRoad;
import com.anli.busstation.dal.interfaces.providers.traffic.RideRoadProvider;
import com.anli.busstation.dal.jpa.entities.traffic.RideRoadImpl;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
@TransactionAttribute(REQUIRED)
public class RideRoadProviderBean extends AbstractBSProviderBean<RideRoad, RideRoadImpl>
        implements RideRoadProvider {

    @Override
    protected RideRoadImpl getEntityInstance() {
        return new RideRoadImpl();
    }

    @Override
    protected Class<RideRoadImpl> getEntityClass() {
        return RideRoadImpl.class;
    }

    @Override
    public List<RideRoad> findByRoad(Road road) {
        return findByEquals("road", road);
    }

    @Override
    public List<RideRoad> findByAnyRoad(Collection<Road> roads) {
        return findByAny("road", roads);
    }

    @Override
    public List<RideRoad> findByDriver(Driver driver) {
        return findByEquals("driver", driver);
    }

    @Override
    public List<RideRoad> findByAnyDriver(Collection<Driver> drivers) {
        return findByAny("driver", drivers);
    }

    @Override
    public List<BigInteger> collectIdsByRoad(Road road) {
        return collectIdsByEquals("road", road);
    }

    @Override
    public List<BigInteger> collectIdsByAnyRoad(Collection<Road> roads) {
        return collectIdsByAny("road", roads);
    }

    @Override
    public List<BigInteger> collectIdsByDriver(Driver driver) {
        return collectIdsByEquals("driver", driver);
    }

    @Override
    public List<BigInteger> collectIdsByAnyDriver(Collection<Driver> drivers) {
        return collectIdsByAny("driver", drivers);
    }
}
