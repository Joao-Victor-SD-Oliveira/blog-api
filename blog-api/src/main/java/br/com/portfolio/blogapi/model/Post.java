package br.com.portfolio.blogapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import lombok.Builder;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY) // Muitos posts para um usuário
    @JoinColumn(name = "author_id") // Chave estrangeira
    private User author;

    @CreationTimestamp // Data e hora automáticas na criação
    private LocalDateTime createdAt;

}



