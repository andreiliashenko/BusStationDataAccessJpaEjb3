package com.anli.busstation.dal.jpa.entities.staff;

import com.anli.busstation.dal.jpa.entities.BSEntityImpl;
import com.anli.busstation.dal.interfaces.entities.BSEntity;
import com.anli.busstation.dal.interfaces.entities.staff.DriverSkill;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "DriverSkill")
@Table(name = "driver_skills")
@AttributeOverride(name = "id", column = @Column(name = "skill_id"))
public class DriverSkillImpl extends BSEntityImpl implements DriverSkill {

    @Column(name = "name")
    protected String name;
    @Column(name = "max_ride_length")
    protected Integer maxRideLength;
    @Column(name = "max_passengers")
    protected Integer maxPassengers;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getMaxRideLength() {
        return maxRideLength;
    }

    @Override
    public void setMaxRideLength(Integer maxRideLength) {
        this.maxRideLength = maxRideLength;
    }

    @Override
    public Integer getMaxPassengers() {
        return maxPassengers;
    }

    @Override
    public void setMaxPassengers(Integer maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    @Override
    public boolean deepEquals(BSEntity comparee) {
        if (!this.equals(comparee)) {
            return false;
        }
        DriverSkillImpl dsComparee = (DriverSkillImpl) comparee;
        return nullableEquals(this.name, dsComparee.name)
                && nullableEquals(this.maxPassengers, dsComparee.maxPassengers)
                && nullableEquals(this.maxRideLength, dsComparee.maxRideLength);
    }

    @Override
    public String toString() {
        return super.toString() + " name = " + name + " maxPassengers = "
                + maxPassengers + " maxRideLength = " + maxRideLength;
    }
}
