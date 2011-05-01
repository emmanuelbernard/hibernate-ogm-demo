/*
 *   SPECjEnterprise2010 - a benchmark for enterprise middleware
 *  Copyright 1995-2010 Standard Performance Evaluation Corporation
 *   All Rights Reserved
 */

package org.spec.jent.ejb.orders.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * A compound primary key for {@link OrderLine} consists of the identifier of
 * the owning {@link Order} and a serial number.
 * <br>
 * According to JPA Specification [1], usage of this class as primary key for 
 * persistent entity requires this class
 * <LI> to define a no-arg public constructor
 * <LI> to define {@link Object#equals(Object)} and {@link Object#hashCode()} 
 * methods such that the semantics of value-based equality must be consistent
 * with the database equality of the database types to which this key has been
 * mapped.
 * 
 * [1] JSR-220: Java Persistence API Version 3.0, section 2.1.3 
 * 
 * @author Pinaki Poddar
 */
@SuppressWarnings("serial")
@Embeddable
public class OrderLinePK  implements Serializable{
	private int orderId;
    private int number;
	
    public OrderLinePK() {
        
    }
    
    public OrderLinePK(int orderId, int number) {
        super();
        this.orderId = orderId;
        this.number  = number;
    }

    public int getNumber() {
		return number;
	}
	
    public int getOrderId() {
		return orderId;
	}

    /**
     * Required by JPA (refer Section 2.1.4 of JPA Specification Version 1.0).
     * The semantics of value equality must be consistent with the database
     * equality for the database types to which this key is mapped.  
     */
    @Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + new Integer(number).hashCode();
		result = PRIME * result + new Integer(orderId).hashCode();
		return result;
	}

    /**
     * Required by JPA (refer Section 2.1.4 of JPA Specification Version 1.0).
     * The semantics of value equality must be consistent with the database
     * equality for the database types to which this key is mapped.  
     */
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || !(obj instanceof OrderLinePK ))
			return false;
		
		final OrderLinePK other = (OrderLinePK ) obj;
		return number == other.number && orderId == other.orderId;
	}
	
    @Override
	public String toString() {
	    return ""+orderId+":"+number;
	}
}
