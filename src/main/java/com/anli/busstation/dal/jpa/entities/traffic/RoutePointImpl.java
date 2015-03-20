package com.anli.busstation.dal.jpa.entities.traffic;

import com.anli.busstation.dal.jpa.entities.BSEntityImpl;
import com.anli.busstation.dal.jpa.entities.geography.StationImpl;
import com.anli.busstation.dal.interfaces.entities.BSEntity;
import com.anli.busstation.dal.interfaces.entities.traffic.RoutePoint;
import com.anli.busstation.dal.interfaces.entities.geography.Station;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "RoutePoint")
@Table(name = "route_points")
@AttributeOverride(name = "id", column = @Column(name = "route_point_id"))
public class RoutePointImpl extends BSEntityImpl implements RoutePoint {

    @OneToOne
    @JoinColumn(name = "station", referencedColumnName = "station_id")
    protected StationImpl station;

    @Override
    public Station getStation() {
        return station;
    }

    @Override
    public void setStation(Station station) {
        this.station = (StationImpl) station;
    }

    @Override
    public boolean deepEquals(BSEntity comparee) {
        if (!this.equals(comparee)) {
            return false;
        }
        RoutePointImpl rpComparee = (RoutePointImpl) comparee;
        return nullableDeepEquals(this.station, rpComparee.station);
    }

    @Override
    public String toString() {
        return super.toString() + " station = {" + station + "}";
    }
}
