package study111.commerce.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
