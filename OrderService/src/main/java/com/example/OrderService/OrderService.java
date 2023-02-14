package com.example.OrderService;

import com.example.OrderService.dto.OrderLineItemsDto;
import com.example.OrderService.dto.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){
    Orderr order= new Orderr();
    order.setOrderNumber(UUID.randomUUID().toString());
    List<OrderLineItems> orderLineItems =orderRequest.getOrderLineItemsDtoList()
            .stream()
            .map(this::mapToDto)
            .toList();
    order.setOrderLineItemsList(orderLineItems);
    orderRepository.save(order);

    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems= new OrderLineItems();
        orderLineItems.setId(orderLineItemsDto.getId());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setSkuCode(orderLineItems.getSkuCode());
        return orderLineItems;
    }
}
