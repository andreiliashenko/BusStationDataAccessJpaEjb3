package com.anli.busstation.dal.jpa.entities.staff;

import com.anli.busstation.dal.interfaces.entities.BSEntity;
import com.anli.busstation.dal.interfaces.entities.staff.Driver;
import com.anli.busstation.dal.interfaces.entities.staff.DriverSkill;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity(name = "Driver")
@Table(name = "drivers")
@PrimaryKeyJoinColumn(name = "employee_id", referencedColumnName = "employee_id")
public class DriverImpl extends EmployeeImpl implements Driver {

    @OneToOne
    @JoinColumn(name = "skill", referencedColumnName = "skill_id")
    protected DriverSkillImpl skill;

    @Override
    public DriverSkill getSkill() {
        return skill;
    }

    @Override
    public void setSkill(DriverSkill skill) {
        this.skill = (DriverSkillImpl) skill;
    }

    @Override
    public boolean deepEquals(BSEntity comparee) {
        if (!super.deepEquals(comparee)) {
            return false;
        }
        DriverImpl drComparee = (DriverImpl) comparee;
        return nullableDeepEquals(this.skill, drComparee.skill);
    }

    @Override
    public String toString() {
        return super.toString() + " skill = {" + skill + "}";
    }
}
