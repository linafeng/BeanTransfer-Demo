import domain.*;
import dto.DeliveryAddress;
import dto.OrderQueryParam;
import mapstrut.AddressMapper;
import mapstrut.CommomBeanMapper;
import mapstrut.InheritBasicOrderMapper;
import mapstrut.OrderBeanMapper;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Description: ${description}
 * @Author: lina.feng
 * @Date: 2020/3/20 10:50
 * @Version: 1.0
 */
public class TestData {
    public static void main(String[] args) {
        Order order = new Order();
        order.setId(12345L);
        order.setOrderSn("orderSn");
        order.setOrderType(0);
        order.setReceiverKeyword("keyword");
        order.setSourceType(1);
        order.setStatus(2);

        Order2 order2 = new Order2();
        order2.setId(12345L);
        order2.setOrderSn("orderSn");
        order2.setOrderType(0);
        order2.setReceiverKeyword("keyword");
        order2.setSourceType(1);
        order2.setStatus(2);
        order2.setLine1("hhh");//测试@Mapping

        SubSource subSource = new SubSource().setName("名字").setResult("Y");
        order2.setSubSource(subSource);

        Person person = new Person().setDescription("描述");
        Address address = new Address().setHouseNo(101);


        OrderQueryParam orderQueryParam = CommomBeanMapper.instance.entity2queryParam(order);
        OrderQueryParam orderQueryParam2 = OrderBeanMapper.instance.entity2queryParam2(order2);
        OrderQueryParam orderQueryParam4 = OrderBeanMapper.instance.entity2queryParam4(order2);//嵌套
        OrderQueryParam orderQueryParam5 = OrderBeanMapper.instance.entity2queryParam5(order2);//名字复写
        DeliveryAddress deliveryAddress = AddressMapper.instance.personAndAddressToDeliveryAddressDto(person, address);
        System.out.println(deliveryAddress.getDescription()+"-"+deliveryAddress.getHouseNumber());

        System.out.println(orderQueryParam2.getSeq());
        System.out.println(orderQueryParam4.getSubTarget().getName());
        System.out.println(orderQueryParam5.getSubTarget().getName());
        System.out.println(1);
    }

    @Test
    public void testUpdate() {
        Person person = new Person().setFirstName("lina").setDescription("描述");//描述会被覆盖，因为同名
        Address address = new Address().setHouseNo(101).setZipCode(123);
        DeliveryAddress deliveryAddress = AddressMapper.instance.person2deliveryAddress(person);
        AddressMapper.instance.updateDeliveryAddressFromAddress(address,deliveryAddress);
        System.out.println(deliveryAddress.getDescription()+"-"+deliveryAddress.getHouseNumber());

    }

    /**
     * 避免同名字段覆盖
     */
    @Test
    public void testUpdateIgnore() {
        Person person = new Person().setFirstName("lina").setDescription("描述");//描述会被覆盖，因为同名
        Address address = new Address().setHouseNo(101).setZipCode(123);
        DeliveryAddress deliveryAddress = AddressMapper.instance.person2deliveryAddress(person);
        AddressMapper.instance.updateDeliveryAddressFromAddressIgnoreDesc(address,deliveryAddress);
        System.out.println(deliveryAddress.getDescription()+"-"+deliveryAddress.getHouseNumber());

    }

    /**
     * 继承配置
     */
    @Test
    public void InheritConfiguration() {
        Order2 order2 = new Order2();
        order2.setId(12345L);
        order2.setOrderSn("orderSn");
        order2.setOrderType(8);
        order2.setReceiverKeyword("keyword");
        order2.setSourceType(1);
        order2.setStatus(2);
        order2.setLine1("hhh");//测试@Mapping
        OrderQueryParam orderQueryParam = InheritBasicOrderMapper.instance.to(order2);
        System.out.println(orderQueryParam.getStatus());
        System.out.println(orderQueryParam.getOrderType());
        System.out.println(orderQueryParam.getSeq());
        //同名参考配置翻转
        Order2 order4=InheritBasicOrderMapper.instance.from(orderQueryParam);
        System.out.println(order4.getLine1());

    }
    @Test
    public void updateDeliveryAddressFromAddress() {
        Person person = new Person();
        person.setFirstName("first");
        person.setDescription("perSonDescription");
        person.setHeight(183);
        person.setLastName("homejim");
        DeliveryAddress deliveryAddress = AddressMapper.instance.person2deliveryAddress(person);
        Assert.assertEquals(deliveryAddress.getFirstName(), person.getFirstName());
        Assert.assertNull(deliveryAddress.getStreet());
        Address address = new Address();
        address.setDescription("addressDescription");
        address.setHouseNo(29);
        address.setStreet("street");
        address.setZipCode(344);
        AddressMapper.instance.updateDeliveryAddressFromAddress(address, deliveryAddress);
        Assert.assertNotNull(deliveryAddress.getStreet());
    }
}
