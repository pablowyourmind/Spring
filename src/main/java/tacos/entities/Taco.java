package tacos.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity //JPA
@NoArgsConstructor(force = true)
public class Taco {
	
	@Id //JPA
	@GeneratedValue(strategy = GenerationType.AUTO) //JPA
	private Long id;
	
	@NotNull
	@Size(min=5, message="Name must be at least 5 characters long")
	private String tacoName;
	
	@ManyToMany(targetEntity = Ingredient.class) //JPA
	@NotEmpty(message="You must choose at least 1 ingredient")
	private List<Ingredient> ingredients;
	
	private Date createdAt;
	
	@PrePersist //JPA
	void createdAt() {
		this.createdAt = new Date();
	}
}
