/*
 *   SPECjEnterprise2010 - a benchmark for enterprise middleware
 *  Copyright 1995-2010 Standard Performance Evaluation Corporation
 *   All Rights Reserved
 */

package org.spec.jent.ejb.supplier.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * Immutable compound primary key for {@link PurchaseOrderLine} comprised of
 * identifier of the {@link PurchaseOrder} that owns this PurchaseOrderLine and
 * a serial number that denotes the position of the PurchaseOrderLine in its
 * owning PurchaseOrder.
 * 
 * @author Pinaki Poddar
 *
 */
@SuppressWarnings("serial")
@Embeddable
public class PurchaseOrderLinePK implements Serializable {
    int lineNumber;  
    int poID;
    int location;

	public PurchaseOrderLinePK() {
    }
	
	public PurchaseOrderLinePK(int poLineNumber, int poLinePoID, int location) {
		this.lineNumber = poLineNumber;
		this.poID   = poLinePoID;
		this.location = location;
	}
	
	public int getLineNumber() {
		return lineNumber;
	}

	public int getPoID() {
		return poID;
	}

	public int getLocation() {
		return location;
	}
	
    @Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + lineNumber;
		result = PRIME * result + poID;
		result = PRIME * result + location;
		return result;
	}

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final PurchaseOrderLinePK other = (PurchaseOrderLinePK ) obj;
		if (lineNumber != other.lineNumber)
			return false;
		if (poID != other.poID)
			return false;
		if (location != other.location)
			return false;
		return true;
	}
}
