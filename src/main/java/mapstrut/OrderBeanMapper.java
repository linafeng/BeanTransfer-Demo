package mapstrut;


import domain.Order2;
import domain.SubSource;
import domain.SubTarget;
import dto.OrderQueryParam;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 *@description:普通用法
 *@author: Fiona Fung
 *@date: 2020/3/20
 */
@Mapper
public interface OrderBeanMapper {
    public OrderBeanMapper instance = Mappers.getMapper(OrderBeanMapper.class);
    /**
     * 属性名不同的转换用@Mapping
     * @param order
     * @return
     */
    @Mapping(source = "line1",target = "seq")
    public OrderQueryParam entity2queryParam2(Order2 order);

    /**
     * 反转entity2queryParam2
     * @param order
     * @return
     */
    @InheritConfiguration
    public Order2 entity2queryParam3(OrderQueryParam param);

    /**
     * 多个属性名不同的转换用@Mappings包起来
     * @param order
     * @return
     */
    @Mappings({
            @Mapping(source = "line1",target = "seq"),
            @Mapping(source = "line2",target = "title")
    })
    public OrderQueryParam entity2queryParam3(Order2 order);
    /**
     * 可以通过表达式来构造一些简单的转化关系。
     * 虽然设计的时候想兼容很多语言，不过目前只能写Java代码。
     * 嵌套mapper转换
     * @param order
     * @return
     */
    @Mapping( target = "subTarget",expression = "java( SubBeanMapper.instance.subSource2subTarget(order.getSubSource()))")
    public OrderQueryParam entity2queryParam4(Order2 order);


    /**
     * 自定义转换
     * @param order
     * @return
     */
    @Mapping(source = "subSource", target = "subTarget")
    public OrderQueryParam entity2queryParam5(Order2 order);

    default SubTarget uniSubSource2subTarget(SubSource subSource) {
        if (subSource == null) {
            return null;
        }
        SubTarget subTarget = new SubTarget();
        subTarget.setResult("ss");
        subTarget.setName(subSource.getName()==null?"":subSource.getName()+subSource.getName());
        return subTarget;
    }



}