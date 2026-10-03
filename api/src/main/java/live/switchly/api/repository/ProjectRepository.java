package live.switchly.api.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import live.switchly.api.model.Project;
public interface ProjectRepository {
        Project save(Project project);
        Optional<Project> findById(UUID id);
        List<Project> findByOrganizationId(UUID organizationId);

}
