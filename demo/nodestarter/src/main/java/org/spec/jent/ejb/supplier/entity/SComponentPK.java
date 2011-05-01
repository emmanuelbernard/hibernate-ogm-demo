/*
 *   SPECjEnterprise2010 - a benchmark for enterprise middleware
 *  Copyright 1995-2010 Standard Performance Evaluation Corporation
 *   All Rights Reserved
 */

package org.spec.jent.ejb.supplier.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class SComponentPK implements Serializable {
    private String compId;

    private int siteId;

    public SComponentPK() {
    }

    public SComponentPK(int siteId, String compId) {
        this.siteId = siteId;
        this.compId = compId;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        SComponentPK scp = (SComponentPK ) other;
        return (siteId == scp.siteId && (compId == scp.compId || (compId != null && compId.equals(scp.compId))));
    }

    @Override
    public int hashCode() {
        return (compId == null ? 0 : compId.hashCode()) ^ siteId;
    }

    public String getComponentId() {
        return compId;
    }

    public int getSiteId() {
        return siteId;
    }
}
