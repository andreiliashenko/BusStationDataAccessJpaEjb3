package com.anli.busstation.dal.ejb3.providers.staff;

import com.anli.busstation.dal.interfaces.entities.staff.Employee;
import com.anli.busstation.dal.interfaces.providers.staff.EmployeeProvider;
import com.anli.busstation.dal.jpa.entities.staff.EmployeeImpl;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
@TransactionAttribute(REQUIRED)
public class EmployeeProviderBean extends AbstractEmployeeProviderBean<Employee, EmployeeImpl>
        implements EmployeeProvider {

    @Override
    protected EmployeeImpl getEntityInstance() {
        return null;
    }

    @Override
    protected Class<EmployeeImpl> getEntityClass() {
        return EmployeeImpl.class;
    }
}
