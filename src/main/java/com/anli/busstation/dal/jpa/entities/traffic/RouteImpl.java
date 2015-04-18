package com.anli.busstation.dal.jpa.entities.traffic;

import com.anli.busstation.dal.jpa.entities.BSEntityImpl;
import com.anli.busstation.dal.interfaces.entities.BSEntity;
import com.anli.busstation.dal.interfaces.entities.traffic.Ride;
import com.anli.busstation.dal.interfaces.entities.traffic.Route;
import com.anli.busstation.dal.interfaces.entities.traffic.RoutePoint;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import static javax.persistence.FetchType.LAZY;

@Entity(name = "Route")
@Table(name = "routes")
@AttributeOverride(name = "id", column = @Column(name = "route_id"))
public class RouteImpl extends BSEntityImpl implements Route {

    @Column(name = "num_code")
    protected String numCode;
    @Column(name = "ticket_price")
    protected BigDecimal ticketPrice;
    @OneToMany(fetch = LAZY)
    @JoinColumn(name = "route", referencedColumnName = "route_id")
    @OrderColumn(name = "route_order")
    protected List<RoutePointImpl> routePoints;
    @OneToMany(fetch = LAZY)
    @JoinColumn(name = "route", referencedColumnName = "route_id")
    @OrderColumn(name = "route_order")
    protected List<RideImpl> rides;

    @Override
    public String getNumCode() {
        return numCode;
    }

    @Override
    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    @Override
    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    @Override
    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public List<RoutePoint> getRoutePoints() {
        return (List) routePoints;
    }

    public void setRoutePoints(List<RoutePointImpl> routePoint) {
        this.routePoints = routePoint;
    }

    @Override
    public List<Ride> getRides() {
        return (List) rides;
    }

    public void setRides(List<RideImpl> rides) {
        this.rides = rides;
    }

    @Override
    public boolean deepEquals(BSEntity comparee) {
        if (!this.equals(comparee)) {
            return false;
        }
        RouteImpl routeComparee = (RouteImpl) comparee;
        return nullableEquals(this.numCode, routeComparee.numCode)
                && nullableEquals(this.ticketPrice, routeComparee.ticketPrice)
                && nullableListDeepEquals((List) this.rides, (List) routeComparee.rides)
                && nullableListDeepEquals((List) this.routePoints, (List) routeComparee.routePoints);
    }

    @Override
    public String toString() {
        return super.toString() + " numCode = " + numCode + " ticketPrice = "
                + ticketPrice + " rides = " + rides + " routePoints" + routePoints;
    }
}
