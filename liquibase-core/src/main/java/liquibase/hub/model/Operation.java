package liquibase.hub.model;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;

public class Operation implements HubModel {

    private UUID id;
    private Environment environment;
    private OperationStatus operationStatus;
    private Map<String, String> clientMetadata;
    private Map<String, String> parameters;
    private ZonedDateTime createDate;
    private ZonedDateTime removeDate;

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public OperationStatus getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }

    public Map<String, String> getClientMetadata() {
        return clientMetadata;
    }

    public void setClientMetadata(Map<String, String> clientMetadata) {
        this.clientMetadata = clientMetadata;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public ZonedDateTime getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(ZonedDateTime removeDate) {
        this.removeDate = removeDate;
    }

    public static class OperationStatus {
        private UUID id;
        private String statusMessage;
        private String operationStatusTYpe;


        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public String getStatusMessage() {
            return statusMessage;
        }

        public void setStatusMessage(String statusMessage) {
            this.statusMessage = statusMessage;
        }
    }
}
