class ReviewResponse {
    constructor(reviewObject = null, objectId = null, reviewText = null, reviewRating = null) {
        this.reviewObject = reviewObject;
        this.objectId = objectId;
        this.reviewText = reviewText;
        this.reviewRating = reviewRating;
    }
}

export default ReviewResponse;

