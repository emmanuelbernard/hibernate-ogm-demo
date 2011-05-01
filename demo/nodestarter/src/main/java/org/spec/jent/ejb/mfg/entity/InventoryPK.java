/*
 *   SPECjEnterprise2010 - a benchmark for enterprise middleware
 *  Copyright 1995-2010 Standard Performance Evaluation Corporation
 *   All Rights Reserved
 */

package org.spec.jent.ejb.mfg.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class InventoryPK implements Serializable{
  private  String  componentId;
  private  Integer location;

  public InventoryPK() {
  }
  
  public InventoryPK(String componentId, int location) {
    this.componentId = componentId;
    this.location = location;
  }
  
  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof InventoryPK)) {
      return false;
    }
    InventoryPK ip = (InventoryPK)other;
    return (( location == ip.location  ||
            location != null && location.equals(ip.location))
        && ( componentId == ip.componentId
         || ( componentId !=null && componentId.equals(ip.componentId))));
  }

  @Override
  public int hashCode() {
    return ( componentId == null ? 0 : componentId.hashCode()) 
          ^ location.intValue();
  }

  public String getComponentId() {
    return componentId;
  }

  public int getLocation() {
    return location;
  }
}