/*
 *   SPECjEnterprise2010 - a benchmark for enterprise middleware
 *  Copyright 1995-2010 Standard Performance Evaluation Corporation
 *   All Rights Reserved
 *
 *  History:
 *  Date        ID, Company             Description
 *  ----------  ----------------        ----------------------------------------------
 *  2009/05/31  Anoop Gupta, Oracle     Created for SPECjEnterprise2010
 */

package org.spec.jent.ejb.supplier.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PurchaseOrderPK implements Serializable {
    private int id;
    private Integer site;

    public PurchaseOrderPK() {
    }

    public PurchaseOrderPK(Integer site, int id) {
        this.site = site;
        this.id = id;  
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        PurchaseOrderPK pop = (PurchaseOrderPK ) other;
        return (id == pop.id && (site == pop.site || 
                        (site != null && site.equals(pop.site))));
    }

    public int hashCode() {
        return (site == null ? 0 : site.hashCode()) ^ id;
    }

    public int getId() {
        return id;
    }

    public Integer getSite() {
        return site;
    }
}
