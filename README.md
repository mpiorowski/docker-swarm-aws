# Instruction on how to deploy a docker swarm on Amazon AWS
source tutorial: https://www.youtube.com/watch?v=Io3YMg6CTXA&t=245s

### Deploy docker swarm using AWS CloudFormation

1. Enter https://docs.docker.com/docker-for-aws/. 
2. Scroll down and click on "Deploy Docker Community Edition (CE) for AWS (stable)" link. This will forward you to AWS Cloud Formation Console.
3. Log into your AWS account.  
4. You will be redirected to AWS form, where You can specify all the important parameters of the docker stack, like numbers of swarm managers and workers node.
The important thing here is to set up Your ssh EC2 KeyPair. If You don't have it, create one by entering EC2 Dashboard and choosing Key Pairs link.
5. Follow the instruction and ine the end click "Create stack". The docker swarm will be set up automatically.


### Connect to your docker swarm 

In order to connect to newly created docker swarm, open ssh tunnel to aws serer using KeyPair selected during installation process.

```
ssh -i <path-to-ssh-key> -NL localhost:2374:/var/run/docker.sock docker@<ssh-host> &

docker -H localhost:2374 info
```
Now we can remotely run commands on the server.

### Install swarmpit

Swarmpit is a lightweight Docker Swarm management UI.

source repo:
https://github.com/swarmpit/swarmpit  

The swarmpit folder inside project contain a little modified docker-compose.yml file compared to the original.

To install the swarmpit one the Docker Stack, run these commands:
```
docker -H localhost:2374 stack deploy -c .swarmpit/docker-compose.yml swarm

docker -H localhost:2374 stack services swarm
```

### Access swarmpit interface

Enter AWS CloudFormation console and click on the created docker swarm. Navigate to Outputs tab, and copy the "DefaultDNSTarget".

This is the public url, which allows you to access your docker swarm.

Access http://<"DefaultDNSTarget">:8888

### Manual deployment
https://caylent.com/high-availability-docker-swarm-aws/