package fmi.cloudcomputing.buyerservice.offer.presentation;

//Astea sunt toate argumente posibile pe care le poate pune in frontend la URL
// gen /offers?param1=val1,param2=val2, etc.
//Practic field-urile astea sunt 'where' in baza de date
public class OfferFilters {
    private Long postId;
    private Long ownerId;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
