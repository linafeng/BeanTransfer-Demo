package mapstrut;

import domain.Order2;
import dto.OrderQueryParam;
import mapstrut.base.BasicObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 继承基类配置
 *
 * @description:
 * @author: Fiona Fung
 * @date: 2020/3/20
 */
@Mapper//(componentModel = "spring")
public interface InheritBasicOrderMapper extends BasicObjectMapper<Order2, OrderQueryParam> {
    InheritBasicOrderMapper instance = Mappers.getMapper(InheritBasicOrderMapper.class);

  /*  @Override
  重写的话父类的配置继承不下来
    @Mappings({
            @Mapping(source = "status", target = "orderType"),
    })
    OrderQueryParam to(Order2 source);*/
}