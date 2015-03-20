package com.anli.busstation.dal.jpa.entities.staff;

import com.anli.busstation.dal.interfaces.entities.BSEntity;
import com.anli.busstation.dal.interfaces.entities.staff.Salesman;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity(name = "Salesman")
@Table(name = "salesmen")
@PrimaryKeyJoinColumn(name = "employee_id", referencedColumnName = "employee_id")
public class SalesmanImpl extends EmployeeImpl implements Salesman {

    @Column(name = "total_sales")
    protected Integer totalSales;

    @Override
    public Integer getTotalSales() {
        return totalSales;
    }

    @Override
    public void setTotalSales(Integer totalSales) {
        this.totalSales = totalSales;
    }

    @Override
    public boolean deepEquals(BSEntity comparee) {
        if (!super.deepEquals(comparee)) {
            return false;
        }
        SalesmanImpl salesComparee = (SalesmanImpl) comparee;
        return nullableEquals(this.totalSales, salesComparee.totalSales);
    }

    @Override
    public String toString() {
        return super.toString() + " totalSales = " + totalSales;
    }
}
