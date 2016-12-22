# datasource.onplatforms.net

#### migration

```bash
sbt -Dsbt.SCHEMAS=accounts.onplatforms.net \
    -Dsbt.LOCATIONS="schema/accounts" \
    -Dsbt.RDB_USER=$(aws s3 cp s3://internal-storage.onplatforms.net/internal/datastore/rdb/user.name - ) \
    -Dsbt.RDB_PASS=$(aws s3 cp s3://internal-storage.onplatforms.net/internal/datastore/rdb/user.pass - ) \
    flywayMigrate
```

#### production migration
```bash
sbt -Dsbt.SCHEMAS=accounts.onplatforms.net \
    -Dsbt.LOCATIONS="schema/accounts" \
    -Dsbt.RDB_USER=$(aws s3 cp s3://internal-storage.onplatforms.net/internal/datastore/rdb/user.name - ) \
    -Dsbt.RDB_PASS=$(aws s3 cp s3://internal-storage.onplatforms.net/internal/datastore/rdb/user.pass - ) \
    -Dsbt.RDB_HOST=xxx.xxx.xxx.xxx -Dsbt.RDB_PORT=xxxxx \
    flywayMigrate
```
#### code generate

```bash
sbt -Dsbt.RDB_USER=$(aws s3 cp s3://internal-storage.onplatforms.net/internal/datastore/rdb/user.name - ) \
    -Dsbt.RDB_PASS=$(aws s3 cp s3://internal-storage.onplatforms.net/internal/datastore/rdb/user.pass - ) \
    slick-gen
```

#### migrate and packaging

```bash
sbt -Dsbt.RDB_USER=$(aws s3 cp s3://internal-storage.onplatforms.net/internal/datastore/rdb/user.name - ) \
    -Dsbt.RDB_PASS=$(aws s3 cp s3://internal-storage.onplatforms.net/internal/datastore/rdb/user.pass - ) \
    slick-gen publishLocal
```