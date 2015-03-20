package com.anli.busstation.dal.jpa.entities.staff;

import com.anli.busstation.dal.interfaces.entities.BSEntity;
import com.anli.busstation.dal.interfaces.entities.staff.Mechanic;
import com.anli.busstation.dal.interfaces.entities.staff.MechanicSkill;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity(name = "Mechanics")
@Table(name = "mechanics")
@PrimaryKeyJoinColumn(name = "employee_id", referencedColumnName = "employee_id")
public class MechanicImpl extends EmployeeImpl implements Mechanic {

    @OneToOne
    @JoinColumn(name = "skill", referencedColumnName = "skill_id")
    protected MechanicSkillImpl skill;

    @Override
    public MechanicSkill getSkill() {
        return skill;
    }

    @Override
    public void setSkill(MechanicSkill skill) {
        this.skill = (MechanicSkillImpl) skill;
    }

    @Override
    public boolean deepEquals(BSEntity comparee) {
        if (!super.deepEquals(comparee)) {
            return false;
        }
        MechanicImpl mechComparee = (MechanicImpl) comparee;
        return nullableDeepEquals(this.skill, mechComparee.skill);
    }

    @Override
    public String toString() {
        return super.toString() + " skill = {" + skill + "}";
    }
}
