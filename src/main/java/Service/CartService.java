package Service;

import DTO.Request.CartCreateRequest;
import DTO.Request.CartUpdateRequest;
import Entity.CartEntity;
import Entity.ItemEntity;
import Entity.UserEntity;
import Exception1.ResourceNotFoundException;
import MapperData.CartMapper;
import Repository.CartRepository;
import Repository.ItemRepository;
import Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartMapper cartMapper;

    public List<CartEntity> findAll() {
        return cartRepository.findAll();
    }

    public List<CartEntity> findAllByUserId(int idUser) {
        Optional<UserEntity> userEntity =  userRepository.findById(idUser);
        if(!userEntity.isPresent()) {
            throw new ResourceNotFoundException("User not found");
        }
        return userEntity.get().getCarts();
    }
    public CartEntity fix(int idCart, CartUpdateRequest cartUpdateRequest) {
        Optional<CartEntity> cartEntity =  cartRepository.findById(idCart);
        if(!cartEntity.isPresent()) {
            throw new ResourceNotFoundException("cartEntity not found");
        }
        cartMapper.updateCart(cartUpdateRequest,cartEntity.get());
        return cartRepository.save(cartEntity.get());
    }

    public void delete(int idCart) {
        Optional<CartEntity> cartEntity =  cartRepository.findById(idCart);
        if(!cartEntity.isPresent()) {
            throw new ResourceNotFoundException("cartEntity not found");
        }
        cartRepository.delete(cartEntity.get());
    }



    public CartEntity addCart(CartCreateRequest cartRequest) {
        Optional<ItemEntity> item = itemRepository.findById(cartRequest.getIdItem());
        Optional<UserEntity> user = userRepository.findById(cartRequest.getIdUser());

        if (!item.isPresent()) {
            throw new ResourceNotFoundException("Item với ID = " + cartRequest.getIdItem() + " không tồn tại!");
        }
        if (!user.isPresent()) {
            throw new ResourceNotFoundException("User với ID = " + cartRequest.getIdUser() + " không tồn tại!");
        }
        CartEntity cartEntity = new CartEntity();
        cartEntity.setQuantity(cartRequest.getQuantity());
        cartEntity.setUser(user.get());
        cartEntity.setItem(item.get());
         return cartRepository.save(cartEntity);
    }



    public CartEntity getCartById(int id) {
        Optional<CartEntity> cartEntity = cartRepository.findById(id);
        if (!cartEntity.isPresent()) {
            throw new ResourceNotFoundException("cart với ID = " + id + " không tồn tại!");
        }
        return  cartEntity.get();
    }
}
