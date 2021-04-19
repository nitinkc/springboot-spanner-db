package model.spanner;

import com.google.cloud.spring.data.spanner.repository.SpannerRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface TraderRepository extends SpannerRepository<Trader, UUID> {
}
