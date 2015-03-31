package com.anli.busstation.dal.ejb3.providers.geography;

import com.anli.busstation.dal.ejb3.providers.AbstractBSProviderBean;
import com.anli.busstation.dal.interfaces.entities.geography.Region;
import com.anli.busstation.dal.interfaces.entities.geography.Station;
import com.anli.busstation.dal.interfaces.providers.geography.RegionProvider;
import com.anli.busstation.dal.jpa.entities.geography.RegionImpl;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
@Remote(RegionProvider.class)
@TransactionAttribute(REQUIRED)
public class RegionProviderBean extends AbstractBSProviderBean<Region, RegionImpl>
        implements RegionProvider {

    @Override
    protected RegionImpl getEntityInstance() {
        return new RegionImpl();
    }

    @Override
    protected Class<RegionImpl> getEntityClass() {
        return RegionImpl.class;
    }

    @Override
    public Region pullStations(Region region) {
        RegionImpl originalRegion = (RegionImpl) region;
        originalRegion.setStations((List) checkEntityConsistency(originalRegion, false)
                .getStations());
        return originalRegion;
    }

    @Override
    public List<Region> findByName(String name) {
        return findByEquals("name", name);
    }

    @Override
    public List<Region> findByNameRegexp(String nameRegexp) {
        return findByRegexp("name", nameRegexp);
    }

    @Override
    public List<Region> findByCode(Integer code) {
        return findByEquals("code", code);
    }

    @Override
    public List<Region> findByAnyCode(Collection<Integer> codes) {
        return findByAny("code", codes);
    }

    @Override
    public List<Region> findByStation(Station station) {
        return findByContains("stations", station);
    }

    @Override
    public List<Region> findByAnyStation(Collection<Station> stations) {
        return findByAny("stations", stations);
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
    public List<BigInteger> collectIdsByCode(Integer code) {
        return collectIdsByEquals("code", code);
    }

    @Override
    public List<BigInteger> collectIdsByAnyCode(Collection<Integer> codes) {
        return collectIdsByAny("code", codes);
    }

    @Override
    public List<BigInteger> collectIdsByStation(Station station) {
        return collectIdsByContains("stations", station);
    }

    @Override
    public List<BigInteger> collectIdsByAnyStation(Collection<Station> stations) {
        return collectIdsByAny("stations", stations);
    }
}
