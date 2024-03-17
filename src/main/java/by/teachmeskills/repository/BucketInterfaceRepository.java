package by.teachmeskills.repository;

import by.teachmeskills.entity.Bucket;

public interface BucketInterfaceRepository {
    public Bucket add(Long orderId, Long productId, Long count);
    public void makeOrder(Long userId);
}
