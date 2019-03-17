package challenge;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Classe para mapear o restaurante no MongoDB
 *
 */
@Document(collection = "restaurant")
public class RestaurantMongo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId id;

    private GeoJsonPoint location;

    private String name;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public GeoJsonPoint getLocation() {
        return location;
    }

    public void setLocation(GeoJsonPoint location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RestaurantMongo{" +
                "id=" + id +
                ", location=" + location +
                ", name='" + name + '\'' +
                '}';
    }
}
