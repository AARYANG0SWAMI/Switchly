package live.switchly.api.model;

import java.util.UUID;

public class Flag {

    private final UUID id;
    private final UUID organizationId;
    private final UUID projectId;
    private final String key;
    private final String name;
    private String description;
    private boolean enabled;

    public Flag(UUID id, UUID organizationId,UUID projectId, String key, String name,String description ,boolean enabled) {
        this.id = id;
        this.organizationId = organizationId;
        this.projectId = projectId;
        this.key = key;
        this.name = name;
        this.enabled = enabled;
	    this.description = description;
    }
    public UUID getId(){return id;}
    public UUID getOrganizaionId(){return organizationId;}
    public UUID getProjectId(){return projectId;}
    public String getKey(){return key;}
    public String getName(){return name;}
    public boolean isEnabled(){return enabled;}
    public String getDescription(){return description;}

    public void setEnabled(boolean enabled){this.enabled = enabled;}


}
