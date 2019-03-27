package domain;

import javax.persistence.*;

@Embeddable
public class Route {
    @Column(name = "startLocation")
    @AttributeOverrides({@AttributeOverride(name = "latitude", column = @Column(name = "latitude_start")),
                         @AttributeOverride(name = "longitude", column = @Column(name = "longitude_start"))
                        })
    @Embedded
    private Location startLocation;
    @Column(name = "endLocation")
    @AttributeOverrides({@AttributeOverride(name = "latitude", column = @Column(name = "latitude_end")),
                         @AttributeOverride(name = "longitude", column = @Column(name = "longitude_end"))
                        })
    @Embedded
    private Location endLocation;

    public Route(){}
    public Route(Location startLocation, Location endLocation) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }
}
