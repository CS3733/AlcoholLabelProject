package com.emeraldElves.alcohollabelproject.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Essam on 4/9/2017.
 */
@Entity
@DiscriminatorValue("agent")
public class ManufacturerEntity extends AccountEntity {

}
