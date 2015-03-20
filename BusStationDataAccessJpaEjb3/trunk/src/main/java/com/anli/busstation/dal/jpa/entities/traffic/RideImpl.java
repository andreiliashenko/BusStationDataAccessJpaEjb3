package com.anli.busstation.dal.jpa.entities.traffic;

import com.anli.busstation.dal.jpa.entities.BSEntityImpl;
import com.anli.busstation.dal.jpa.entities.vehicles.BusImpl;
import com.anli.busstation.dal.interfaces.entities.BSEntity;
import com.anli.busstation.dal.interfaces.entities.vehicles.Bus;
import com.anli.busstation.dal.interfaces.entities.traffic.Ride;
import com.anli.busstation.dal.interfaces.entities.traffic.RidePoint;
import com.anli.busstation.dal.interfaces.entities.traffic.RideRoad;
import com.anli.busstation.dal.interfaces.entities.traffic.Ticket;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity(name = "Ride")
@Table(name = "rides")
@AttributeOverride(name = "id", column = @Column(name = "ride_id"))
public class RideImpl extends BSEntityImpl implements Ride {

    @OneToOne
    @JoinColumn(name = "bus", referencedColumnName = "bus_id")
    protected BusImpl bus;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ride", referencedColumnName = "ride_id")
    @OrderColumn(name = "ride_order")
    protected List<RidePointImpl> ridePoints;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ride", referencedColumnName = "ride_id")
    @OrderColumn(name = "ride_order")
    protected List<RideRoadImpl> rideRoads;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ride", referencedColumnName = "ride_id")
    @OrderColumn(name = "ride_order")
    protected List<TicketImpl> tickets;

    @Override
    public Bus getBus() {
        return bus;
    }

    @Override
    public void setBus(Bus bus) {
        this.bus = (BusImpl) bus;
    }

    @Override
    public List<RidePoint> getRidePoints() {
        return (List) ridePoints;
    }

    public void setRidePoints(List<RidePointImpl> ridePoints) {
        this.ridePoints = ridePoints;
    }

    @Override
    public List<RideRoad> getRideRoads() {
        return (List) rideRoads;
    }

    public void setRideRoads(List<RideRoadImpl> rideRoads) {
        this.rideRoads = rideRoads;
    }

    @Override
    public List<Ticket> getTickets() {
        return (List) tickets;
    }

    public void setTickets(List<TicketImpl> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean deepEquals(BSEntity comparee) {
        if (!this.equals(comparee)) {
            return false;
        }
        RideImpl rideComparee = (RideImpl) comparee;
        return nullableDeepEquals(this.bus, rideComparee.bus)
                && nullableListDeepEquals((List) this.ridePoints, (List) rideComparee.ridePoints)
                && nullableListDeepEquals((List) this.rideRoads, (List) rideComparee.rideRoads)
                && nullableListDeepEquals((List) this.tickets, (List) rideComparee.tickets);
    }

    @Override
    public String toString() {
        return super.toString() + " bus = {" + bus + "} ridePoints = "
                + ridePoints + " rideRoads = " + rideRoads + " tickets = " + tickets;
    }
}
