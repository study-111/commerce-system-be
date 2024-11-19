package study111.commerce.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.EntityManager;

@Configuration
public class QueryDslConfig {

    @Bean
    JPAQueryFactory queryFactory(EntityManager em) {
        return new JPAQueryFactory(em);
    }
}
