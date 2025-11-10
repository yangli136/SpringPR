> README.md

# yugabyteDB Module Instruction
---

## Install yugabyteDB

[Install yugabyteDB Docker Image](https://docs.yugabyte.com/preview/tutorials/quick-start/docker/)

### get latest yugabyteDB Docker image:

```
docker pull yugabytedb/yugabyte:latest
```

### Create a ~/yb_data directory by executing the following command:

```
mkdir ~/yb_data
```
### Run Docker with the volume mount option by executing the following command:

```
docker run -d --name yugabyte \
         -p7000:7000 -p9000:9000 -p15433:15433 -p5433:5433 -p9042:9042 \
         -v ~/yb_data:/home/yugabyte/yb_data \
         yugabytedb/yugabyte:latest bin/yugabyted start \
         --base_dir=/home/yugabyte/yb_data \
         --background=false
```

```
docker exec -it yugabyte yugabyted status
```

## Install Cassandra

### get latest yugabyteDB Docker image:

[Install Cassandra Docker image](https://hub.docker.com/_/cassandra)

```
docker pull cassandra:latest
```

### Create a ~/cassandra_data directory by executing the following command:

```
mkdir ~/cassandra_data
```

### Start Cassandra

```
docker run --name cassandra-9142 -p 9042:9042 -v ~/cassandra_data:/var/lib/cassandra -d cassandra:latest
```

### Check Cassadra status

```
docker exec -it cassandra-9142 nodetool status
```

### Run Bash in Cassandra Container

```
docker exec -it cassandra-9142 bash
```

```
cqlsh -u cassandra -p cassandra
```

[Download Cqlsh](https://docs.datastax.com/en/dse/6.9/installing/cqlsh.html)

unzip then run

```
./cqlsh -u cassandra -p cassandra localhost 9142
```



## Install Couchbase

### get latest Couchbase Docker image:

[Install Couchbase Docker image](https://hub.docker.com/_/couchbase)

```
docker pull couchbase:latest
```

### Create a ~/couchbase_data directory by executing the following command:

```
mkdir ~/couchbase_data
```

### Start Couchbase

```
docker run --ulimit nofile=40960:40960 --ulimit core=100000000:100000000 --ulimit memlock=100000000:100000000 --name couchbase -v ~/couchbase_data:/opt/couchbase/var -p 8091-8097:8091-8097 -p 9123:9123 -p 11207:11207 -p 11210:11210 -p 11280:11280 -p 18091-18097:18091-18097 -d couchbase:latest
```

### Visit

```
http://localhost:8091
```




### Create Schema

#### Install Python3

[Python3 Installation]

#### pip


#### pip3 install jsonschema2db

```
pip3 install jsonschema2db
```


https://www.liquid-technologies.com/online-json-to-schema-converter
https://github.com/better/jsonschema2db/blob/master/test/test_pp_to_def.json
