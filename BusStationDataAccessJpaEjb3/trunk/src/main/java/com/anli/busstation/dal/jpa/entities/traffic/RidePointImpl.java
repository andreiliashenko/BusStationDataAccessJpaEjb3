package com.anli.busstation.dal.jpa.entities.traffic;

import com.anli.busstation.dal.jpa.entities.BSEntityImpl;
import com.anli.busstation.dal.interfaces.entities.BSEntity;
import com.anli.busstation.dal.interfaces.entities.traffic.RidePoint;
import com.anli.busstation.dal.interfaces.entities.traffic.RoutePoint;
import com.anli.busstation.dal.jpa.converters.DateTimeConverter;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.joda.time.DateTime;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity(name = "RidePoint")
@Table(name = "ride_points")
@AttributeOverride(name = "id", column = @Column(name = "ride_point_id"))
public class RidePointImpl extends BSEntityImpl implements RidePoint {

    @OneToOne
    @JoinColumn(name = "route_point", referencedColumnName = "route_point_id")
    protected RoutePointImpl routePoint;
    @Temporal(TIMESTAMP)
    @Column(name = "arrival_time")
    @Convert(converter = DateTimeConverter.class)
    protected DateTime arrivalTime;
    @Temporal(TIMESTAMP)
    @Column(name = "departure_time")
    @Convert(converter = DateTimeConverter.class)
    protected DateTime departureTime;

    @Override
    public RoutePoint getRoutePoint() {
        return routePoint;
    }

    @Override
    public void setRoutePoint(RoutePoint routePoint) {
        this.routePoint = (RoutePointImpl) routePoint;
    }

    @Override
    public DateTime getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public void setArrivalTime(DateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public DateTime getDepartureTime() {
        return departureTime;
    }

    @Override
    public void setDepartureTime(DateTime departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public boolean deepEquals(BSEntity comparee) {
        if (!this.equals(comparee)) {
            return false;
        }
        RidePointImpl rpComparee = (RidePointImpl) comparee;
        return nullableDeepEquals(this.routePoint, rpComparee.routePoint)
                && nullableEquals(this.arrivalTime, rpComparee.arrivalTime)
                && nullableEquals(this.departureTime, rpComparee.departureTime);
    }

    @Override
    public String toString() {
        return super.toString() + " routePoint = {" + routePoint + "} arrivalTime = "
                + arrivalTime + " departureTime = " + departureTime;
    }
}
