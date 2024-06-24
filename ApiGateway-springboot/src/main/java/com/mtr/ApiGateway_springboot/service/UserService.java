package com.mtr.ApiGateway_springboot.service;

import com.egzosn.pay.paypal.v2.api.PayPalPayService;
import com.mtr.ApiGateway_springboot.model.UserOrder;
import com.mtr.ApiGateway_springboot.repository.UserOrderRepo;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserOrderRepo userOrderRepo;

    @Value("${razorpay.key.id}")
    private String razorpayKey;

    @Value("${razorpay.secret.key}")
    private String razorpaySecret;

    RazorpayClient client;
    public UserOrder createOrder(UserOrder userOrder) throws RazorpayException {

        JSONObject orderReq = new JSONObject();
        //here amount calculated in indian paisa
        orderReq.put("amount", userOrder.getAmount() * 100);
        orderReq.put("currency", "INR");
        orderReq.put("receipt", userOrder.getEmail());

        this.client = new RazorpayClient(razorpayKey,razorpaySecret);
        //Create Order in razorPay
        Order razorpayOrder =  client.orders.create(orderReq);
        userOrder.setPaypalOrderId(razorpayOrder.get("id"));
        userOrder.setOrderStatus(razorpayOrder.get("status"));

        userOrderRepo.save(userOrder);

        return userOrder;
    }


}
