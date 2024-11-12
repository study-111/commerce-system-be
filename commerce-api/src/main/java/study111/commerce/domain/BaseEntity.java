package study111.commerce.domain;

import java.time.Instant;

import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @CreatedBy
    private Long createdBy;

    @CreatedDate
    private Instant createdDate;

    @LastModifiedBy
    private Long modifiedBy;

    @LastModifiedDate
    private Instant modifiedDate;
}
