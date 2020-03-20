package domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *@description:
 *@author: Fiona Fung
 *@date: 2020/3/20
 */
@Data
@Accessors(chain = true)
public class Address {
    private String street;
    private int zipCode;
    private int houseNo;
    private String description;
}