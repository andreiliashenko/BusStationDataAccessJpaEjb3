package com.anli.busstation.dal.jpa.entities.vehicles;

import com.anli.busstation.dal.jpa.entities.BSEntityImpl;
import com.anli.busstation.dal.interfaces.entities.BSEntity;
import com.anli.busstation.dal.interfaces.entities.vehicles.Bus;
import com.anli.busstation.dal.interfaces.entities.vehicles.Model;
import com.anli.busstation.dal.interfaces.entities.vehicles.TechnicalState;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Bus")
@Table(name = "buses")
@AttributeOverride(name = "id", column = @Column(name = "bus_id"))
public class BusImpl extends BSEntityImpl implements Bus {

    @OneToOne
    @JoinColumn(name = "model", referencedColumnName = "model_id")
    protected ModelImpl model;
    @OneToOne
    @JoinColumn(name = "state", referencedColumnName = "state_id")
    protected TechnicalStateImpl state;
    @Column(name = "plate")
    protected String plate;

    @Override
    public Model getModel() {
        return model;
    }

    @Override
    public void setModel(Model model) {
        this.model = (ModelImpl) model;
    }

    @Override
    public TechnicalState getState() {
        return state;
    }

    @Override
    public void setState(TechnicalState state) {
        this.state = (TechnicalStateImpl) state;
    }

    @Override
    public String getPlate() {
        return plate;
    }

    @Override
    public void setPlate(String plate) {
        this.plate = plate;
    }

    @Override
    public boolean deepEquals(BSEntity comparee) {
        if (!this.equals(comparee)) {
            return false;
        }
        BusImpl busComparee = (BusImpl) comparee;
        return nullableDeepEquals(this.model, busComparee.model)
                && nullableDeepEquals(this.state, busComparee.state)
                && nullableEquals(this.plate, busComparee.plate);

    }

    @Override
    public String toString() {
        return super.toString() + " plate = " + plate
                + " state = {" + state + "} model = {" + model + "}";
    }
}
