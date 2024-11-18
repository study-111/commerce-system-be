package study111.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String content;

    @Builder
    public TodoEntity(String content) {
        this.content = content;
    }

    public void update(String content) {
        if (!Objects.equals(this.content, content)) {
            this.content=content;
        }
    }
}
