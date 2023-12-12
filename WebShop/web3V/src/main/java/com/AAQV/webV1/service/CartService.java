package com.AAQV.webV1.service;

import com.AAQV.webV1.dto.cart.AddToCartDto;
import com.AAQV.webV1.dto.cart.CartDto;
import com.AAQV.webV1.dto.cart.CartItemDto;
import com.AAQV.webV1.error.CartItemNotExistException;
import com.AAQV.webV1.model.ApplicationUser;
import com.AAQV.webV1.model.Product;
import com.AAQV.webV1.model.ShoppingCart;
import com.AAQV.webV1.repository.ShoppingCartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartService {

    @Autowired
    private ShoppingCartRepository cartRepository;

    public CartService(){}

    public CartService(ShoppingCartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void addToCart(AddToCartDto addToCartDto, Product product){
        ShoppingCart cart = new ShoppingCart(product, addToCartDto.getQuantity());
        cartRepository.save(cart);
    }

    public CartDto listCartItems(ApplicationUser user) {
        List<ShoppingCart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<CartItemDto> cartItems = new ArrayList<>();
        for (ShoppingCart cart:cartList){
            CartItemDto cartItemDto = getDtoFromCart(cart);
            cartItems.add(cartItemDto);
        }
        double totalCost = 0;
        for (CartItemDto cartItemDto :cartItems){
            totalCost += (cartItemDto.getProduct().getPrice()* cartItemDto.getQuantity());
        }
        return new CartDto(cartItems,totalCost);
    }

    public static CartItemDto getDtoFromCart(ShoppingCart cart) {
        // Implement the logic to convert ShoppingCart to CartItemDto
        return new CartItemDto(cart);
    }

    public void updateCartItem(int cartItemId, AddToCartDto cartDto, ApplicationUser user, Product product){
        ShoppingCart cart = cartRepository.getOne(cartDto.getId());
        cart.setQuantity(cartDto.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepository.save(cart);
    }
    public void deleteCartItem(int cartItemId, int userId) throws CartItemNotExistException {
        Optional<ShoppingCart> optionalCart = cartRepository.findById(cartItemId);

        if (optionalCart.isPresent()) {
            ShoppingCart cart = optionalCart.get();

            // Assuming the cart is associated with the current user
            if (cart.getUser().getUserId() != userId) {
                throw new AccessDeniedException("You don't have permission to delete this cart item");
            }

            cartRepository.deleteById(cartItemId);
        } else {
            throw new CartItemNotExistException("Cart item with id " + cartItemId + " not found");
        }
    }

    public void deleteCartItems(int userId) {
        cartRepository.deleteAll();
    }


    public void deleteUserCartItems(ApplicationUser user) {
        cartRepository.deleteByUser(user);
    }

}
