package by.teachmeskills.mapper;

import by.teachmeskills.api.bucket.BucketResponse;
import by.teachmeskills.entity.Bucket;

public class BucketMapper {
    public BucketResponse toResponse(Bucket bucket){
        BucketResponse bucketResponse = new BucketResponse();
        bucketResponse.setOrderId(bucket.getOrderId());
        bucketResponse.setProductId(bucket.getProductId());
        bucketResponse.setCount(bucket.getCount());
        return bucketResponse;
    }
}
