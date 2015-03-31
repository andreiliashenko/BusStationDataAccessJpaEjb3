package com.anli.busstation.dal.jpa.extractors;

import com.anli.busstation.dal.jpa.entities.maintenance.BusServiceImpl;
import com.anli.busstation.dal.jpa.entities.maintenance.StationServiceImpl;

public class TechnicalAssignmentExtractor extends BaseJoinExtractor {

    @Override
    protected Class[] getClasses() {
        return new Class[]{BusServiceImpl.class, StationServiceImpl.class};
    }
}
