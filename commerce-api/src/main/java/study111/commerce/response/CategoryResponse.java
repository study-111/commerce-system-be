package study111.commerce.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import study111.commerce.domain.Category;

@NoArgsConstructor
@Getter
public class CategoryResponse {

    private Long id;
    private String name;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
