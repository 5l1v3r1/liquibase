package liquibase.hub.core;

import liquibase.changelog.RanChangeSet;
import liquibase.exception.LiquibaseException;
import liquibase.hub.HubService;
import liquibase.hub.LiquibaseHubException;
import liquibase.hub.model.*;

import java.time.ZonedDateTime;
import java.util.*;

public class MockHubService implements HubService {

    public static UUID randomUUID;

    public List<Project> returnProjects = new ArrayList<>();
    public List<Environment> returnEnvironments;
    public List<HubChangeLog> returnChangeLogs = new ArrayList<>();
    public SortedMap<String, List> sentObjects = new TreeMap<>();
    public boolean online = true;

    @Override
    public int getPriority() {
        return PRIORITY_NOT_APPLICABLE;
    }

    public MockHubService() {
        reset();
    }

    @Override
    public boolean isOnline() {
        return online;
    }

    @Override
    public HubUser getMe() throws LiquibaseHubException {
        return null;
    }

    @Override
    public Organization getOrganization() throws LiquibaseHubException {
        return null;
    }

    @Override
    public Project createProject(Project project) {
        return null;
    }

    public HubChangeLog createChangeLog(HubChangeLog hubChangeLog) throws LiquibaseException {
        hubChangeLog.setId(UUID.randomUUID());
        return hubChangeLog;
    }

    @Override
    public List<Project> getProjects() throws LiquibaseHubException {
        Project project1 = new Project();
        project1.setId(UUID.randomUUID());
        project1.setName("Project 1");
        project1.setCreateDate(ZonedDateTime.now());

        Project project2 = new Project();
        project2.setId(UUID.randomUUID());
        project2.setName("Project 2");
        project2.setCreateDate(ZonedDateTime.now());
        return Arrays.asList(project1, project2);
    }

    @Override
    public void setRanChangeSets(Environment environmentId, List<RanChangeSet> ranChangeSets) throws LiquibaseHubException {
        sentObjects.computeIfAbsent("setRanChangeSets/" + environmentId, k -> new ArrayList<>()).addAll(ranChangeSets);
    }

    @Override
    public List<Environment> getEnvironments(Environment exampleEnvironment) {
        return returnEnvironments;
    }

    @Override
    public Environment getEnvironment(Environment exampleEnvironment, boolean createIfNotExists) throws LiquibaseHubException {
        return returnEnvironments.get(0);
    }

    @Override
    public Environment createEnvironment(Environment environment) throws LiquibaseHubException {
        sentObjects.computeIfAbsent("createEnvironment/" + environment.getPrj().getId(), k -> new ArrayList<>()).add(environment);

        return new Environment()
                .setId(UUID.randomUUID())
                .setJdbcUrl(environment.getJdbcUrl());
    }

    @Override
    public HubChangeLog getChangeLog(UUID changeLogId) throws LiquibaseHubException {
        for (HubChangeLog changeLog : returnChangeLogs) {
            if (String.valueOf(changeLog.getId()).equals(String.valueOf(changeLogId))) {
                return changeLog;
            }
        }

        return null;
    }

    @Override
    public Operation createOperation(String operationType, HubChangeLog changeLog, Environment environment, Map<String, String> operationParameters) throws LiquibaseHubException {
        sentObjects.computeIfAbsent("startOperation/" + environment.getId(), k -> new ArrayList<>()).add(operationParameters);

        return null;
    }

    @Override
    public OperationEvent sendOperationEvent(Operation operation, OperationEvent operationEvent) throws LiquibaseException {
        return null;
    }

    @Override
    public void sendOperationChangeEvent(OperationChangeEvent operationChangeEvent) throws LiquibaseException {

    }

    @Override
    public void sendOperationChanges(OperationChange operationChange) throws LiquibaseHubException {

    }

    public void reset() {
        randomUUID = UUID.randomUUID();

        this.returnProjects = new ArrayList<>(Collections.singletonList(
                new Project()
                        .setId(randomUUID)
                        .setName("Test project")
        ));
        this.returnEnvironments = new ArrayList<>(Collections.singletonList(
                new Environment()
                        .setId(randomUUID)
                        .setJdbcUrl("jdbc://test")
                        .setPrj(this.returnProjects.get(0))
        ));
        this.returnChangeLogs = new ArrayList<>(Collections.singletonList(
                new HubChangeLog()
                        .setId(randomUUID)
                        .setName("Mock changelog")
                        .setFileName("com/example/test.xml")
                        .setPrj(this.returnProjects.get(0))
        ));
        this.sentObjects = new TreeMap<>();
    }
}
