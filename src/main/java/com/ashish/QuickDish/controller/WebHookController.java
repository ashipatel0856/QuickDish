package com.ashish.QuickDish.controller;

import com.ashish.QuickDish.service.OrderService;
import com.stripe.model.Event;
import com.stripe.model.billingportal.Session;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
public class WebHookController {

    @Value("${stripe.webhook.secret}")
    private String endpointSecret;

    private final OrderService orderService;

    public WebHookController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<String> capturePayments(@RequestBody String payload,
                                                  @RequestHeader("Stripe-Signature") String signature) {
        try {
            Event event = Webhook.constructEvent(payload,signature, endpointSecret);
            if("checkout.session.complete".equals(event.getType())){
                Session session = (Session) event.getDataObjectDeserializer().getObject().get();
                String sessionId = session.getId();

                orderService.markOrderAsPaid(sessionId);
            }
            return ResponseEntity.ok("Webhook received successfully");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Webhook error:"+e.getMessage());
        }
    }
}
