package com.anli.busstation.dal.jpa.extractors;

import com.anli.busstation.dal.jpa.entities.staff.DriverImpl;
import com.anli.busstation.dal.jpa.entities.staff.MechanicImpl;
import com.anli.busstation.dal.jpa.entities.staff.SalesmanImpl;

public class EmployeeExtractor extends BaseJoinExtractor {

    @Override
    protected Class[] getClasses() {
        return new Class[]{MechanicImpl.class, DriverImpl.class, SalesmanImpl.class};
    }
}
