package live.switchly.api.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import live.switchly.api.model.Flag;


public interface FlagRepository {
    Flag save(Flag flag);
    Optional<Flag> findById(UUID id);
    List<Flag> findByProjectId(UUID projectId);
    boolean existsByProjectIdAndKey(UUID projectId, String key);

}
