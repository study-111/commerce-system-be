package study111.commerce.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(name = "category_name_uniq", columnNames = {"name"})
}, indexes = {})
public class Category {

    @GeneratedValue
    @Id
    @Column(name = "category_id")
    private Long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "category_parent_fkey"))
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> children;

    @Builder
    public Category(String name) {
        this.name = name;
    }
}
