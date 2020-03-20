package domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 *@description:
 *@author: Fiona Fung
 *@date: 2020/3/20
 */
@Data
@Accessors(chain = true)
@Setter
@Getter
public class Person {
    private String firstName;
    private String lastName;
    private int height;
    private String description;
}