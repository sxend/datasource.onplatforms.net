import org.flywaydb.sbt.FlywayPlugin._

val RDB_HOST = Option(System.getProperty("sbt.RDB_HOST"))
  .orElse(Option(System.getenv("RDB_HOST"))).getOrElse("0.0.0.0")

val RDB_PORT = Option(System.getProperty("sbt.RDB_PORT"))
  .orElse(Option(System.getenv("RDB_PORT"))).getOrElse("3306")

val RDB_USER = Option(System.getProperty("sbt.RDB_USER"))
  .orElse(Option(System.getenv("RDB_USER"))).getOrElse("root")

val RDB_PASS = Option(System.getProperty("sbt.RDB_PASS"))
  .orElse(Option(System.getenv("RDB_PASS"))).getOrElse("")

val SCHEMAS = Option(System.getProperty("sbt.SCHEMAS"))
  .orElse(Option(System.getenv("SCHEMAS"))).getOrElse("")

val LOCATIONS = Option(System.getProperty("sbt.LOCATIONS"))
  .orElse(Option(System.getenv("LOCATIONS"))).getOrElse("")

flywayUrl := s"jdbc:mysql://$RDB_HOST:$RDB_PORT?useSSL=false"

flywayUser := RDB_USER

flywayPassword := RDB_PASS

flywaySchemas := SCHEMAS.split(",").toSeq

flywayLocations := LOCATIONS.split(",").toSeq
