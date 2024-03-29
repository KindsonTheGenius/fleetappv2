package com.kindsonthegenius.fleetappv2.fleet.models;

import com.kindsonthegenius.fleetappv2.parameters.models.CommonObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class VehicleModel extends CommonObject {

}
