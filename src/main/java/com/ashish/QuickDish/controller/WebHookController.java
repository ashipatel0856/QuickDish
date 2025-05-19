package com.ashish.QuickDish.controller;

import com.ashish.QuickDish.advice.ApiResponse;
import com.ashish.QuickDish.service.OrderService;
import com.stripe.model.Event;
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
    public ResponseEntity<ApiResponse> capturePayments(
            @RequestBody(required = false) String payload,
            @RequestHeader(value = "Stripe-Signature", required = false) String signature) {

        try {
            if (payload == null || signature == null) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>("Missing payload or Stripe-Signature header"));
            }

            Event event = Webhook.constructEvent(payload, signature, endpointSecret);

            System.out.println(" Received event type: " + event.getType());

            if ("checkout.session.completed".equals(event.getType())) {
                com.stripe.model.checkout.Session session = (com.stripe.model.checkout.Session)
                        event.getDataObjectDeserializer().getObject().orElse(null);

                if (session != null) {
                    String sessionId = session.getId();
                    System.out.println(" Checkout Session completed, Session ID: " + sessionId);
                    orderService.markOrderAsPaid(sessionId);
                } else {
                    System.out.println("Could not deserialize session object.");
                }
            }

            return ResponseEntity.ok(new ApiResponse<>("Webhook received successfully"));

        } catch (Exception e) {
//            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("Webhook error: " + e.getMessage()));
        }
    }
}
