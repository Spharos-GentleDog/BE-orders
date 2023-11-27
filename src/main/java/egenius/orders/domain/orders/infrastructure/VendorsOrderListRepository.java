package egenius.orders.domain.orders.infrastructure;

import egenius.orders.domain.orders.entity.VendorsOrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendorsOrderListRepository extends JpaRepository<VendorsOrderList, Long>, VendorsOrderListRepositoryCustom {
    Boolean existsByOrderNumber(String orderNumber);

    @Query("select v from VendorsOrderList v " +
            "where v.userEmail = :userEmail " +
            "order by v.groupId desc " +
            "limit 1")
    VendorsOrderList findMaxGroupId(@Param("userEmail") String userEmail);

    @Query("select v from VendorsOrderList v " +
            "where v.userEmail = :userEmail " +
            "and v.groupId < :groupId " +
            "order by v.groupId desc " +
            "limit 1")
    VendorsOrderList findByNextGroupId(@Param("userEmail") String userEmail,
                                       @Param("groupId") Long groupId);

    List<VendorsOrderList> findByUserEmailAndOrderNumber(String userEmail, String orderNumber);

}
