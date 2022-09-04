# Create Spanner Instance
#gcloud spanner instances create devevelopment-2\ --config=regional-us-central1 \
#--description="Spanner DB Instance" --nodes=1

# Create Database
#gcloud spanner databases create spanner-db --instance=devevelopment-2\

#Set the profile
gcloud config configurations activate lab

cd /src/main/resources/db

#Create Tables
gcloud spanner databases ddl \
update reports-poc \
--instance=devevelopment-2 \
--ddl="$(<table_ddl.sql)"

#Insert Data
gcloud spanner databases execute-sql reports-poc --instance=devevelopment-2 \
--sql="$(<data/singers.sql)"

gcloud spanner databases execute-sql reports-poc --instance=devevelopment-2 \
--sql="$(<data/albums.sql)"

gcloud spanner databases execute-sql reports-poc --instance=devevelopment-2 \
--sql="$(<data/songs.sql)"

gcloud spanner databases execute-sql reports-poc --instance=devevelopment-2 \
--sql="$(<data/refData.sql)"