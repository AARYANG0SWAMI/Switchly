package live.switchly.api.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import live.switchly.api.exception.ConflictException;
import live.switchly.api.exception.NotFoundException;
import live.switchly.api.model.Flag;
import live.switchly.api.model.Project;
import live.switchly.api.repository.FlagRepository;

@Service 
public class FlagService {
    private final ProjectService projectService;
    private final FlagRepository flagRepository;
    public FlagService(ProjectService projectService, FlagRepository flagRepository){
        this.projectService = projectService;
        this.flagRepository = flagRepository;
    }

    public Flag create(UUID projectId, String key, String name, String description){
        Project project = projectService.getById(projectId);     //404 if does not exists
        if(flagRepository.existsByProjectIdAndKey(projectId, key)){
            throw new ConflictException("A flag with key '" + key + "' already exists in this project");
        }
        Flag flag = new Flag(UUID.randomUUID(), project.getOrganizationId(), project.getId(),key,name,description,false);
            return flagRepository.save(flag);
    }
    public Flag getById(UUID id){
        return flagRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Flag " + id + " not found"));
    }
    public List<Flag> getAllForProject(UUID projectId){
        projectService.getById(projectId); //404 if does not exists
        return flagRepository.findByProjectId(projectId);
    }
    public Flag setEnabled(UUID flagid, boolean enabled){
        Flag flag = getById(flagid);
        flag.setEnabled(enabled);
        return flagRepository.save(flag);
    }

}
