# Instruction on how to deploy docker swarm on Amazon AWS

1) Deploy docker swarm using AWS CloudFormation

Enter https://docs.docker.com/docker-for-aws/deploy/ and click on "Deploy Docker Community Edition" link. 
This will forward you to AWS Cloud Formation Console. Follow the instruction there and the docker swarm will set up automatically.

2) Open ssh tunnel to your docker manager on aws serer

ssh -i <path-to-ssh-key> -NL localhost:2374:/var/run/docker.sock docker@<ssh-host> &
	
docker -H localhost:2374 info