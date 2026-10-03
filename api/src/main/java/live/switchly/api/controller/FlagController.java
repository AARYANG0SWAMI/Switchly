package live.switchly.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import live.switchly.api.dto.CreateFlagRequest;
import live.switchly.api.dto.UpdateFlagStateRequest;
import live.switchly.api.model.Flag;
import live.switchly.api.service.FlagService;

@RestController 
@RequestMapping("/api/v1")
public class FlagController {
    private final FlagService flagService;
    public  FlagController(FlagService flagService){
        this.flagService = flagService;
    }

    @PostMapping("/projects/{projectId}/flags")
    @ResponseStatus(HttpStatus.CREATED)
    public Flag create(@PathVariable UUID projectId,@Valid @RequestBody CreateFlagRequest request){
        return flagService.create(projectId, request.key(), request.name(),request.description());
    }
    @GetMapping("/flags/{flagId}")
    public Flag getById(@PathVariable UUID flagId){
        return flagService.getById(flagId);         
    }
    @GetMapping("/projects/{projectId}/flags")
    public List<Flag> getAllForProject(@PathVariable UUID projectId){
        return flagService.getAllForProject(projectId);
    }
    @PutMapping("/flags/{flagId}/state")
    public Flag setState(@PathVariable UUID flagId, @Valid @RequestBody UpdateFlagStateRequest request){
        return flagService.setEnabled(flagId, request.enabled());
    }
    @DeleteMapping("/api/v1/flags/{flagId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFlag(@PathVariable UUID flagId){
            flagService.delete(flagId);
    }
}
