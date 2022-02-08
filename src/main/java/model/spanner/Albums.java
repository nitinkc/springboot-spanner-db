package model.spanner;

import com.google.cloud.spring.data.spanner.core.mapping.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = Albums.NAME )
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class Albums {
    public static final String NAME = "Albums";

    @PrimaryKey
    @Column(name = "albumId")
    private Integer albumId;

    @Interleaved
    @Column(name = "singerId")
    private List<Singers> singerId;

    @Column(name = "albumTitle")
    private String albumTitle;
}
