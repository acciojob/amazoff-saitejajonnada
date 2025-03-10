package com.driver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.Order;
import com.example.ordermanagement.model.DeliveryPartner;
import com.example.ordermanagement.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add-order")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.addOrder(order));
    }

    @PostMapping("/add-partner/{partnerId}")
    public ResponseEntity<DeliveryPartner> addPartner(@PathVariable Long partnerId) {
        return ResponseEntity.ok(orderService.addPartner(partnerId));
    }

    @PutMapping("/add-order-partner-pair")
    public ResponseEntity<String> assignOrderToPartner(@RequestParam Long orderId, @RequestParam Long partnerId) {
        orderService.assignOrderToPartner(orderId, partnerId);
        return ResponseEntity.ok("Order assigned successfully");
    }

    @GetMapping("/get-order-by-id/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @GetMapping("/get-partner-by-id/{partnerId}")
    public ResponseEntity<DeliveryPartner> getPartnerById(@PathVariable Long partnerId) {
        return ResponseEntity.ok(orderService.getPartnerById(partnerId));
    }

    @GetMapping("/get-order-count-by-partner-id/{partnerId}")
    public ResponseEntity<Integer> getOrderCountByPartnerId(@PathVariable Long partnerId) {
        return ResponseEntity.ok(orderService.getOrderCountByPartnerId(partnerId));
    }

    @GetMapping("/get-orders-by-partner-id/{partnerId}")
    public ResponseEntity<List<Order>> getOrdersByPartnerId(@PathVariable Long partnerId) {
        return ResponseEntity.ok(orderService.getOrdersByPartnerId(partnerId));
    }

    @GetMapping("/get-all-orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/get-count-of-unassigned-orders")
    public ResponseEntity<Integer> getUnassignedOrderCount() {
        return ResponseEntity.ok(orderService.getUnassignedOrderCount());
    }

    @GetMapping("/get-count-of-orders-left-after-given-time/{time}/{partnerId}")
    public ResponseEntity<Integer> getOrdersLeftAfterTime(@PathVariable String time, @PathVariable Long partnerId) {
        return ResponseEntity.ok(orderService.getOrdersLeftAfterTime(LocalTime.parse(time), partnerId));
    }

    @GetMapping("/get-last-delivery-time/{partnerId}")
    public ResponseEntity<LocalTime> getLastDeliveryTime(@PathVariable Long partnerId) {
        return ResponseEntity.ok(orderService.getLastDeliveryTime(partnerId));
    }

    @DeleteMapping("/delete-partner-by-id/{partnerId}")
    public ResponseEntity<String> deletePartner(@PathVariable Long partnerId) {
        orderService.deletePartnerById(partnerId);
        return ResponseEntity.ok("Partner deleted and orders unassigned");
    }

    @DeleteMapping("/delete-order-by-id/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrderById(orderId);
        return ResponseEntity.ok("Order deleted and partner unassigned");
    }
}
