package com.anli.busstation.dal.ejb3.providers.staff;

import com.anli.busstation.dal.ejb3.providers.AbstractBSProviderBean;
import com.anli.busstation.dal.interfaces.entities.staff.Employee;
import com.anli.busstation.dal.interfaces.providers.staff.GenericEmployeeProvider;
import com.anli.busstation.dal.jpa.entities.staff.EmployeeImpl;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.TransactionAttribute;
import org.joda.time.DateTime;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@TransactionAttribute(REQUIRED)
public abstract class AbstractEmployeeProviderBean<I extends Employee, E extends EmployeeImpl>
        extends AbstractBSProviderBean<I, E> implements GenericEmployeeProvider<I> {

    @Override
    public List<I> findByName(String name) {
        return findByEquals("name", name);
    }

    @Override
    public List<I> findByNameRegexp(String nameRegexp) {
        return findByRegexp("name", nameRegexp);
    }

    @Override
    public List<I> findBySalaryRange(BigDecimal salaryLeft, boolean strictLeft, BigDecimal salaryRight,
            boolean strictRight) {
        return findByRange("salary", salaryLeft, strictLeft, salaryRight, strictRight);
    }

    @Override
    public List<I> findByHiringDateRange(DateTime hiringDateLeft, boolean strictLeft,
            DateTime hiringDateRight, boolean strictRight) {
        return findByRange("hiringDate", hiringDateLeft, strictLeft, hiringDateRight, strictRight);
    }

    @Override
    public List<BigInteger> collectIdsByName(String name) {
        return collectIdsByEquals("name", name);
    }

    @Override
    public List<BigInteger> collectIdsByNameRegexp(String nameRegexp) {
        return collectIdsByRegexp("name", nameRegexp);
    }

    @Override
    public List<BigInteger> collectIdsBySalaryRange(BigDecimal salaryLeft, boolean strictLeft,
            BigDecimal salaryRight, boolean strictRight) {
        return collectIdsByRange("salary", salaryLeft, strictLeft, salaryRight, strictRight);
    }

    @Override
    public List<BigInteger> collectIdsByHiringDateRange(DateTime hiringDateLeft, boolean strictLeft,
            DateTime hiringDateRight, boolean strictRight) {
        return collectIdsByRange("hiringDate", hiringDateLeft, strictLeft, hiringDateRight, strictRight);
    }
}
