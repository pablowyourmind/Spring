package tacos.entities;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import tacos.entities.security.User;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity //JPA
@Table(name = "Taco_Order") //JPA, class name and table name are different, so there is a need to specify the DB name
public class Order {
	
	@Id //JPA
	@GeneratedValue(strategy = GenerationType.AUTO) //JPA
	private Long id;
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@NotBlank(message = "Street is required")
	private String street;
	
	@NotBlank(message = "City is required")
	private String city;

	@NotBlank(message = "Zip Code is required")
	private String zip;
	
	@CreditCardNumber(message = "Wrong Credit Card number")
	private String ccNumber;
	
	@Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
			message = "Expiration date must be formatted MM/YY")
	private String expiration;
	
	@Digits(integer = 3, fraction = 0, message = "Invalid CVV")
	private String cvv;
	
	private Date placedAt;
	
	@ManyToMany(targetEntity = Taco.class) //JPA
	private List<Taco> tacos = new ArrayList<Taco>();

	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "modifiedBy", nullable = false)
	private User modifiedBy;

	public void addDesign(Taco taco) {
		tacos.add(taco);
	}
	
	@PrePersist //JPA
	void placedAt() {
		this.placedAt = new Date();
	}

	public String getTacosNames() {
		return tacos.stream().map(taco -> taco.getTacoName()).collect(Collectors.joining("\n"));
	}
}
