package org.java.pizza;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.java.pizza.auth.pojo.Role;
import org.java.pizza.auth.pojo.User;
import org.java.pizza.auth.service.RoleService;
import org.java.pizza.auth.service.UserService;
import org.java.pizza.pojo.Ingredient;
import org.java.pizza.pojo.Pizza;
import org.java.pizza.pojo.SpecialOffer;
import org.java.pizza.service.IngredientService;
import org.java.pizza.service.PizzaService;
import org.java.pizza.service.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private SpecialOfferService specialOfferService;
	@Autowired
	private IngredientService ingredientService;
	
	@Override
	public void run(String... args) throws Exception {
		Ingredient farina00 = new Ingredient("Farina 00");
		Ingredient mozzarella = new Ingredient("mozzarella");
		Ingredient pomodoro = new Ingredient("pomodoro");
		Ingredient acciughe = new Ingredient("acciughe");
		ingredientService.save(acciughe);
		ingredientService.save(pomodoro);
		ingredientService.save(mozzarella);
		ingredientService.save(farina00);
		

		List<Pizza> pizza = Arrays.asList(
				new Pizza("Margherita", "Pomodoro e mozzarella", "https://primochef.it/wp-content/uploads/2019/08/SH_pizza_fatta_in_casa-640x350.jpg.webp", new BigDecimal("8.00"), farina00, mozzarella),
				new Pizza("Diavola", "Pomodoro, mozzarella, salame piccante", "https://www.iffco.it/sites/default/files/styles/free_crop/public/img/recipe/gran-cucina-pizza-diavola.jpg?h=de92a0b7&itok=eC0EvTVI", new BigDecimal("10.00"), mozzarella),
				new Pizza("Napoli", "Pomodoro, mozzarella, acciughe", "https://www.donnamoderna.com/content/uploads/2021/08/pizza-napoli-830x625.jpg", new BigDecimal("20.00")),
				new Pizza("Ortolana", "Pomodoro, mozzarella e verdure", "https://www.demetrafood.it/storage/media/recipes/recipe_images/000088.jpg", new BigDecimal("15.00")),
				new Pizza("Carbonara", "Pomodoro, mozzarella, uovo e guanciale", "https://www.pizzatales.it/wp50/wp-content/uploads/2021/10/la-pizza-alla-carbonara.jpg", new BigDecimal("13.50")),
				new Pizza("Pizza", "Pomodoro, mozzarella, uovo e guanciale", "https://www.pizzatales.it/wp50/wp-content/uploads/2021/10/la-pizza-alla-carbonara.jpg", new BigDecimal("17.50"))
			);
		for (Pizza piz : pizza) {
			pizzaService.save(piz);
		}
		
		SpecialOffer specialOffer = new SpecialOffer(LocalDate.of(2023, 01, 01), LocalDate.of(2023, 01, 10), "Promo 10", 10, pizza.get(0));
		SpecialOffer specialOffer2 = new SpecialOffer(LocalDate.of(2023, 01, 01), LocalDate.of(2023, 01, 10), "Promo 20", 50, pizza.get(0));
		SpecialOffer specialOffer3 = new SpecialOffer(LocalDate.of(2023, 01, 01), LocalDate.of(2023, 01, 10), "Promo 10", 10, pizza.get(1));
		SpecialOffer specialOffer4 = new SpecialOffer(LocalDate.of(2023, 01, 01), LocalDate.of(2023, 01, 10), "Promo 20", 50, pizza.get(1));
		SpecialOffer specialOffer5 = new SpecialOffer(LocalDate.of(2023, 01, 01), LocalDate.of(2023, 01, 10), "Promo 10", 10, pizza.get(2));
		SpecialOffer specialOffer6 = new SpecialOffer(LocalDate.of(2023, 01, 01), LocalDate.of(2023, 01, 10), "Promo 20", 20, pizza.get(2));
		specialOfferService.save(specialOffer);
		specialOfferService.save(specialOffer2);
		specialOfferService.save(specialOffer3);
		specialOfferService.save(specialOffer4);
		specialOfferService.save(specialOffer5);
		specialOfferService.save(specialOffer6);
		
		Optional<Pizza> firstPizzaOpt = pizzaService.findByIdWithSpecialOffer(1);
		Pizza firstPizza = firstPizzaOpt.get();
		
		System.out.println("Pizza:\n" + firstPizza);
		System.out.println("Pizza offerta:\n" + firstPizza.getSpecialOffers());
		
//		ROLES
		Role roleUser = new Role("USER");
		Role roleAdmin = new Role("ADMIN");
		roleService.save(roleUser);
		roleService.save(roleAdmin);
		
		final String pws = new BCryptPasswordEncoder().encode("pws");
		
		User userUser = new User("user", pws, roleUser);
		User userAdmin = new User("admin", pws, roleAdmin);
				
		userService.save(userUser);
		userService.save(userAdmin);
	}

}
