# springboot-spanner-db


### Create Spanner table from Cloud Console

```shell
#INSTANCE=development-nc
INSTANCE=cwow-spanner-lab-f8ef
DB=spanner-db

gcloud spanner databases ddl \
update $DB \
--instance= $INSTANCE \
--ddl="$(<schema.ddl)"

gcloud spanner databases ddl \
update spanner-db \
--instance=development-nc \
--ddl="$(<table.ddl)"

gcloud spanner databases execute-sql spanner-db --instance=development-nc \
--sql="$(<singers.sql)"

gcloud spanner databases execute-sql spanner-db --instance=development-nc \
--sql="$(<albums.sql)"

gcloud spanner databases execute-sql spanner-db --instance=development-nc \
--sql="$(<songs.sql)"
```

Or just run
 
zsh src/main/resources/db/db.sh

[Follow this link for detailed description of Data migration](http://nitinkc.github.io/gcp/MySql-to-Spanner-migration/)


## Spanner

All Annotation are from Spanner

https://spring-gcp.saturnism.me/app-dev/cloud-services/databases/cloud-spanner

SqlFileUtil.java has a method getS6QLQuery, that takes in SQL query from path. This comes handy when the application is run 
as a jar file. At that time, the resources folder is not available. with Gradleboot run, the classpath is already set, so it runs, but gives 
file not found exception when run as jar file.]\6