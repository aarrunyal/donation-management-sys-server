#!/bin/sh

scp ./docker-compose.yml root@178.128.228.234:dms/docker-compose.yml

scp ./.env root@178.128.228.234:dms/.env
