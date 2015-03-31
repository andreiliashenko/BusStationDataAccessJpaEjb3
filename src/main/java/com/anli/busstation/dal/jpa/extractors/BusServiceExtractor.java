package com.anli.busstation.dal.jpa.extractors;

import com.anli.busstation.dal.jpa.entities.maintenance.BusRefuellingImpl;
import com.anli.busstation.dal.jpa.entities.maintenance.BusRepairmentImpl;

public class BusServiceExtractor extends BaseJoinExtractor {

    @Override
    protected Class[] getClasses() {
        return new Class[]{BusRepairmentImpl.class, BusRefuellingImpl.class};
    }
}
