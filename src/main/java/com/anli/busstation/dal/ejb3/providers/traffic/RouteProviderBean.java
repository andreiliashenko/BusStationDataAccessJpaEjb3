package com.anli.busstation.dal.ejb3.providers.traffic;

import com.anli.busstation.dal.ejb3.providers.AbstractBSProviderBean;
import com.anli.busstation.dal.interfaces.entities.traffic.Ride;
import com.anli.busstation.dal.interfaces.entities.traffic.Route;
import com.anli.busstation.dal.interfaces.entities.traffic.RoutePoint;
import com.anli.busstation.dal.interfaces.providers.traffic.RouteProvider;
import com.anli.busstation.dal.jpa.entities.traffic.RouteImpl;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
@Remote(RouteProvider.class)
@TransactionAttribute(REQUIRED)
public class RouteProviderBean extends AbstractBSProviderBean<Route, RouteImpl>
        implements RouteProvider {

    @Override
    protected RouteImpl getEntityInstance() {
        return new RouteImpl();
    }

    @Override
    protected Class<RouteImpl> getEntityClass() {
        return RouteImpl.class;
    }

    @Override
    public Route pullRoutePoints(Route route) {
        RouteImpl originalRoute = (RouteImpl) route;
        originalRoute.setRoutePoints((List) checkEntityConsistency(originalRoute, false).getRoutePoints());
        return originalRoute;
    }

    @Override
    public Route pullRides(Route route) {
        RouteImpl originalRoute = (RouteImpl) route;
        originalRoute.setRides((List) checkEntityConsistency(originalRoute, false).getRides());
        return originalRoute;
    }

    @Override
    public List<Route> findByNumCode(String numCode) {
        return findByEquals("numCode", numCode);
    }

    @Override
    public List<Route> findByAnyNumCode(Collection<String> numCodes) {
        return findByAny("numCode", numCodes);
    }

    @Override
    public List<Route> findByTicketPriceRange(BigDecimal ticketPriceLeft, boolean strictLeft,
            BigDecimal ticketPriceRight, boolean strictRight) {
        return findByRange("ticketPrice", ticketPriceLeft, strictLeft, ticketPriceRight, strictRight);
    }

    @Override
    public List<Route> findByRoutePoint(RoutePoint routePoint) {
        return findByContains("routePoints", routePoint);
    }

    @Override
    public List<Route> findByAnyRoutePoint(Collection<RoutePoint> routePoints) {
        return findByAny("routePoints", routePoints);
    }

    @Override
    public List<Route> findByRide(Ride ride) {
        return findByContains("rides", ride);
    }

    @Override
    public List<Route> findByAnyRide(Collection<Ride> rides) {
        return findByAny("rides", rides);
    }

    @Override
    public List<BigInteger> collectIdsByNumCode(String numCode) {
        return collectIdsByEquals("numCode", numCode);
    }

    @Override
    public List<BigInteger> collectIdsByAnyNumCode(Collection<String> numCodes) {
        return collectIdsByAny("numCode", numCodes);
    }

    @Override
    public List<BigInteger> collectIdsByTicketPriceRange(BigDecimal ticketPriceLeft, boolean strictLeft,
            BigDecimal ticketPriceRight, boolean strictRight) {
        return collectIdsByRange("ticketPrice", ticketPriceLeft, strictLeft, ticketPriceRight, strictRight);
    }

    @Override
    public List<BigInteger> collectIdsByRoutePoint(RoutePoint routePoint) {
        return collectIdsByContains("routePoints", routePoint);
    }

    @Override
    public List<BigInteger> collectIdsByAnyRoutePoint(Collection<RoutePoint> routePoints) {
        return collectIdsByAny("routePoints", routePoints);
    }

    @Override
    public List<BigInteger> collectIdsByRide(Ride ride) {
        return collectIdsByContains("rides", ride);
    }

    @Override
    public List<BigInteger> collectIdsByAnyRide(Collection<Ride> rides) {
        return collectIdsByAny("rides", rides);
    }
}
