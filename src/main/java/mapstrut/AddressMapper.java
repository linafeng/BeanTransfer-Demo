package mapstrut;

import domain.Address;
import domain.Person;
import dto.DeliveryAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
/**
 *@description:multiple DO to one DTO
 *@author: Fiona Fung
 *@date: 2020/3/20
 */

@Mapper
public interface AddressMapper {
    AddressMapper instance = Mappers.getMapper(AddressMapper.class);
    @Mapping(source = "person.description", target = "description")
    @Mapping(source = "address.houseNo", target = "houseNumber")
    DeliveryAddress personAndAddressToDeliveryAddressDto(Person person, Address address);


    /**
     * Person->DeliveryAddress, 缺失地址信息
     * @param person
     * @return
     */
    DeliveryAddress person2deliveryAddress(Person person);
    /**
     * 更新， 使用 Address 来补全 DeliveryAddress 信息。 注意注解 @MappingTarget
     * @param address
     * @param deliveryAddress
     */
    void updateDeliveryAddressFromAddress(Address address,
                                          @MappingTarget DeliveryAddress deliveryAddress);


    /**
     * 更新， 使用 Address 来补全 DeliveryAddress 信息。 注意注解 @MappingTarget
     * 避免覆盖同名字段
     * @param address
     * @param deliveryAddress
     */
    @Mapping(target = "description",ignore = true)
    void updateDeliveryAddressFromAddressIgnoreDesc(Address address,
                                          @MappingTarget DeliveryAddress deliveryAddress);
}