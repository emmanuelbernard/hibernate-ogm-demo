/*
 *   SPECjEnterprise2010 - a benchmark for enterprise middleware
 *  Copyright 1995-2010 Standard Performance Evaluation Corporation
 *   All Rights Reserved
 */

package org.spec.jent.ejb.supplier.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;


/**
 * A compound identity comprised of the Manufacturing domain {@link Component}
 * identifier and identifier of the {@link Supplier} of the component.
 *  
 * @author Pinaki Poddar 
 *
 */
@SuppressWarnings("serial")
@Embeddable
public class SupplierComponentPK implements Serializable {

	String componentID;
	int supplierID;
	
    public SupplierComponentPK() {
    }

    public SupplierComponentPK(String suppCompID, int suppCompSuppID) {
        this.componentID = suppCompID;
        this.supplierID = suppCompSuppID;
    }

	public String getComponentID() {
		return componentID;
	}
	
    public int getSupplierID() {
		return supplierID;
	}
	
    @Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + componentID.hashCode();
		result = PRIME * result + supplierID;
		return result;
	}

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		final SupplierComponentPK other = (SupplierComponentPK ) obj;
		return componentID.equals(other.componentID);
	}
}
