package com.fenibook.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
@Getter
@Setter
@Data
@NoArgsConstructor
public class Book {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 20)
    private String isbn;
    
    @Column(nullable = false)
    private String title;
    
    
    @Column(length = 2000)
    private String description;
    
   
    
    private String author;
    private String genre;
    private LocalDate publicationDate;
    private String coverImageUrl;
    
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "book_tag_assignments",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<BookTag> tags = new HashSet<>();
    
	
    public void setTitle(String string) {
		// TODO Auto-generated method stub
		
	}
	public void setAuthor(String string) {
		// TODO Auto-generated method stub
		
	}
	public void setCoverImageUrl(String string) {
		// TODO Auto-generated method stub
		
	}
	public Object getTags() {
		// TODO Auto-generated method stub
		return null;
	}
}