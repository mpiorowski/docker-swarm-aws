deploy docker swarm on aws using aws cloudformation

- https://docs.docker.com/docker-for-aws/deploy/

open ssh tunel to docker manager on aws serwer

ssh -i <path-to-ssh-key> -NL localhost:2374:/var/run/docker.sock docker@<ssh-host> &
	
docker -H localhost:2374 info