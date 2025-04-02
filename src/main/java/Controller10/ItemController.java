package Controller10;

import DTO.Request.ItemRequest;
import DTO.Response.ItemResponse;
import Entity.ItemEntity;
import Service.CartService;
import Service.ItemService;
import Service.UserService;
//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
public class ItemController {
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private CartService cartService;

    @GetMapping("/")
    public String home(HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        WebApplicationContext rootContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);

        if(rootContext != null) {
            String[] beanNames = rootContext.getBeanDefinitionNames();
            System.out.println("Danh sách bean trong Root ApplicationContext:");
            Arrays.stream(beanNames).forEach(System.out::println);
        } else {
            System.out.println("Không tìm thấy Root ApplicationContext.");
        }
        return "home";

    }

    @GetMapping("/items")
    public List<ItemEntity> itemAll(){
        return itemService.findAll();
    }

    @PostMapping("/item")
    public ItemEntity addItem(@RequestBody ItemEntity item){
        return itemService.addItem(item);
    }

    @PutMapping("/item/{id}")
    public ItemResponse fixItem(@Valid @RequestBody ItemRequest item, @PathVariable int id){
        ItemResponse itemResponse = itemService.fixItem(item,id);
        System.out.println(itemResponse);
        return itemResponse;
    }

    @DeleteMapping("/item/{id}")
    public void delete(@PathVariable int id){
        itemService.delete(id);
    }

}
