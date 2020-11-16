package tacos.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import types.IngredientType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Entity // JPA annotation
public class Ingredient {
	
	@Id //JPA
	private final String id;
	
	private final String name;
	
	@Enumerated(EnumType.STRING)
	private IngredientType type;
}
