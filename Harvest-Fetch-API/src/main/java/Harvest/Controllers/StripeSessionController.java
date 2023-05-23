package Harvest.Controllers;

import Harvest.Models.FarmerProduct;
import Harvest.Models.OrderItem;
import Harvest.Models.Product;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;


import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
@ConditionalOnWebApplication
public class StripeSessionController {

    public StripeSessionController(@Value("${API_TEST_KEY}") String secretStripeKey) {
        Stripe.apiKey = secretStripeKey;
        StripePriceMap.put(1,"price_1MsxiQB3x4K2H8lxLE5iWCG7");
        StripePriceMap.put(2,"price_1MtGkYB3x4K2H8lxCJecuxUw");
        StripePriceMap.put(3,"price_1MtGluB3x4K2H8lxfSTJl3D5");
        StripePriceMap.put(4,"price_1MtHFCB3x4K2H8lxi6VApojp");
        StripePriceMap.put(5,"price_1MtHFqB3x4K2H8lxPPgG2g0x");
        StripePriceMap.put(6,"price_1MtHH7B3x4K2H8lxKwc10Gfy");
        StripePriceMap.put(7,"price_1MtHHYB3x4K2H8lxw6OEx2fI");
        StripePriceMap.put(8,"price_1MtHI0B3x4K2H8lxw08uxmn9");
        StripePriceMap.put(9,"price_1MtHIZB3x4K2H8lxnnl1GrqW");
        StripePriceMap.put(10,"price_1MtHIyB3x4K2H8lxEw2z4p1H");
        StripePriceMap.put(11,"price_1Msy1kB3x4K2H8lxVcahzUc8");
        StripePriceMap.put(12,"price_1Msy2AB3x4K2H8lxBTPvK8pv");
        StripePriceMap.put(13,"price_1MsxzMB3x4K2H8lxIks0KDK7");
        StripePriceMap.put(14,"price_1Msy3kB3x4K2H8lxcY1RI3ef");
        StripePriceMap.put(15,"price_1Msy6PB3x4K2H8lxeXTQ3ytz");
        StripePriceMap.put(16,"price_1MsxxyB3x4K2H8lxK9IxgolR");
        StripePriceMap.put(17,"price_1MsxuuB3x4K2H8lxKOIehrX5");
        StripePriceMap.put(18,"price_1MsxtCB3x4K2H8lxJgMpMhLq");
        StripePriceMap.put(19,"price_1MsxnSB3x4K2H8lxg2hZwpBN");
        StripePriceMap.put(20,"price_1Msy4EB3x4K2H8lx6csrJ9GJ");
        StripePriceMap.put(21,"price_1MtyBQB3x4K2H8lxWO7AZQXW");


    }

    private HashMap<Integer,String> StripePriceMap = new HashMap<>();





    @PostMapping("/api/create/session")
    public ResponseEntity<String> createSession(@RequestBody List<OrderItem> orderItems) {
        ArrayList<Object> lineItems = new ArrayList<>();

        for (OrderItem oi : orderItems) {
            HashMap<String, Object> lineItem = new HashMap<>();
            lineItem.put("price", StripePriceMap.get(oi.getProductId()));
            lineItem.put("quantity", oi.getQuantity());
            lineItems.add(lineItem);
        }
        HashMap<String, Object> sessionParams = new HashMap<>();
        sessionParams.put("success_url", "http://localhost:3000/success");
        sessionParams.put("cancel_url", "http://localhost:3000/cart");
        sessionParams.put("mode", "payment");
        sessionParams.put("line_items", lineItems);

        try {
            Session session = Session.create(sessionParams);
            return new ResponseEntity<>(session.getUrl(), HttpStatus.OK);
        } catch (StripeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

