package Controller10;

import DTO.Request.CartCreateRequest;
import DTO.Request.CartUpdateRequest;
import Entity.CartEntity;
import Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;


    @PostMapping("/addCart")
    public CartEntity addCart(@RequestBody CartCreateRequest cart){
        return cartService.addCart(cart);
    }

    @GetMapping("/cart/{id}")
    public CartEntity getCartById(@PathVariable int id){
        return cartService.getCartById(id);
    }

    @GetMapping("/cartByUserId/{idUser}")
    public List<CartEntity> getAll(@PathVariable(name = "idUser") int id){
        return   cartService.findAllByUserId(id);
    }

    @PutMapping("/cart/{id}")
    public CartEntity fix(@RequestBody CartUpdateRequest cartUpdateRequest, @PathVariable int id){
        return cartService.fix(id,cartUpdateRequest);
    }

    @DeleteMapping("/cart/{id}")
    public void delete(@PathVariable int id){
        cartService.delete(id);
    }



}
