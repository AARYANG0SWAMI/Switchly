package live.switchly.api.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import live.switchly.api.model.Organization;

public interface OrganizationRepository {
            Organization save(Organization organization);
            Optional<Organization> findById(UUID id);
            List<Organization> findAll();
            
}
