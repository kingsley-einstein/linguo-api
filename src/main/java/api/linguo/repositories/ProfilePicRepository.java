package api.linguo.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.bson.types.ObjectId;

import api.linguo.models.ProfilePic;
import api.linguo.models.User;

@Repository
public interface ProfilePicRepository extends MongoRepository<ProfilePic, ObjectId> {
    ProfilePic findByOwner(User owner);
}