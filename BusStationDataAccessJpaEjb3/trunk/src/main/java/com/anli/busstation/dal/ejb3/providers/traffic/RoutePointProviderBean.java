package com.anli.busstation.dal.ejb3.providers.traffic;

import com.anli.busstation.dal.ejb3.providers.AbstractBSProviderBean;
import com.anli.busstation.dal.interfaces.entities.geography.Station;
import com.anli.busstation.dal.interfaces.entities.traffic.RoutePoint;
import com.anli.busstation.dal.interfaces.providers.traffic.RoutePointProvider;
import com.anli.busstation.dal.jpa.entities.traffic.RoutePointImpl;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
@TransactionAttribute(REQUIRED)
public class RoutePointProviderBean extends AbstractBSProviderBean<RoutePoint, RoutePointImpl>
        implements RoutePointProvider {

    @Override
    protected RoutePointImpl getEntityInstance() {
        return new RoutePointImpl();
    }

    @Override
    protected Class<RoutePointImpl> getEntityClass() {
        return RoutePointImpl.class;
    }

    @Override
    public List<RoutePoint> findByStation(Station station) {
        return findByEquals("station", station);
    }

    @Override
    public List<RoutePoint> findByAnyStation(Collection<Station> stations) {
        return findByAny("station", stations);
    }

    @Override
    public List<BigInteger> collectIdsByStation(Station station) {
        return collectIdsByEquals("station", station);
    }

    @Override
    public List<BigInteger> collectIdsByAnyStation(Collection<Station> stations) {
        return collectIdsByAny("station", stations);
    }
}
