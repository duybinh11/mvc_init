package MapperData;

import DTO.Request.ItemRequest;
import DTO.Response.ItemResponse;
import Entity.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy  = NullValuePropertyMappingStrategy.IGNORE)
public interface ItemMapper {
    ItemEntity toItemEntity(ItemRequest item);
    ItemResponse toItemResponse(ItemEntity item);
    void updateItemRequestFromItemEntity(ItemRequest itemRequest, @MappingTarget ItemEntity entity);
}
