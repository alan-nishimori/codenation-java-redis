package challenge;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NeighborhoodMongoRepository extends MongoRepository<NeighborhoodMongo, ObjectId> {

    @Query("{ geometry: { $geoIntersects: { $geometry: { type: \"Point\", coordinates: [ ?0, ?1 ] } } } }")
    NeighborhoodMongo findNeighborhoodByPoint(double x, double y);
}
