package com.anli.busstation.dal.jpa.entities.staff;

import com.anli.busstation.dal.jpa.entities.BSEntityImpl;
import com.anli.busstation.dal.interfaces.entities.BSEntity;
import com.anli.busstation.dal.interfaces.entities.staff.Employee;
import com.anli.busstation.dal.jpa.converters.DateTimeConverter;
import com.anli.busstation.dal.jpa.extractors.EmployeeExtractor;
import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.eclipse.persistence.annotations.ClassExtractor;
import org.joda.time.DateTime;

import static javax.persistence.InheritanceType.JOINED;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity(name = "Employee")
@Inheritance(strategy = JOINED)
@ClassExtractor(EmployeeExtractor.class)
@Table(name = "employees")
@AttributeOverride(name = "id", column = @Column(name = "employee_id"))
public abstract class EmployeeImpl extends BSEntityImpl implements Employee {

    @Column(name = "name")
    protected String name;
    @Column(name = "salary")
    protected BigDecimal salary;
    @Temporal(TIMESTAMP)
    @Column(name = "hiring_date")
    @Convert(converter = DateTimeConverter.class)
    protected DateTime hiringDate;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public BigDecimal getSalary() {
        return salary;
    }

    @Override
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public DateTime getHiringDate() {
        return hiringDate;
    }

    @Override
    public void setHiringDate(DateTime hiringDate) {
        this.hiringDate = hiringDate;
    }

    @Override
    public boolean deepEquals(BSEntity comparee) {
        if (!this.equals(comparee)) {
            return false;
        }
        EmployeeImpl empComparee = (EmployeeImpl) comparee;
        return nullableEquals(this.name, empComparee.name)
                && nullableEquals(this.hiringDate, empComparee.hiringDate)
                && nullableEquals(this.salary, empComparee.salary);
    }

    @Override
    public String toString() {
        return super.toString() + " name = " + name
                + " salary = " + salary + " hiringDate = " + hiringDate;
    }
}
