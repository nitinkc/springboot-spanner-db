# springboot-spanner-db


### Create Spanner table from Cloud Console

gcloud spanner databases ddl \
update spanner-db \                                                                                                                                                           ─╯
--instance=development-nc \
--ddl="$(<schema.ddl)"
