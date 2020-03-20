package mapstrut;


import domain.Order;
import dto.OrderQueryParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *@description:普通用法
 *@author: Fiona Fung
 *@date: 2020/3/20
 */
@Mapper
public interface CommomBeanMapper {
    public CommomBeanMapper instance = Mappers.getMapper(CommomBeanMapper.class);

    /**
     * 属性名相同的转换
     * @param order
     * @return
     */
    public OrderQueryParam entity2queryParam(Order order);






}