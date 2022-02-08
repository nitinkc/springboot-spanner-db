# Create Spanner Instance
#gcloud spanner instances create devevelopment-2\ --config=regional-us-central1 \
#--description="Spanner DB Instance" --nodes=1

# Create Database
#gcloud spanner databases create spanner-db --instance=devevelopment-2\

gcloud spanner databases ddl \
update spanner-db \
--instance=devevelopment-2 \
--ddl="$(<table.ddl)"

gcloud spanner databases execute-sql spanner-db --instance=devevelopment-2 \
--sql="$(<singers.sql)"

gcloud spanner databases execute-sql spanner-db --instance=devevelopment-2 \
--sql="$(<albums.sql)"

gcloud spanner databases execute-sql spanner-db --instance=devevelopment-2 \
--sql="$(<songs.sql)"