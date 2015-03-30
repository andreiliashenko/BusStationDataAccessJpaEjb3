package com.anli.busstation.dal.ejb3.providers.staff;

import com.anli.busstation.dal.interfaces.entities.staff.Salesman;
import com.anli.busstation.dal.interfaces.providers.staff.SalesmanProvider;
import com.anli.busstation.dal.jpa.entities.staff.SalesmanImpl;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
@TransactionAttribute(REQUIRED)
public class SalesmanProviderBean extends AbstractEmployeeProviderBean<Salesman, SalesmanImpl>
        implements SalesmanProvider {

    @Override
    protected SalesmanImpl getEntityInstance() {
        return new SalesmanImpl();
    }

    @Override
    protected Class<SalesmanImpl> getEntityClass() {
        return SalesmanImpl.class;
    }

    @Override
    public List<Salesman> findByTotalSalesRange(Integer totalSalesLeft, boolean strictLeft,
            Integer totalSalesRight, boolean strictRight) {
        return findByRange("totalSales", totalSalesLeft, strictLeft, totalSalesRight, strictRight);
    }

    @Override
    public List<BigInteger> collectIdsByTotalSalesRange(Integer totalSalesLeft, boolean strictLeft,
            Integer totalSalesRight, boolean strictRight) {
        return collectIdsByRange("totalSales", totalSalesLeft, strictLeft, totalSalesRight, strictRight);
    }
}
