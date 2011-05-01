/*
 *   SPECjEnterprise2010 - a benchmark for enterprise middleware
 *  Copyright 1995-2010 Standard Performance Evaluation Corporation
 *   All Rights Reserved
 */

package org.spec.jent.ejb.mfg.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * Compound primary key for {@link Bom Bill of Materials} comprised of
 * identifier for the Assembly, Component and a line number.
 */
@SuppressWarnings("serial")
@Embeddable
public class BomPK implements Serializable {
	private int lineNo;
	private String assemblyId;
	private String componentId;
	
	/**
	 * A public no-arg constructor is required for Primary Key class by JPA
	 * specification (refer Section 2.1.4 of JPA Specification Version 1.0).  
	 */
    public BomPK() {
    }

    public BomPK(String assemblyId, String componentId, int lineNo) {
        if (assemblyId == null)
            throw new IllegalArgumentException("Can not create a compound " +
               " primary key for BOM with null Assembly identifier");
        if (componentId == null)
            throw new IllegalArgumentException("Can not create a compound " +
               " primary key for BOM with null Component identifier");
        
        this.assemblyId  = assemblyId;
        this.componentId = componentId;
        this.lineNo      = lineNo;
    }
    

	public String getAssemblyId() {
		return assemblyId;
	}
	
	public void setAssemblyId(String assemblyId) {
		this.assemblyId = assemblyId;
	}
	
	public String getComponentId() {
		return componentId;
	}
	
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	
	public int getLineNo() {
		return lineNo;
	}
	
	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}

    @Override
	public String toString() {
		return "Assembly:" + assemblyId + " Component:" + componentId + 
		   " lineNo:" + lineNo;
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
		result = PRIME * result + assemblyId.hashCode();
		result = PRIME * result + componentId.hashCode();
		result = PRIME * result + lineNo;
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
		if (obj == null || !(obj instanceof BomPK))
			return false;
		
		final BomPK other = (BomPK) obj;
		return assemblyId.equals(other.assemblyId) 
		    && componentId.equals(other.componentId)
		    && lineNo == other.lineNo;
	}
}
