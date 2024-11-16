package study111.commerce.repository;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import study111.commerce.config.QueryDslConfig;
import study111.commerce.config.TempConfig;

@Import({TempConfig.class, QueryDslConfig.class})
@DataJpaTest
public abstract class AbstractDataJpaTest {
}
