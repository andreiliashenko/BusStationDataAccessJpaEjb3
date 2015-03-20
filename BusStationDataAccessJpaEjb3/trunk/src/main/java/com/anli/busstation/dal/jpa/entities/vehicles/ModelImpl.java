package com.anli.busstation.dal.jpa.entities.vehicles;

import com.anli.busstation.dal.jpa.entities.BSEntityImpl;
import com.anli.busstation.dal.interfaces.entities.BSEntity;
import com.anli.busstation.dal.interfaces.entities.vehicles.GasLabel;
import com.anli.busstation.dal.interfaces.entities.vehicles.Model;
import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Model")
@Table(name = "models")
@AttributeOverride(name = "id", column = @Column(name = "model_id"))
public class ModelImpl extends BSEntityImpl implements Model {

    @Column(name = "name")
    protected String name;
    @Column(name = "seats_number")
    protected Integer seatsNumber;
    @Column(name = "tank_volume")
    protected Integer tankVolume;
    @OneToOne
    @JoinColumn(name = "gas_label", referencedColumnName = "label_id")
    protected GasLabelImpl gasLabel;
    @Column(name = "gas_rate")
    protected BigDecimal gasRate;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getSeatsNumber() {
        return seatsNumber;
    }

    @Override
    public void setSeatsNumber(Integer seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    @Override
    public Integer getTankVolume() {
        return tankVolume;
    }

    @Override
    public void setTankVolume(Integer tankVolume) {
        this.tankVolume = tankVolume;
    }

    @Override
    public GasLabel getGasLabel() {
        return gasLabel;
    }

    @Override
    public void setGasLabel(GasLabel gasLabel) {
        this.gasLabel = (GasLabelImpl) gasLabel;
    }

    @Override
    public BigDecimal getGasRate() {
        return gasRate;
    }

    @Override
    public void setGasRate(BigDecimal gasRate) {
        this.gasRate = gasRate;
    }

    @Override
    public boolean deepEquals(BSEntity comparee) {
        if (!this.equals(comparee)) {
            return false;
        }
        ModelImpl modelComparee = (ModelImpl) comparee;
        return nullableEquals(this.name, modelComparee.name)
                && nullableEquals(this.seatsNumber, modelComparee.seatsNumber)
                && nullableEquals(this.tankVolume, modelComparee.tankVolume)
                && nullableEquals(this.gasRate, modelComparee.gasRate)
                && nullableDeepEquals(this.gasLabel, modelComparee.gasLabel);
    }

    @Override
    public String toString() {
        return super.toString() + " name = " + name + " seatsNumber = " + seatsNumber
                + " tankVolume = " + tankVolume + " gasRate = " + gasRate + " gasLabel = {" + gasLabel + "}";
    }
}
