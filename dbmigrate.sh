#!/bin/bash

SCHEMAS=$1
LOCATIONS=$2

RDB_USER=""
RDB_PASS=""

if [ ""${CIRCLECI} = "true" ]; then
    RDB_USER=ubuntu
    RDB_PASS=""
else
    RDB_USER=$(aws s3 cp s3://internal-storage.onplatforms.net/internal/datastore/rdb/user.name - )
    RDB_PASS=$(aws s3 cp s3://internal-storage.onplatforms.net/internal/datastore/rdb/user.pass - )
fi

sbt -Dsbt.RDB_USER=${RDB_USER} \
    -Dsbt.RDB_PASS=${RDB_PASS} \
    -Dsbt.SCHEMAS=${SCHEMAS} \
    -Dsbt.LOCATIONS=${LOCATIONS} \
    flywayMigrate
