package com.anli.busstation.dal.jpa.entities.traffic;

import com.anli.busstation.dal.jpa.entities.BSEntityImpl;
import com.anli.busstation.dal.jpa.entities.staff.DriverImpl;
import com.anli.busstation.dal.jpa.entities.geography.RoadImpl;
import com.anli.busstation.dal.interfaces.entities.BSEntity;
import com.anli.busstation.dal.interfaces.entities.staff.Driver;
import com.anli.busstation.dal.interfaces.entities.traffic.RideRoad;
import com.anli.busstation.dal.interfaces.entities.geography.Road;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "RideRoad")
@Table(name = "ride_roads")
@AttributeOverride(name = "id", column = @Column(name = "ride_road_id"))
public class RideRoadImpl extends BSEntityImpl implements RideRoad {

    @OneToOne
    @JoinColumn(name = "road", referencedColumnName = "road_id")
    protected RoadImpl road;
    @OneToOne
    @JoinColumn(name = "driver", referencedColumnName = "employee_id")
    protected DriverImpl driver;

    @Override
    public Road getRoad() {
        return road;
    }

    @Override
    public void setRoad(Road road) {
        this.road = (RoadImpl) road;
    }

    @Override
    public Driver getDriver() {
        return driver;
    }

    @Override
    public void setDriver(Driver driver) {
        this.driver = (DriverImpl) driver;
    }

    @Override
    public boolean deepEquals(BSEntity comparee) {
        if (!this.equals(comparee)) {
            return false;
        }
        RideRoadImpl rrComparee = (RideRoadImpl) comparee;
        return nullableDeepEquals(this.driver, rrComparee.driver)
                && nullableDeepEquals(this.road, rrComparee.road);
    }

    @Override
    public String toString() {
        return super.toString() + " driver = {" + driver
                + "} road = {" + road + "}";
    }
}
