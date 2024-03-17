package by.teachmeskills.mapper;

import by.teachmeskills.api.BucketResponse;
import by.teachmeskills.api.order.OrderResponse;
import by.teachmeskills.entity.Bucket;
import by.teachmeskills.entity.Order;

public class BucketMapper {
    public BucketResponse toResponse(Bucket bucket){
        BucketResponse bucketResponse = new BucketResponse();
        bucketResponse.setOrderId(bucket.getOrderId());
        bucketResponse.setProductId(bucket.getProductId());
        bucketResponse.setCount(bucket.getCount());
        return bucketResponse;
    }
}
