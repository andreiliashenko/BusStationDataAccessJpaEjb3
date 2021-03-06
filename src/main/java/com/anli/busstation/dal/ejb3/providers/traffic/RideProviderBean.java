package com.anli.busstation.dal.ejb3.providers.traffic;

import com.anli.busstation.dal.ejb3.providers.AbstractBSProviderBean;
import com.anli.busstation.dal.interfaces.entities.traffic.Ride;
import com.anli.busstation.dal.interfaces.entities.traffic.RidePoint;
import com.anli.busstation.dal.interfaces.entities.traffic.RideRoad;
import com.anli.busstation.dal.interfaces.entities.traffic.Ticket;
import com.anli.busstation.dal.interfaces.entities.vehicles.Bus;
import com.anli.busstation.dal.interfaces.providers.traffic.RideProvider;
import com.anli.busstation.dal.jpa.entities.traffic.RideImpl;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
@Remote(RideProvider.class)
@TransactionAttribute(REQUIRED)
public class RideProviderBean extends AbstractBSProviderBean<Ride, RideImpl> implements RideProvider {

    @Override
    protected RideImpl getEntityInstance() {
        return new RideImpl();
    }

    @Override
    protected Class<RideImpl> getEntityClass() {
        return RideImpl.class;
    }

    @Override
    public Ride pullRidePoints(Ride ride) {
        RideImpl originalRide = (RideImpl) ride;
        RideImpl reference = getEntityReference(originalRide);
        originalRide.setRidePoints((List) getCloner()
                .cloneCollection(reference.getRidePoints(), false));
        return originalRide;
    }

    @Override
    public Ride pullRideRoads(Ride ride) {
        RideImpl originalRide = (RideImpl) ride;
        RideImpl reference = getEntityReference(originalRide);
        originalRide.setRideRoads((List) getCloner()
                .cloneCollection(reference.getRideRoads(), false));
        return originalRide;
    }

    @Override
    public Ride pullTickets(Ride ride) {
        RideImpl originalRide = (RideImpl) ride;
        RideImpl reference = getEntityReference(originalRide);
        originalRide.setTickets((List) getCloner()
                .cloneCollection(reference.getTickets(), false));
        return originalRide;
    }

    @Override
    public List<Ride> findByBus(Bus bus) {
        return findByEquals("bus", bus);
    }

    @Override
    public List<Ride> findByAnyBus(Collection<Bus> buses) {
        return findByAny("bus", buses);
    }

    @Override
    public List<Ride> findByRidePoint(RidePoint ridePoint) {
        return findByContains("ridePoints", ridePoint);
    }

    @Override
    public List<Ride> findByAnyRidePoint(Collection<RidePoint> ridePoints) {
        return findByAny("ridePoints", ridePoints);
    }

    @Override
    public List<Ride> findByRideRoad(RideRoad rideRoad) {
        return findByContains("rideRoads", rideRoad);
    }

    @Override
    public List<Ride> findByAnyRideRoad(Collection<RideRoad> rideRoads) {
        return findByAny("rideRoads", rideRoads);
    }

    @Override
    public List<Ride> findByTicket(Ticket ticket) {
        return findByContains("tickets", ticket);
    }

    @Override
    public List<Ride> findByAnyTicket(Collection<Ticket> tickets) {
        return findByAny("tickets", tickets);
    }

    @Override
    public List<BigInteger> collectIdsByBus(Bus bus) {
        return collectIdsByEquals("bus", bus);
    }

    @Override
    public List<BigInteger> collectIdsByAnyBus(Collection<Bus> buses) {
        return collectIdsByAny("bus", buses);
    }

    @Override
    public List<BigInteger> collectIdsByRidePoint(RidePoint ridePoint) {
        return collectIdsByContains("ridePoints", ridePoint);
    }

    @Override
    public List<BigInteger> collectIdsByAnyRidePoint(Collection<RidePoint> ridePoints) {
        return collectIdsByAny("ridePoints", ridePoints);
    }

    @Override
    public List<BigInteger> collectIdsByRideRoad(RideRoad rideRoad) {
        return collectIdsByContains("rideRoads", rideRoad);
    }

    @Override
    public List<BigInteger> collectIdsByAnyRideRoad(Collection<RideRoad> rideRoads) {
        return collectIdsByAny("rideRoads", rideRoads);
    }

    @Override
    public List<BigInteger> collectIdsByTicket(Ticket ticket) {
        return collectIdsByContains("tickets", ticket);
    }

    @Override
    public List<BigInteger> collectIdsByAnyTicket(Collection<Ticket> tickets) {
        return collectIdsByAny("tickets", tickets);
    }
}
