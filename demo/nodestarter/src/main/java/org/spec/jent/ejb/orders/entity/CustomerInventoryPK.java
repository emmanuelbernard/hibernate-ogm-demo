/*
 *   SPECjEnterprise2010 - a benchmark for enterprise middleware
 *  Copyright 1995-2010 Standard Performance Evaluation Corporation
 *   All Rights Reserved
 *
 *  History:
 *  Date        ID, Company             Description
 *  ----------  ----------------        ----------------------------------------------
 *  2009/06/17  Anoop Gupta, Oracle     Created for SPECjEnterprise2010
 */

package org.spec.jent.ejb.orders.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CustomerInventoryPK implements Serializable {
    private Integer id;
    private int custId;

    public CustomerInventoryPK() {
    }

    public CustomerInventoryPK(Integer id, int custId) {
        this.id = id;
        this.custId = custId;  
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        CustomerInventoryPK cip = (CustomerInventoryPK ) other;
        return (custId == cip.custId && (id == cip.id || 
                        ( id != null && id.equals(cip.id))));
    }

    public int hashCode() {
        return (id == null ? 0 : id.hashCode()) ^ custId;
    }

    public Integer getId() {
        return id;
    }

    public int getCustId() {
        return custId;
    }
}
