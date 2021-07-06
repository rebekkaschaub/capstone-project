package de.neuefische.backend.repos;

import de.neuefische.backend.model.Review;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ReviewRepo extends PagingAndSortingRepository<Review, String> {

  List<Review> findByAuthor(String name);
  List<Review> findByCounselingCenterId(String id);
  boolean existsByCounselingCenterIdAndAuthor(String id, String Author);

}
