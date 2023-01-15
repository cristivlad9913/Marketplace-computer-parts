package post.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByowner_id(Long buyerID);

    Arrays findAllByPostSummary_IdOrPostSummary_OwnerId(Long postId, Long ownerId);
}
