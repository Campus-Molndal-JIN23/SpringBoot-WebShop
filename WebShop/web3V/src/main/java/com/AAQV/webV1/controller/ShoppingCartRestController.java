package com.AAQV.webV1.controller;

import com.AAQV.webV1.Utils.ApiResponse;
import com.AAQV.webV1.dto.cart.AddToCartDto;
import com.AAQV.webV1.dto.cart.CartDto;
import com.AAQV.webV1.error.CartItemNotExistException;
import com.AAQV.webV1.model.ApplicationUser;
import com.AAQV.webV1.model.Product;
import com.AAQV.webV1.service.CartService;
import com.AAQV.webV1.service.ProductService;
import com.AAQV.webV1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cart")
public class ShoppingCartRestController {

    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@AuthenticationPrincipal ApplicationUser user, @RequestBody AddToCartDto addToCartDto) {
        Product product = productService.getById(addToCartDto.getProductId());
        System.out.println("product to add"+  product.getName());
        cartService.addToCart(addToCartDto, product);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);

    }

    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@AuthenticationPrincipal ApplicationUser user) {
        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<CartDto>(cartDto,HttpStatus.OK);
    }

    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<ApiResponse> updateCartItem(@PathVariable int cartItemId,
                                                      @RequestBody @Valid AddToCartDto cartDto,
                                                      @AuthenticationPrincipal ApplicationUser user) {
        Product product = productService.getById(cartDto.getProductId());
        cartService.updateCartItem(cartItemId, cartDto, user, product);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable int cartItemId,
                                                      @RequestParam int userId) {
        try {
            cartService.deleteCartItem(cartItemId, userId);
            return new ResponseEntity<>(new ApiResponse(true, "Cart item has been deleted"), HttpStatus.OK);
        } catch (CartItemNotExistException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

















//    private Logger logger = LoggerFactory.getLogger(ShoppingCartRestController.class);
//
//    @GetMapping(value = "/getAllProducts")
//    public ResponseEntity<List<Product>> getAllProducts() {
//
//        List<Product> productList = productService.findAll();
//
//        return ResponseEntity.ok(productList);
//    }
//
//    @GetMapping(value = "/getOrder/{orderId}")
//    public ResponseEntity<Order> getOrderDetails(@PathVariable int orderId) {
//
//        Order order = orderService.getOrderDetail(orderId);
//        return ResponseEntity.ok(order);
//    }
//
//    @PostMapping("/placeOrder")
//    public ResponseEntity<ResponseOrderDTO> placeOrder(@RequestBody OrderDTO orderDTO) {
//        logger.info("Request Payload " + orderDTO.toString());
//        ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO();
//        float amount = orderService.getCartAmount(orderDTO.getCartItems());
//
//        ApplicationUser customer = new ApplicationUser(orderDTO.getCustomerName(), orderDTO.getCustomerEmail());
//        Integer customerIdFromDb = userService.isCustomerPresent(customer);
//        if (customerIdFromDb != null) {
//            customer.setId(customerIdFromDb);
//            logger.info("Customer already present in db with id : " + customerIdFromDb);
//        }else{
//            customer = userService.saveUser(customer);
//            logger.info("Customer saved.. with id : " + customer.getUserId());
//        }
//        Order order = new Order(orderDTO.getOrderDescription(), customer, orderDTO.getCartItems());
//        order = orderService.saveOrder(order);
//        logger.info("Order processed successfully..");
//
//        responseOrderDTO.setAmount(amount);
//        responseOrderDTO.setDate(String.valueOf(LocalDateTime.now()));
//        responseOrderDTO.setInvoiceNumber(new Random().nextInt(1000));
//        responseOrderDTO.setOrderId(order.getId());
//        responseOrderDTO.setOrderDescription(orderDTO.getOrderDescription());
//
//        logger.info("test push..");
//
//        return ResponseEntity.ok(responseOrderDTO);
//    }






}
