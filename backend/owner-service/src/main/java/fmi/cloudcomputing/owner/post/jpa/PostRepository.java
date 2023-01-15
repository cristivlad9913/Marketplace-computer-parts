package fmi.cloudcomputing.owner.post.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOwner_Id(Long buyerID);

//    Arrays findAllByPostSummary_IdOrPostSummary_OwnerId(Long postId, Long ownerId);
}
