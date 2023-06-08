CREATE TABLE SINGERS (
    SINGER_ID STRING(36) NOT NULL,
    FIRST_NAME STRING(1024),
    LAST_NAME STRING(1024),
    SINGER_INFO BYTES(MAX),
    CREATE_DATE_TIME_GMT TIMESTAMP NOT NULL OPTIONS (allow_commit_timestamp=true),
    UPDATE_DATE_TIME_GMT TIMESTAMP NOT NULL OPTIONS (allow_commit_timestamp=true),
    DELETE_DATE_TIME_GMT TIMESTAMP OPTIONS (allow_commit_timestamp=true),
    RECORD_VERSION INT64
) PRIMARY KEY (SINGER_ID);

CREATE TABLE SINGERS_HIST (
    SINGER_HIST_ID STRING(36) NOT NULL,
    SINGER_ID STRING(36) NOT NULL,
    FIRST_NAME STRING(1024),
    LAST_NAME STRING(1024),
    SINGER_INFO BYTES(MAX),
    CREATE_DATE_TIME_GMT TIMESTAMP NOT NULL OPTIONS (allow_commit_timestamp=true),
    UPDATE_DATE_TIME_GMT TIMESTAMP NOT NULL OPTIONS (allow_commit_timestamp=true),
    DELETE_DATE_TIME_GMT TIMESTAMP OPTIONS (allow_commit_timestamp=true),
    RECORD_VERSION INT64
) PRIMARY KEY(SINGER_ID,SINGER_HIST_ID);

CREATE TABLE ALBUMS (
    SINGER_ID STRING(36) NOT NULL,
    ALBUM_ID STRING(36) NOT NULL,
    ALBUM_TITLE STRING(MAX),
    CREATE_DATE_TIME_GMT TIMESTAMP NOT NULL OPTIONS (allow_commit_timestamp=true),
    UPDATE_DATE_TIME_GMT TIMESTAMP NOT NULL OPTIONS (allow_commit_timestamp=true),
    DELETE_DATE_TIME_GMT TIMESTAMP OPTIONS (allow_commit_timestamp=true),
    RECORD_VERSION INT64
) PRIMARY KEY (SINGER_ID,ALBUM_ID),
  INTERLEAVE IN PARENT SINGERS ON DELETE CASCADE;

CREATE TABLE ALBUMS_HIST (
    ALBUM_HIST_ID STRING(36) NOT NULL,
    SINGER_ID STRING(36) NOT NULL,
    ALBUM_ID STRING(36) NOT NULL,
    ALBUM_TITLE STRING(MAX),
    CREATE_DATE_TIME_GMT TIMESTAMP NOT NULL OPTIONS (allow_commit_timestamp=true),
    UPDATE_DATE_TIME_GMT TIMESTAMP NOT NULL OPTIONS (allow_commit_timestamp=true),
    DELETE_DATE_TIME_GMT TIMESTAMP OPTIONS (allow_commit_timestamp=true),
    RECORD_VERSION INT64
 ) PRIMARY KEY(SINGER_ID,ALBUM_ID,ALBUM_HIST_ID);

CREATE TABLE SONGS (
    SINGER_ID STRING(36) NOT NULL,
    ALBUM_ID STRING(36) NOT NULL,
    TRACK_ID STRING(36) NOT NULL,
    SONG_NAME STRING(MAX),
    CREATE_DATE_TIME_GMT TIMESTAMP NOT NULL OPTIONS (allow_commit_timestamp=true),
    UPDATE_DATE_TIME_GMT TIMESTAMP NOT NULL OPTIONS (allow_commit_timestamp=true),
    DELETE_DATE_TIME_GMT TIMESTAMP OPTIONS (allow_commit_timestamp=true),
    RECORD_VERSION INT64
) PRIMARY KEY (SINGER_ID,ALBUM_ID,TRACK_ID),
  INTERLEAVE IN PARENT ALBUMS ON DELETE CASCADE;

CREATE TABLE SONGS_HIST (
    SONGS_HIST_ID STRING(36) NOT NULL,
    SINGER_ID STRING(36) NOT NULL,
    ALBUM_ID STRING(36) NOT NULL,
    TRACK_ID STRING(36) NOT NULL,
    SONG_NAME STRING(MAX),
    CREATE_DATE_TIME_GMT TIMESTAMP NOT NULL OPTIONS (allow_commit_timestamp=true),
    UPDATE_DATE_TIME_GMT TIMESTAMP NOT NULL OPTIONS (allow_commit_timestamp=true),
    DELETE_DATE_TIME_GMT TIMESTAMP OPTIONS (allow_commit_timestamp=true),
    RECORD_VERSION INT64
) PRIMARY KEY (SINGER_ID,ALBUM_ID,TRACK_ID,SONGS_HIST_ID);

CREATE TABLE REF_DATA (
    CODE_ID STRING(MAX) NOT NULL,
    NUMERIC_VALUE INT64,
    DESCRIPTION STRING(MAX),--coming from Ref Display Class
    DISPLAY_NAME STRING(MAX),
    END_DATE TIMESTAMP,-- coming from RefDatabase class
    CREATE_RELEASE_NUMBER INT64,
    START_DATE TIMESTAMP,
    UPDATE_DATE_TIME_GMT TIMESTAMP OPTIONS (allow_commit_timestamp=true),
) PRIMARY KEY (CODE_ID) ;