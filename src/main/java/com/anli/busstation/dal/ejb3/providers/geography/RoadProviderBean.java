package com.anli.busstation.dal.ejb3.providers.geography;

import com.anli.busstation.dal.ejb3.providers.AbstractBSProviderBean;
import com.anli.busstation.dal.interfaces.entities.geography.Road;
import com.anli.busstation.dal.interfaces.entities.geography.Station;
import com.anli.busstation.dal.interfaces.providers.geography.RoadProvider;
import com.anli.busstation.dal.jpa.entities.geography.RoadImpl;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
@Remote(RoadProvider.class)
@TransactionAttribute(REQUIRED)
public class RoadProviderBean extends AbstractBSProviderBean<Road, RoadImpl>
        implements RoadProvider {

    @Override
    protected RoadImpl getEntityInstance() {
        return new RoadImpl();
    }

    @Override
    protected Class<RoadImpl> getEntityClass() {
        return RoadImpl.class;
    }

    @Override
    public List<Road> findByAStation(Station aStation) {
        return findByEquals("aStation", aStation);
    }

    @Override
    public List<Road> findByAnyAStation(Collection<Station> aStations) {
        return findByAny("aStation", aStations);
    }

    @Override
    public List<Road> findByZStation(Station zStation) {
        return findByEquals("zStation", zStation);
    }

    @Override
    public List<Road> findByAnyZStation(Collection<Station> zStations) {
        return findByAny("zStation", zStations);
    }

    @Override
    public List<Road> findByStation(Station station) {
        if (station == null) {
            return findByQuery("Road.findByNullStation", Collections.emptyList(), true);
        } else {
            return findByQuery("Road.findByStation", Collections.singletonList(station), true);
        }
    }

    @Override
    public List<Road> findByAnyStation(Collection<Station> stations) {
        if (stations == null || stations.isEmpty()) {
            return Collections.emptyList();
        } else {
            return findByQuery("Road.findByAnyStation",
                    Collections.singletonList(stations), true);
        }
    }

    @Override
    public List<Road> findByLengthRange(Integer lengthLeft, boolean strictLeft, Integer lengthRight,
            boolean strictRight) {
        return findByRange("length", lengthLeft, strictLeft, lengthRight, strictRight);
    }

    @Override
    public List<Road> findByRidePriceRange(BigDecimal ridePriceLeft, boolean strictLeft,
            BigDecimal ridePriceRight, boolean strictRight) {
        return findByRange("ridePrice", ridePriceLeft, strictLeft, ridePriceRight, strictRight);
    }

    @Override
    public List<BigInteger> collectIdsByAStation(Station aStation) {
        return collectIdsByEquals("aStation", aStation);
    }

    @Override
    public List<BigInteger> collectIdsByAnyAStation(Collection<Station> aStations) {
        return collectIdsByAny("aStation", aStations);
    }

    @Override
    public List<BigInteger> collectIdsByZStation(Station zStation) {
        return collectIdsByEquals("zStation", zStation);
    }

    @Override
    public List<BigInteger> collectIdsByAnyZStation(Collection<Station> zStations) {
        return collectIdsByAny("zStation", zStations);
    }

    @Override
    public List<BigInteger> collectIdsByStation(Station station) {
        if (station == null) {
            return collectIdsByQuery("Road.collectIdsByNullStation", Collections.emptyList(), true);
        } else {
            return collectIdsByQuery("Road.collectIdsByStation",
                    Collections.singletonList(station), true);
        }
    }

    @Override
    public List<BigInteger> collectIdsByAnyStation(Collection<Station> stations) {
        if (stations == null || stations.isEmpty()) {
            return Collections.emptyList();
        } else {
            return collectIdsByQuery("Road.collectIdsByAnyStation",
                    Collections.singletonList(stations), true);
        }
    }

    @Override
    public List<BigInteger> collectIdsByLengthRange(Integer lengthLeft, boolean strictLeft,
            Integer lengthRight, boolean strictRight) {
        return collectIdsByRange("length", lengthLeft, strictLeft, lengthRight, strictRight);
    }

    @Override
    public List<BigInteger> collectIdsByRidePriceRange(BigDecimal ridePriceLeft, boolean strictLeft,
            BigDecimal ridePriceRight, boolean strictRight) {
        return collectIdsByRange("ridePrice", ridePriceLeft, strictLeft, ridePriceRight, strictRight);
    }
}
