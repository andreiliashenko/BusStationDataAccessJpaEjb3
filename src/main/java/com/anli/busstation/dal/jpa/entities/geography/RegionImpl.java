package com.anli.busstation.dal.jpa.entities.geography;

import com.anli.busstation.dal.jpa.entities.BSEntityImpl;
import com.anli.busstation.dal.interfaces.entities.BSEntity;
import com.anli.busstation.dal.interfaces.entities.geography.Region;
import com.anli.busstation.dal.interfaces.entities.geography.Station;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity(name = "Region")
@Table(name = "regions")
@AttributeOverride(name = "id", column = @Column(name = "region_id"))
public class RegionImpl extends BSEntityImpl implements Region {

    @Column(name = "name")
    protected String name;
    @Column(name = "code")
    protected Integer code;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "region", referencedColumnName = "region_id")
    @OrderColumn(name = "region_order")
    protected List<StationImpl> stations;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public List<Station> getStations() {
        return (List) stations;
    }

    public void setStations(List<StationImpl> stations) {
        this.stations = stations;
    }

    @Override
    public boolean deepEquals(BSEntity comparee) {
        if (!this.equals(comparee)) {
            return false;
        }
        RegionImpl regComparee = (RegionImpl) comparee;
        return nullableEquals(this.name, regComparee.name)
                && nullableEquals(this.code, regComparee.code)
                && nullableListDeepEquals((List) this.stations, (List) regComparee.stations);
    }

    @Override
    public String toString() {
        return super.toString() + " name = " + name + " code = " + code
                + " stations = " + stations;
    }
}
