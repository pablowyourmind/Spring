package tacos.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;
import tacos.entities.Ingredient;
import tacos.entities.Order;
import tacos.entities.Taco;
import types.IngredientType;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

	private final IngredientRepository ingredientRepository;

	private final TacoRepository tacoRepository;

	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
		this.ingredientRepository = ingredientRepository;
		this.tacoRepository = tacoRepository;
	}

	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}

	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	
	@ModelAttribute(name = "ingredients")
	public Map<String, List<Ingredient>> ingredients() {
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredientRepository.findAll().forEach(ingredient -> ingredients.add(ingredient));
		return addIngredients(ingredients);
	}

	@GetMapping
	public String showDesignForm(Model model) {
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredientRepository.findAll().forEach(ingredient -> ingredients.add(ingredient));

		model.addAttribute("ingredients", ingredients());
		model.addAttribute("taco", new Taco());

		return "design";
	}

	@PostMapping
	public String submitForm(@Valid Taco taco, Errors errors, @ModelAttribute Order order, @ModelAttribute TreeMap<String, List<Ingredient>> ingredients, Model model) {

		if (errors.hasErrors()) {
			log.info("Validation errors:\n" + errors.getFieldErrors().stream().map(error -> error.getField() + ": " + error.getDefaultMessage())
					.collect(Collectors.joining("\n")));
			model.addAttribute("fields", errors);
			return "design";
		}

		Taco savedTaco = tacoRepository.save(taco);
		order.addDesign(savedTaco);
		log.info("Taco saved!");
		return "redirect:orders/current";
	}

	private Map<String, List<Ingredient>> addIngredients(List<Ingredient> ingredients) {
		Map<String, List<Ingredient>> mappedIngredients = new TreeMap();
		for (IngredientType ingredientType : IngredientType.values()) {
			List<Ingredient> ingredientsFilteredByType = ingredients.stream()
					.filter(ingredient -> ingredientType.equals(ingredient.getType())).collect(Collectors.toList());
			mappedIngredients.put(ingredientType.toString().toLowerCase(), ingredientsFilteredByType);
		}
		return mappedIngredients;
	}

}
