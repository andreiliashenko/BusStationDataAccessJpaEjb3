package com.anli.busstation.dal.ejb3.providers.geography;

import com.anli.busstation.dal.ejb3.providers.AbstractBSProviderBean;
import com.anli.busstation.dal.interfaces.entities.geography.Station;
import com.anli.busstation.dal.interfaces.entities.staff.Employee;
import com.anli.busstation.dal.interfaces.entities.vehicles.Bus;
import com.anli.busstation.dal.interfaces.providers.geography.StationProvider;
import com.anli.busstation.dal.jpa.entities.geography.StationImpl;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
@TransactionAttribute(REQUIRED)
public class StationProviderBean extends AbstractBSProviderBean<Station, StationImpl>
        implements StationProvider {

    @Override
    protected StationImpl getEntityInstance() {
        return new StationImpl();
    }

    @Override
    protected Class<StationImpl> getEntityClass() {
        return StationImpl.class;
    }

    @Override
    public Station pullEmployees(Station station) {
        StationImpl originalStation = (StationImpl) station;
        originalStation.setEmployees((List) checkEntityConsistency(originalStation, false)
                .getEmployees());
        return originalStation;
    }

    @Override
    public Station pullBuses(Station station) {
        StationImpl originalStation = (StationImpl) station;
        originalStation.setBuses((List) checkEntityConsistency(originalStation, false)
                .getBuses());
        return originalStation;
    }

    @Override
    public List<Station> findByName(String name) {
        return findByEquals("name", name);
    }

    @Override
    public List<Station> findByNameRegexp(String regexpName) {
        return findByRegexp("name", regexpName);
    }

    @Override
    public List<Station> findByLatitudeRange(BigDecimal latitudeLeft, boolean strictLeft,
            BigDecimal latitudeRight, boolean strictRight) {
        return findByRange("latitude", latitudeLeft, strictLeft, latitudeRight, strictRight);
    }

    @Override
    public List<Station> findByLongitudeRange(BigDecimal longitudeLeft, boolean strictLeft,
            BigDecimal longitudeRight, boolean strictRight) {
        return findByRange("longitude", longitudeLeft, strictLeft, longitudeRight, strictRight);
    }

    @Override
    public List<Station> findByEmployee(Employee employee) {
        return findByContains("employees", employee);
    }

    @Override
    public List<Station> findByAnyEmployee(Collection<Employee> employees) {
        return findByAny("employees", employees);
    }

    @Override
    public List<Station> findByBus(Bus bus) {
        return findByContains("buses", bus);
    }

    @Override
    public List<Station> findByAnyBus(Collection<Bus> buses) {
        return findByAny("buses", buses);
    }

    @Override
    public List<BigInteger> collectIdsByName(String name) {
        return collectIdsByEquals("name", name);
    }

    @Override
    public List<BigInteger> collectIdsByNameRegexp(String regexpName) {
        return collectIdsByRegexp("name", regexpName);
    }

    @Override
    public List<BigInteger> collectIdsByLatitudeRange(BigDecimal latitudeLeft, boolean strictLeft,
            BigDecimal latitudeRight, boolean strictRight) {
        return collectIdsByRange("latitude", latitudeLeft, strictLeft, latitudeRight, strictRight);
    }

    @Override
    public List<BigInteger> collectIdsByLongitudeRange(BigDecimal longitudeLeft, boolean strictLeft,
            BigDecimal longitudeRight, boolean strictRight) {
        return collectIdsByRange("longitude", longitudeLeft, strictLeft, longitudeRight, strictRight);
    }

    @Override
    public List<BigInteger> collectIdsByEmployee(Employee employee) {
        return collectIdsByContains("employees", employee);
    }

    @Override
    public List<BigInteger> collectIdsByAnyEmployee(Collection<Employee> employees) {
        return collectIdsByAny("employees", employees);
    }

    @Override
    public List<BigInteger> collectIdsByBus(Bus bus) {
        return collectIdsByContains("buses", bus);
    }

    @Override
    public List<BigInteger> collectIdsByAnyBus(Collection<Bus> buses) {
        return collectIdsByAny("buses", buses);
    }
}
