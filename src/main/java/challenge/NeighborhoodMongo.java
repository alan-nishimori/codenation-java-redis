package challenge;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Classe para mapear o bairro no MongoDB
 *
 */
@Document(collection = "neighborhood")
public class NeighborhoodMongo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId id;

    private String name;

    private GeoJson<?> geometry;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GeoJson getGeometry() {
        return geometry;
    }

    public void setGeometry(GeoJson geometry) {
        this.geometry = geometry;
    }
}
