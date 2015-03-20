package com.anli.busstation.dal.jpa.entities.staff;

import com.anli.busstation.dal.jpa.entities.BSEntityImpl;
import com.anli.busstation.dal.interfaces.entities.BSEntity;
import com.anli.busstation.dal.interfaces.entities.staff.MechanicSkill;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "MechanicSkill")
@Table(name = "mechanic_skills")
@AttributeOverride(name = "id", column = @Column(name = "skill_id"))
public class MechanicSkillImpl extends BSEntityImpl implements MechanicSkill {

    @Column(name = "name")
    protected String name;
    @Column(name = "max_diff_level")
    protected Integer maxDiffLevel;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getMaxDiffLevel() {
        return maxDiffLevel;
    }

    @Override
    public void setMaxDiffLevel(Integer maxDiffLevel) {
        this.maxDiffLevel = maxDiffLevel;
    }

    @Override
    public boolean deepEquals(BSEntity comparee) {
        if (!this.equals(comparee)) {
            return false;
        }
        MechanicSkillImpl msComparee = (MechanicSkillImpl) comparee;
        return nullableEquals(this.name, msComparee.name)
                && nullableEquals(this.maxDiffLevel, msComparee.maxDiffLevel);
    }

    @Override
    public String toString() {
        return super.toString() + " name = " + name
                + " maxDiffLevel = " + maxDiffLevel;
    }
}
