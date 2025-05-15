package com.ashish.QuickDish.service;
import com.stripe.model.checkout.Session;
import com.ashish.QuickDish.Entity.Order;
import com.ashish.QuickDish.repository.OrderRepository;
import com.stripe.param.checkout.SessionCreateParams;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private final OrderRepository orderRepository;

    public CheckoutServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public String getCheckoutServiceSession(Order order, String successUrl, String failureUrl) {
        try {
            SessionCreateParams params = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl(successUrl)
                    .setCancelUrl(failureUrl)
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setQuantity(1L)
                                    .setPriceData(
                                            SessionCreateParams.LineItem.PriceData.builder()
                                                    .setCurrency("inr")
                                                    .setUnitAmount(
                                                            BigDecimal.valueOf(order.getTotalPrice())
                                                                    .multiply(BigDecimal.valueOf(100))
                                                                    .longValue()
                                                    )
                                                    .setProductData(
                                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                    .setName("FOOD ORDER: " + order.getId())
                                                                    .setDescription("ORDERED ITEMS:" +order.getOrderItems().size())
                                                                    .build()

                                                            )
                                                    .build()

                                                    )
                                    .build()
                                 )
                    .build();

                  Session session = Session.create(params) ;
                  order.setPaymentSessionId(session.getId());
                  orderRepository.save(order);
                  return session.getUrl();

        } catch (Exception ex){
            throw new RuntimeException("failed to create spripe session ",ex);
        }
    }

}
