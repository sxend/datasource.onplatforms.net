#!/bin/bash

SCHEMA=$1

LOCATIONS=$2

PACKAGE=$3

RDB_USER=""
RDB_PASS=""

if [ ""${CIRCLECI} = "true" ]; then
    RDB_USER=ubuntu
    RDB_PASS=""
else
    RDB_USER=$(aws s3 cp s3://internal-storage.onplatforms.net/internal/datastore/rdb/user.name - )
    RDB_PASS=$(aws s3 cp s3://internal-storage.onplatforms.net/internal/datastore/rdb/user.pass - )
fi

sbt -Dflyway.schemas=${SCHEMA} \
    -Dsbt.SCHEMA=${SCHEMA} \
    -Dflyway.locations=${LOCATIONS} \
    -Dflyway.user=${RDB_USER} \
    -Dsbt.RDB_USER=${RDB_USER} \
    -Dflyway.password=${RDB_PASS} \
    -Dsbt.RDB_PASS=${RDB_PASS} \
    -Dsbt.PACKAGE=${PACKAGE} \
    flywayMigrate slick-gen