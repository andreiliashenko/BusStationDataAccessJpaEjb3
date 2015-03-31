package com.anli.busstation.dal.jpa.entities.geography;

import com.anli.busstation.dal.jpa.entities.BSEntityImpl;
import com.anli.busstation.dal.interfaces.entities.BSEntity;
import com.anli.busstation.dal.interfaces.entities.geography.Road;
import com.anli.busstation.dal.interfaces.entities.geography.Station;
import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Road")
@Table(name = "roads")
@AttributeOverride(name = "id", column = @Column(name = "road_id"))
@NamedQueries(value = {
    @NamedQuery(name = "Road.findByStation",
            query = "select distinct r from Road r where r.aStation = ?1 or r.zStation = ?1"),
    @NamedQuery(name = "Road.findByNullStation",
            query = "select distinct r from Road r where r.aStation is null or r.zStation is null"),
    @NamedQuery(name = "Road.findByAnyStation", query = "select distinct r from Road r where "
            + "r.aStation in ?1 or r.zStation in ?1"),
    @NamedQuery(name = "Road.collectIdsByStation",
            query = "select distinct r.id from Road r where r.aStation = ?1 or r.zStation = ?1"),
    @NamedQuery(name = "Road.collectIdsByNullStation",
            query = "select distinct r.id from Road r where r.aStation is null or r.zStation is null"),
    @NamedQuery(name = "Road.collectIdsByAnyStation",
            query = "select distinct r.id from Road r where r.aStation in ?1 or r.zStation in ?1")})
public class RoadImpl extends BSEntityImpl implements Road {

    @OneToOne
    @JoinColumn(name = "a_station", referencedColumnName = "station_id")
    protected StationImpl aStation;
    @OneToOne
    @JoinColumn(name = "z_station", referencedColumnName = "station_id")
    protected StationImpl zStation;
    @Column(name = "\"length\"")
    protected Integer length;
    @Column(name = "ride_price")
    protected BigDecimal ridePrice;

    @Override
    public Station getAStation() {
        return aStation;
    }

    @Override
    public void setAStation(Station aStation) {
        this.aStation = (StationImpl) aStation;
    }

    @Override
    public Station getZStation() {
        return zStation;
    }

    @Override
    public void setZStation(Station zStation) {
        this.zStation = (StationImpl) zStation;
    }

    @Override
    public Integer getLength() {
        return length;
    }

    @Override
    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public BigDecimal getRidePrice() {
        return ridePrice;
    }

    @Override
    public void setRidePrice(BigDecimal ridePrice) {
        this.ridePrice = ridePrice;
    }

    @Override
    public boolean deepEquals(BSEntity comparee) {
        if (!this.equals(comparee)) {
            return false;
        }
        RoadImpl roadComparee = (RoadImpl) comparee;
        return nullableDeepEquals(this.aStation, roadComparee.aStation)
                && nullableDeepEquals(this.zStation, roadComparee.zStation)
                && nullableEquals(this.length, roadComparee.length)
                && nullableEquals(this.ridePrice, roadComparee.ridePrice);
    }

    @Override
    public String toString() {
        return super.toString() + " aStation = {" + aStation + "} zStation = {"
                + zStation + "} length = " + length + " ridePrice = " + ridePrice;
    }
}
