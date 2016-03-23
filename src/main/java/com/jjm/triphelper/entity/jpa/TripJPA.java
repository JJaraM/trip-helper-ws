package com.jjm.triphelper.entity.jpa;

import com.jjm.triphelper.entity.spec.Trip;
import com.jjm.triphelper.entity.spec.User;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Trip")
public class TripJPA implements Trip {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="trip_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "trip_seq", sequenceName = "trip_seq")
    private Integer id;

    @Column(name="start_date", nullable = false) @Temporal(TemporalType.TIMESTAMP) private Date startDate;
    @Column(name="end_date", nullable = false) @Temporal(TemporalType.TIMESTAMP) private Date endDate;

    @ManyToOne(targetEntity = UserJPA.class, cascade = CascadeType.ALL) @JoinColumn(name="id_uzer")
    private User owner;

    @ManyToMany(mappedBy="shareTrips", targetEntity = UserJPA.class)
    private Set<User> friends;

    @Column(name = "place_location", nullable = false, length = 100) private String placeLocation;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public User getOwner() {
        return owner;
    }

    @Override
    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public Set<User> getFriends() {
        return friends;
    }

    @Override
    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    @Override
    public User addFriend(User user) {
        getFriends().add(user);
        return user;
    }

    @Override
    public User removeFriend(User user) {
        getFriends().remove(user);
        return user;
    }

    @Override
    public String getPlaceLocation() {
        return placeLocation;
    }

    @Override
    public void setPlaceLocation(String placeLocation) {
        this.placeLocation = placeLocation;
    }


}
