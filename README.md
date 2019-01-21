# Instruction on how to deploy a docker swarm on Amazon AWS
source tutorial: https://www.youtube.com/watch?v=Io3YMg6CTXA&t=245s

### Deploy docker swarm using AWS CloudFormation

Enter https://docs.docker.com/docker-for-aws/deploy/ and click on "Deploy Docker Community Edition" link. 
This will forward you to AWS Cloud Formation Console. Follow the instruction there and the docker swarm will set up automatically.

### Open ssh tunnel to your docker manager on aws serer

```
ssh -i <path-to-ssh-key> -NL localhost:2374:/var/run/docker.sock docker@<ssh-host> &

docker -H localhost:2374 info
```

### Install swarmpit

source repo:
https://github.com/swarmpit/swarmpit  

The swarmpit folder contain a little modified docker-compose.yml file compared to the original.

```
docker -H localhost:2374 stack deploy -c .swarmpit/docker-compose.yml swarm

docker -H localhost:2374 stack services swarm
```

### Access swarmpit interface

Enter AWS CloudFormation console and click on created docker swarm. Navigate to Outputs tab, and copy the "DefaultDNSTarget".

This is the public url, which allows you to access your docker swarm.

Access http://<"DefaultDNSTarget">:8888

### Manual deployment
https://caylent.com/high-availability-docker-swarm-aws/