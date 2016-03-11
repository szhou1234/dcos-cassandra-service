package com.mesosphere.dcos.cassandra.common.tasks.backup;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mesosphere.dcos.cassandra.common.CassandraProtos;
import com.mesosphere.dcos.cassandra.common.tasks.CassandraDaemonTask;
import com.mesosphere.dcos.cassandra.common.tasks.CassandraTask;
import com.mesosphere.dcos.cassandra.common.tasks.CassandraTaskExecutor;
import com.mesosphere.dcos.cassandra.common.tasks.CassandraTaskStatus;
import org.apache.mesos.Protos;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.apache.mesos.protobuf.ResourceBuilder.*;

public class DownloadSnapshotTask extends CassandraTask {
    public static final String NAME_PREFIX = "download-";
    public static final String nameForDaemon(final String daemonName){
        return NAME_PREFIX + daemonName;
    }
    public static final String nameForDaemon(final CassandraDaemonTask daemon){
        return nameForDaemon(daemon.getName());
    }
    public static class Builder {
        private String id;
        private String slaveId;
        private String hostname;
        private CassandraTaskExecutor executor;
        private String name;
        private String role;
        private String principal;
        private double cpus;
        private int memoryMb;
        private int diskMb;
        private DownloadSnapshotStatus status;
        private String backupName;
        private String externalLocation;
        private String s3AccessKey;
        private String s3SecretKey;
        private String localLocation;

        private Builder(DownloadSnapshotTask task) {

            this.id = task.id;
            this.slaveId = task.slaveId;
            this.hostname = task.hostname;
            this.executor = task.executor;
            this.name = task.name;
            this.role = task.role;
            this.principal = task.principal;
            this.cpus = task.cpus;
            this.memoryMb = task.memoryMb;
            this.diskMb = task.diskMb;
            this.status = task.getStatus();
            this.backupName = task.backupName;
            this.externalLocation = task.externalLocation;
            this.s3AccessKey = task.s3AccessKey;
            this.s3SecretKey = task.s3SecretKey;
            this.localLocation = task.localLocation;
        }

        public double getCpus() {
            return cpus;
        }

        public Builder setCpus(double cpus) {
            this.cpus = cpus;
            return this;
        }

        public int getDiskMb() {
            return diskMb;
        }

        public Builder setDiskMb(int diskMb) {
            this.diskMb = diskMb;
            return this;
        }

        public CassandraTaskExecutor getExecutor() {
            return executor;
        }

        public Builder setExecutor(CassandraTaskExecutor executor) {
            this.executor = executor;
            return this;
        }

        public String getExternalLocation() {
            return externalLocation;
        }

        public Builder setExternalLocation(String externalLocation) {
            this.externalLocation = externalLocation;
            return this;
        }

        public String getHostname() {
            return hostname;
        }

        public Builder setHostname(String hostname) {
            this.hostname = hostname;
            return this;
        }

        public String getId() {
            return id;
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public int getMemoryMb() {
            return memoryMb;
        }

        public Builder setMemoryMb(int memoryMb) {
            this.memoryMb = memoryMb;
            return this;
        }

        public String getName() {
            return name;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public String getPrincipal() {
            return principal;
        }

        public Builder setPrincipal(String principal) {
            this.principal = principal;
            return this;
        }

        public String getRole() {
            return role;
        }

        public Builder setRole(String role) {
            this.role = role;
            return this;
        }

        public String getS3AccessKey() {
            return s3AccessKey;
        }

        public Builder setS3AccessKey(String s3AccessKey) {
            this.s3AccessKey = s3AccessKey;
            return this;
        }

        public String getS3SecretKey() {
            return s3SecretKey;
        }

        public Builder setS3SecretKey(String s3SecretKey) {
            this.s3SecretKey = s3SecretKey;
            return this;
        }

        public String getSlaveId() {
            return slaveId;
        }

        public Builder setSlaveId(String slaveId) {
            this.slaveId = slaveId;
            return this;
        }

        public DownloadSnapshotStatus getStatus() {
            return status;
        }

        public Builder setStatus(DownloadSnapshotStatus status) {
            this.status = status;
            return this;
        }

        public DownloadSnapshotTask build() {
            return create(id,
                    slaveId,
                    hostname,
                    executor,
                    name,
                    role,
                    principal,
                    cpus,
                    memoryMb,
                    diskMb,
                    status,
                    backupName,
                    externalLocation,
                    s3AccessKey,
                    s3SecretKey,
                    localLocation);
        }
    }

    @JsonProperty("backupName")
    private final String backupName;

    @JsonProperty("externalLocation")
    private final String externalLocation;

    @JsonProperty("localLocation")
    private final String localLocation;

    @JsonProperty("s3AccessKey")
    private final String s3AccessKey;

    @JsonProperty("s3SecretKey")
    private final String s3SecretKey;

    @JsonCreator
    public static DownloadSnapshotTask create(
            @JsonProperty("id") String id,
            @JsonProperty("slaveId") String slaveId,
            @JsonProperty("hostname") String hostname,
            @JsonProperty("executor") CassandraTaskExecutor executor,
            @JsonProperty("name") String name,
            @JsonProperty("role") String role,
            @JsonProperty("principal") String principal,
            @JsonProperty("cpus") double cpus,
            @JsonProperty("memoryMb") int memoryMb,
            @JsonProperty("diskMb") int diskMb,
            @JsonProperty("status") DownloadSnapshotStatus status,
            @JsonProperty("backupName") String backupName,
            @JsonProperty("externalLocation") String externalLocation,
            @JsonProperty("s3AccessKey") String s3AccessKey,
            @JsonProperty("s3SecretKey") String s3SecretKey,
            @JsonProperty("localLocation") String localLocation) {
        return new DownloadSnapshotTask(id,
                slaveId,
                hostname,
                executor,
                name,
                role,
                principal,
                cpus,
                memoryMb,
                diskMb,
                status,
                backupName,
                externalLocation,
                s3AccessKey,
                s3SecretKey,
                localLocation);
    }

    protected DownloadSnapshotTask(
            String id,
            String slaveId,
            String hostname,
            CassandraTaskExecutor executor,
            String name,
            String role,
            String principal,
            double cpus,
            int memoryMb,
            int diskMb,
            DownloadSnapshotStatus status,
            String backupName,
            String externalLocation,
            String s3AccessKey,
            String s3SecretKey,
            String localLocation) {
        super(TYPE.SNAPSHOT_DOWNLOAD,
                id,
                slaveId,
                hostname,
                executor,
                name,
                role,
                principal,
                cpus,
                memoryMb,
                diskMb,
                status);

        this.backupName = backupName;
        this.externalLocation = externalLocation;
        this.s3AccessKey = s3AccessKey;
        this.s3SecretKey = s3SecretKey;
        this.localLocation = localLocation;
    }

    public String getBackupName() {
        return backupName;
    }

    public String getExternalLocation() {
        return externalLocation;
    }

    public String getS3AccessKey() {
        return s3AccessKey;
    }

    public String getS3SecretKey() {
        return s3SecretKey;
    }

    public String getLocalLocation() {
        return localLocation;
    }

    @Override
    public CassandraProtos.CassandraTaskData getTaskData() {
        return CassandraProtos.CassandraTaskData.newBuilder()
                .setType(CassandraProtos.CassandraTaskData.TYPE.SNAPSHOT_DOWNLOAD)
                .setBackupName(backupName)
                .setExternalLocation(externalLocation)
                .setLocalLocation(localLocation)
                .setS3AccessKey(s3AccessKey)
                .setS3SecretKey(s3SecretKey)
                .build();
    }

    @Override
    public DownloadSnapshotTask update(Protos.Offer offer) {
        return create(id,
                offer.getSlaveId().getValue(),
                offer.getHostname(),
                executor,
                name,
                role,
                principal,
                cpus,
                memoryMb,
                diskMb,
                (DownloadSnapshotStatus) status,
                backupName,
                externalLocation,
                s3AccessKey,
                s3SecretKey,
                localLocation);
    }

    @Override
    public CassandraTask updateId(String id) {
        return create(id,
                slaveId,
                hostname,
                executor,
                name,
                role,
                principal,
                cpus,
                memoryMb,
                diskMb,
                (DownloadSnapshotStatus) status,
                backupName,
                externalLocation,
                s3AccessKey,
                s3SecretKey,
                localLocation);
    }

    @Override
    public DownloadSnapshotTask update(Protos.TaskState state) {
        return create(id,
                slaveId,
                hostname,
                executor,
                name,
                role,
                principal,
                cpus,
                memoryMb,
                diskMb,
                ((DownloadSnapshotStatus) status).update(state),
                backupName,
                externalLocation,
                s3AccessKey,
                s3SecretKey,
                localLocation);
    }

    @Override
    public DownloadSnapshotTask update(CassandraTaskStatus status) {
        if (status.getType() == TYPE.SNAPSHOT_DOWNLOAD &&
                status.getId().equals(id)) {

            return create(id,
                    slaveId,
                    hostname,
                    executor,
                    name,
                    role,
                    principal,
                    cpus,
                    memoryMb,
                    diskMb,
                    (DownloadSnapshotStatus) status,
                    backupName,
                    externalLocation,
                    s3AccessKey,
                    s3SecretKey,
                    localLocation);
        } else {
            return this;
        }
    }

    public Builder mutable() {
        return new Builder(this);
    }

    @Override
    public DownloadSnapshotStatus getStatus() {

        return (DownloadSnapshotStatus) status;
    }

    @Override
    public List<Protos.Resource> getReserveResources() {
        return Collections.emptyList();
    }

    @Override
    public List<Protos.Resource> getCreateResources() {
        return Collections.emptyList();
    }

    @Override
    public List<Protos.Resource> getLaunchResources() {
        return Arrays.asList(
                reservedCpus(cpus, role, principal),
                reservedMem(memoryMb, role, principal),
                reservedDisk(diskMb, role, principal));
    }
}