package com.fenibook.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book_tags")
@Getter
@Setter
@NoArgsConstructor
public class BookTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TagCategory category;

    public enum TagCategory { MOOD, PACE, THEME }

	public void setName(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setCategory(TagCategory mood) {
		// TODO Auto-generated method stub
		
	}
}