package com.anli.busstation.dal.jpa.entities.maintenance;

import com.anli.busstation.dal.interfaces.entities.BSEntity;
import com.anli.busstation.dal.interfaces.entities.maintenance.StationService;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity(name = "StationService")
@Table(name = "station_services")
@PrimaryKeyJoinColumn(name = "assignment_id", referencedColumnName = "assignment_id")
public class StationServiceImpl extends TechnicalAssignmentImpl implements StationService {

    @Column(name = "description")
    protected String description;

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean deepEquals(BSEntity comparee) {
        if (!super.deepEquals(comparee)) {
            return false;
        }
        StationServiceImpl ssComparee = (StationServiceImpl) comparee;
        return nullableEquals(this.description, ssComparee.description);
    }

    @Override
    public String toString() {
        return super.toString() + " description = " + description;
    }
}
