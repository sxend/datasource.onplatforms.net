# datasource.onplatforms.net

#### migration

```bash
sbt -Dflyway.schemas=accounts.onplatforms.net \
    -Dflyway.locations="schema/accounts" \
    -Dflyway.user=$(aws s3 cp s3://internal-storage.onplatforms.net/internal/datastore/rdb/user.name - ) \
    -Dflyway.password=$(aws s3 cp s3://internal-storage.onplatforms.net/internal/datastore/rdb/user.pass - ) \
    flywayMigrate
```

#### production migration
```bash
sbt -Dflyway.schemas=accounts.onplatforms.net \
    -Dflyway.locations="schema/accounts" \
    -Dflyway.user=$(aws s3 cp s3://internal-storage.onplatforms.net/internal/datastore/rdb/user.name - ) \
    -Dflyway.password=$(aws s3 cp s3://internal-storage.onplatforms.net/internal/datastore/rdb/user.pass - ) \
    -Dsbt.RDB_HOST=xxx.xxx.xxx.xxx -Dsbt.RDB_PORT=xxxxx \
    flywayMigrate
```
