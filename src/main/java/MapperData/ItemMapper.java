package Mapper;

import DTO.ItemRequest;
import DTO.ItemResponse;
import Entity.ItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemEntity toItemEntity(ItemRequest item);
    ItemResponse toItemResponse(ItemEntity item);
}
