package com.dh.PI.repositories;

import com.dh.PI.model.Booking;
import com.dh.PI.model.Product;
import com.dh.PI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByProduct(Product product);

    List<Booking> findAllByUser(User user);

    @Query(nativeQuery = true, value = "SELECT * FROM tb_booking b WHERE product_id = (:id) AND (start_date BETWEEN (:dateStart) AND (:dateEnd)" +
            " OR end_date BETWEEN (:dateStart) AND (:dateEnd)) LIMIT 1")
    List<Booking> carReservations(@Param("dateStart") LocalDate dateStart, @Param("dateEnd") LocalDate dateEnd,
                                   @Param("id") Long id);

    @Query(nativeQuery = true, value = "SELECT * FROM tb_booking GROUP BY product_id " +
            "HAVING (start_date BETWEEN (:dateStart) AND (:dateEnd)) OR (end_date BETWEEN (:dateStart) AND (:dateEnd))")
    List<Booking> reservationsForSpecificDate(@Param("dateStart") LocalDate dateStart, @Param("dateEnd") LocalDate dateEnd);

}
