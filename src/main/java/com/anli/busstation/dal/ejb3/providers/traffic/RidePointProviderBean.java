package com.anli.busstation.dal.ejb3.providers.traffic;

import com.anli.busstation.dal.ejb3.providers.AbstractBSProviderBean;
import com.anli.busstation.dal.interfaces.entities.traffic.RidePoint;
import com.anli.busstation.dal.interfaces.entities.traffic.RoutePoint;
import com.anli.busstation.dal.interfaces.providers.traffic.RidePointProvider;
import com.anli.busstation.dal.jpa.entities.traffic.RidePointImpl;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import org.joda.time.DateTime;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
@Remote(RidePointProvider.class)
@TransactionAttribute(REQUIRED)
public class RidePointProviderBean extends AbstractBSProviderBean<RidePoint, RidePointImpl>
        implements RidePointProvider {

    @Override
    protected RidePointImpl getEntityInstance() {
        return new RidePointImpl();
    }

    @Override
    protected Class<RidePointImpl> getEntityClass() {
        return RidePointImpl.class;
    }

    @Override
    public List<RidePoint> findByRoutePoint(RoutePoint routePoint) {
        return findByEquals("routePoint", routePoint);
    }

    @Override
    public List<RidePoint> findByAnyRoutePoint(Collection<RoutePoint> routePoints) {
        return findByAny("routePoint", routePoints);
    }

    @Override
    public List<RidePoint> findByArrivalTimeRange(DateTime arrivalTimeLeft, boolean strictLeft,
            DateTime arrivalTimeRight, boolean strictRight) {
        return findByRange("arrivalTime", arrivalTimeLeft, strictLeft, arrivalTimeRight, strictRight);
    }

    @Override
    public List<RidePoint> findByDepartureTimeRange(DateTime departureTimeLeft, boolean strictLeft,
            DateTime departureTimeRight, boolean strictRight) {
        return findByRange("departureTime", departureTimeLeft, strictLeft, departureTimeRight, strictRight);
    }

    @Override
    public List<BigInteger> collectIdsByRoutePoint(RoutePoint routePoint) {
        return collectIdsByEquals("routePoint", routePoint);
    }

    @Override
    public List<BigInteger> collectIdsByAnyRoutePoint(Collection<RoutePoint> routePoints) {
        return collectIdsByAny("routePoint", routePoints);
    }

    @Override
    public List<BigInteger> collectIdsByArrivalTimeRange(DateTime arrivalTimeLeft, boolean strictLeft,
            DateTime arrivalTimeRight, boolean strictRight) {
        return collectIdsByRange("arrivalTime", arrivalTimeLeft, strictLeft, arrivalTimeRight, strictRight);
    }

    @Override
    public List<BigInteger> collectIdsByDepartureTimeRange(DateTime departureTimeLeft, boolean strictLeft,
            DateTime departureTimeRight, boolean strictRight) {
        return collectIdsByRange("departureTime", departureTimeLeft, strictLeft, departureTimeRight,
                strictRight);
    }
}
