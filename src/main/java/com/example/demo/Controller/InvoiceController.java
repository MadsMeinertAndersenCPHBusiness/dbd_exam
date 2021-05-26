package com.example.demo.Controller;

import com.example.demo.Model.Invoice;
import com.example.demo.Model.InvoiceDTO;
import com.example.demo.Service.CartService;
import com.example.demo.Service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public Collection<InvoiceDTO> getInvoices(@PathVariable Long userId){
        return invoiceService.getInvoices(userId);
    }

    @PostMapping("/checkout/{userId}")
    public boolean postInvoice(@PathVariable Long userId){
        var invoice =  invoiceService.addInvoice(userId);
        cartService.emptyCart(userId.toString());
        return invoice;
    }
}
