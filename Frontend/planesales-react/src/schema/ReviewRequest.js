class ReviewRequest {
    constructor(reviewObject = null, objectId = null, reviewText = null, reviewPicture = null, reviewRating = null) {
        this.reviewObject = reviewObject;
        this.objectId = objectId;
        this.reviewText = reviewText;
        this.reviewPicture = reviewPicture;
        this.reviewRating = reviewRating;
    }
}

export default ReviewRequest;
