package com.example.demo.Service;

import com.example.demo.Model.Invoice;
import com.example.demo.Model.InvoiceDTO;
import com.example.demo.Model.ProductInvoice;
import com.example.demo.Model.RedisProduct;
import com.example.demo.Repository.InvoiceRepository;
import com.example.demo.Repository.InvoiceRepositoryCustomImpl;
import com.example.demo.Repository.ProductInvoiceRepository;
import com.example.demo.Repository.ProductInvoiceRepositoryCustomImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepositoryCustomImpl invoiceRepositoryCustom;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductInvoiceRepositoryCustomImpl productInvoiceRepositoryCustom;
    @Autowired
    private UserService userService;

    public Collection<InvoiceDTO> getInvoices(Long userId) {
        return invoiceRepositoryCustom.getInvoicesForUser(userId);
    }

    public boolean addInvoice(Long userId) {
        int totalInvoice = 0;
        var invoice = new Invoice();
        var user = userService.findOne(userId);
        if (user.isEmpty()){
            System.out.println("User does not exist");
            return false;
        }
        var cart = cartService.getCart(userId.toString());
        if (cart.isEmpty()){
            System.out.println("Cannot checkout with empty cart");
            return false;
        }
        user.ifPresent(users -> invoice.setAddress(users.getAddress()));
        user.ifPresent(users -> invoice.setUser_id(users));
        invoice.setTotalPrice(totalInvoice);
        Integer returnInvoice = invoiceRepositoryCustom.createInvoice(invoice);
            for (RedisProduct product : cart.values()) {
                long id = Long.parseLong(product.getId());
                var fullProduct = productService.findOne(id);
                int amount = Integer.parseInt(product.getAmount());
                if (fullProduct.isPresent()) {
                    var totalPrice = fullProduct.get().getPrice() * amount;
                    totalInvoice = totalInvoice + fullProduct.get().getPrice() * amount;
                    var productInvoice = new ProductInvoice(fullProduct.get().getId(), Long.parseLong(returnInvoice.toString()), amount, totalPrice);
                    productInvoiceRepositoryCustom.insertProductInvoice(productInvoice);
                }

        }
        invoice.setTotalPrice(totalInvoice);
        invoice.setId((long) returnInvoice);


        return invoiceRepositoryCustom.updateInvoice(invoice);
    }
}
