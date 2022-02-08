CREATE TABLE Singers (
  singerId   INT64 NOT NULL,
  firstName  STRING(1024),
  lastName   STRING(1024),
  singerInfo BYTES(MAX),
) PRIMARY KEY (singerId);

CREATE TABLE Albums (
  singerId     INT64 NOT NULL,
  albumId      INT64 NOT NULL,
  albumTitle   STRING(MAX),
) PRIMARY KEY (singerId, albumId),
  INTERLEAVE IN PARENT Singers ON DELETE CASCADE;

CREATE TABLE Songs (
  singerId     INT64 NOT NULL,
  albumId      INT64 NOT NULL,
  trackId      INT64 NOT NULL,
  songName     STRING(MAX),
) PRIMARY KEY (singerId, albumId, trackId),
  INTERLEAVE IN PARENT Albums ON DELETE CASCADE;