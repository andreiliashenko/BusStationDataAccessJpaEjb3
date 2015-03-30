package com.anli.busstation.dal.ejb3.providers.traffic;

import com.anli.busstation.dal.ejb3.providers.AbstractBSProviderBean;
import com.anli.busstation.dal.interfaces.entities.staff.Salesman;
import com.anli.busstation.dal.interfaces.entities.traffic.RidePoint;
import com.anli.busstation.dal.interfaces.entities.traffic.Ticket;
import com.anli.busstation.dal.interfaces.providers.traffic.TicketProvider;
import com.anli.busstation.dal.jpa.entities.traffic.TicketImpl;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import org.joda.time.DateTime;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
@TransactionAttribute(REQUIRED)
public class TicketProviderBean extends AbstractBSProviderBean<Ticket, TicketImpl>
        implements TicketProvider {

    @Override
    protected TicketImpl getEntityInstance() {
        return new TicketImpl();
    }

    @Override
    protected Class<TicketImpl> getEntityClass() {
        return TicketImpl.class;
    }

    @Override
    public List<Ticket> findByDeparturePoint(RidePoint departurePoint) {
        return findByEquals("departurePoint", departurePoint);
    }

    @Override
    public List<Ticket> findByAnyDeparturePoint(Collection<RidePoint> departurePoints) {
        return findByAny("departurePoint", departurePoints);
    }

    @Override
    public List<Ticket> findByArrivalPoint(RidePoint arrivalPoint) {
        return findByEquals("arrivalPoint", arrivalPoint);
    }

    @Override
    public List<Ticket> findByAnyArrivalPoint(Collection<RidePoint> arrivalPoints) {
        return findByAny("arrivalPoint", arrivalPoints);
    }

    @Override
    public List<Ticket> findBySeat(Integer seat) {
        return findByEquals("seat", seat);
    }

    @Override
    public List<Ticket> findBySeatRange(Integer seatLeft, boolean strictLeft, Integer seatRight,
            boolean strictRight) {
        return findByRange("seat", seatLeft, strictLeft, seatRight, strictRight);
    }

    @Override
    public List<Ticket> findBySalesman(Salesman salesman) {
        return findByEquals("salesman", salesman);
    }

    @Override
    public List<Ticket> findByAnySalesman(Collection<Salesman> salesmen) {
        return findByAny("salesman", salesmen);
    }

    @Override
    public List<Ticket> findBySaleDateRange(DateTime saleDateLeft, boolean strictLeft,
            DateTime saleDateRight, boolean strictRight) {
        return findByRange("saleDate", saleDateLeft, strictLeft, saleDateRight, strictRight);
    }

    @Override
    public List<Ticket> findByCustomerName(String customerName) {
        return findByEquals("customerName", customerName);
    }

    @Override
    public List<Ticket> findByCustomerNameRegexp(String customerNameRegexp) {
        return findByRegexp("customerName", customerNameRegexp);
    }

    @Override
    public List<Ticket> findByPriceRange(BigDecimal priceLeft, boolean strictLeft, BigDecimal priceRight,
            boolean strictRight) {
        return findByRange("price", priceLeft, strictLeft, priceRight, strictRight);
    }

    @Override
    public List<Ticket> findBySold(boolean sold) {
        return findByEquals("sold", sold);
    }

    @Override
    public List<BigInteger> collectIdsByDeparturePoint(RidePoint departurePoint) {
        return collectIdsByEquals("departurePoint", departurePoint);
    }

    @Override
    public List<BigInteger> collectIdsByAnyDeparturePoint(Collection<RidePoint> departurePoints) {
        return collectIdsByAny("departurePoint", departurePoints);
    }

    @Override
    public List<BigInteger> collectIdsByArrivalPoint(RidePoint arrivalPoint) {
        return collectIdsByEquals("arrivalPoint", arrivalPoint);
    }

    @Override
    public List<BigInteger> collectIdsByAnyArrivalPoint(Collection<RidePoint> arrivalPoints) {
        return collectIdsByAny("arrivalPoint", arrivalPoints);
    }

    @Override
    public List<BigInteger> collectIdsBySeat(Integer seat) {
        return collectIdsByEquals("seat", seat);
    }

    @Override
    public List<BigInteger> collectIdsBySeatRange(Integer seatLeft, boolean strictLeft, Integer seatRight,
            boolean strictRight) {
        return collectIdsByRange("seat", seatLeft, strictLeft, seatRight, strictRight);
    }

    @Override
    public List<BigInteger> collectIdsBySalesman(Salesman salesman) {
        return collectIdsByEquals("salesman", salesman);
    }

    @Override
    public List<BigInteger> collectIdsByAnySalesman(Collection<Salesman> salesmen) {
        return collectIdsByAny("salesman", salesmen);
    }

    @Override
    public List<BigInteger> collectIdsBySaleDateRange(DateTime saleDateLeft, boolean strictLeft,
            DateTime saleDateRight, boolean strictRight) {
        return collectIdsByRange("saleDate", saleDateLeft, strictLeft, saleDateRight, strictRight);
    }

    @Override
    public List<BigInteger> collectIdsByCustomerName(String customerName) {
        return collectIdsByEquals("customerName", customerName);
    }

    @Override
    public List<BigInteger> collectIdsByCustomerNameRegexp(String customerNameRegexp) {
        return collectIdsByRegexp("customerName", customerNameRegexp);
    }

    @Override
    public List<BigInteger> collectIdsByPriceRange(BigDecimal priceLeft, boolean strictLeft,
            BigDecimal priceRight, boolean strictRight) {
        return collectIdsByRange("price", priceLeft, strictLeft, priceRight, strictRight);
    }

    @Override
    public List<BigInteger> collectIdsBySold(boolean sold) {
        return collectIdsByEquals("sold", sold);
    }
}
