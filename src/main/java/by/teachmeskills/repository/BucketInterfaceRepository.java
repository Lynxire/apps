package by.teachmeskills.repository;

import by.teachmeskills.entity.Bucket;

import java.util.List;

public interface BucketInterfaceRepository {
    public Bucket add(Long orderId, Long productId, Long count);
    public void makeOrder(Long userId);
    List getBucketsByOrderId(List orderIds);
}
