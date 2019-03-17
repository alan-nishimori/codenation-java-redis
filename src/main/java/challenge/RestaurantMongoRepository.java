package challenge;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantMongoRepository extends MongoRepository<RestaurantMongo, ObjectId> {

    @Query("{ location: { $geoWithin: { $geometry: ?0 } } }")
    List<RestaurantMongo> findAllByNeighborhood(GeoJson<?> geometry);
}
