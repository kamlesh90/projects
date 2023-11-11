package com.few.more.java.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.few.more.java.orderservice.external.InventoryResponse;

import org.springframework.stereotype.Service;

import com.few.more.java.orderservice.dto.ItemDto;
import com.few.more.java.orderservice.dto.OrderRequest;
import com.few.more.java.orderservice.model.Item;
import com.few.more.java.orderservice.model.Order;
import com.few.more.java.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
	
	final OrderRepository orderRepository;
	final WebClient webClient;

	public Order placeOrder(OrderRequest orderRequest) {
		
		Order order = new Order();
				order.setOrderNumber(UUID.randomUUID().toString());
				//.orderItemsList(orderRequest.getOrderItemsList()) // this is shallow cloning it will cause if any change in the copy it will affect the 
														// Original Object to that's why need to do deep cloning
				List<Item> items = orderRequest.getOrderItemsList()
												.stream()
												.map(ItemDto -> mapToDto(ItemDto))
												.toList();
				order.setOrderItemsList(items);

				// get all skuCode from items list
				List<String> skuCodes = items.stream().map(item -> item.getSkuCode()).collect(Collectors.toList());

				//Inventory service checks about items if items available then only we place the order
		InventoryResponse[] inventoryResponsesArray = webClient
				.get()
				.uri("http://localhost:8082/api/inventory",
						uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
				.retrieve()
				.bodyToMono(InventoryResponse[].class)
				.block(); // here use of block : by default webClient makes Async call to avoid Async call and make
						//sync call that's why we used block

		boolean result = Arrays.stream(inventoryResponsesArray).allMatch(InventoryResponse::isInStock);

		if(result){
			Order savedOrder = orderRepository.save(order);
			log.info("order placed with {} successfully ", order.getId());
			return savedOrder;
		}else {
			throw new IllegalArgumentException("Product is not in stock, Please try again later !!");
		}
	}

	private Item mapToDto(ItemDto itemDto) { 
		 
		    Item item = new Item();
		
		    item.setSkuCode(itemDto.getSkuCode());
		    item.setPrice(itemDto.getPrice());
		    item.setQuantity(itemDto.getQuantity()); 
		
		return item;
	}
	
	
	
}
