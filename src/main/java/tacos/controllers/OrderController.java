package tacos.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import tacos.configuration.properties.OrderProperties;
import tacos.data.OrderRepository;
import tacos.entities.Order;
import tacos.entities.security.User;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

	private OrderProperties props;
	private OrderRepository orderRepository;

	public OrderController(OrderRepository orderRepository, OrderProperties props) {
		this.orderRepository = orderRepository;
		this.props = props;
	}

	@GetMapping("/current")
	public String orderForm(@SessionAttribute Order order, @AuthenticationPrincipal User user, Model model) {
		order.setModifiedBy(user);
		order.setName(user.getFullName());
		order.setStreet(user.getStreet());
		order.setCity(user.getCity());
		order.setZip(user.getZipCode());
		order.setCcNumber("4111111111111111");
		order.setCvv("123");
		order.setExpiration("01/21");

		model.addAttribute("order", order);
		return "orderForm";
	}
	
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
		
		if (errors.hasErrors()) {
			log.info("Validation errors:\n" 
					+ errors.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining("\n")));
			return "orderForm";
		}

		log.info("Order created/modified by: " + user.getUsername());
		order.setModifiedBy(user);

		// Submit order
		orderRepository.save(order);
		sessionStatus.setComplete();
		log.info("Order submitted!");
		return "redirect:/";
	}

	@GetMapping("/list")
	public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
		Pageable pageable = PageRequest.of(0, props.getPageSize());
		model.addAttribute("orders", orderRepository.findOrdersByModifiedByOrderByPlacedAtDesc(user, pageable));

		return "orderList";
	}

}
