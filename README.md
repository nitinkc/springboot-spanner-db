# springboot-spanner-db


### Create Spanner table from Cloud Console

gcloud spanner databases ddl \
update spanner-db \
--instance=development-nc \
--ddl="$(<schema.ddl)"


[Follow this link for detailed description of Data migration](http://nitinkc.github.io/gcp/MySql-to-Spanner-migration/)


## Spanner

All Annotation are from Spanner

https://spring-gcp.saturnism.me/app-dev/cloud-services/databases/cloud-spanner

SqlFileUtil.java has a method getS6QLQuery, that takes in SQL query from path. This comes handy when the application is run 
as a jar file. At that time, the resources folder is not available. with Gradleboot run, the classpath is already set, so it runs, but gives 
file not found exception when run as jar file.]\6