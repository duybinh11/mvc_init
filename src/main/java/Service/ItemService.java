package Service;

import DTO.Request.ItemRequest;
import DTO.Response.ItemResponse;
import Entity.ItemEntity;
//import Mapper.ItemMapper;
import Exception1.ResourceNotFoundException;
import MapperData.ItemMapper;
import Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;

    public List<ItemEntity> findAll() {
        return itemRepository.findAll();
    }

    public ItemEntity addItem(ItemEntity itemEntity) {
        return itemRepository.save(itemEntity);
    }

    public void delete(int id) {
        Optional<ItemEntity> itemEntity = itemRepository.findById(id);
        if(!itemEntity.isPresent()) {
            throw new ResourceNotFoundException("Item not found");
        }
         itemRepository.delete(itemEntity.get());
    }

    public ItemResponse fixItem(ItemRequest itemRequest, int id) {


        Optional<ItemEntity> item = itemRepository.findById(id);
        if(!item.isPresent()){
            throw new ResourceNotFoundException("Item not found");
        }
        itemMapper.updateItemRequestFromItemEntity(itemRequest,item.get());
        System.out.println(item.get());
        
        ItemEntity savedItem = itemRepository.save(item.get());
        return itemMapper.toItemResponse(savedItem);
    }


}
