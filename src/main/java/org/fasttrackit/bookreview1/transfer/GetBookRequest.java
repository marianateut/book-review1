package org.fasttrackit.bookreview1.transfer;

public class GetBookRequest {
    private String partialTitle;
    private String partialAuthor;

    //Wrapper class accepts so it will also accept null

    public String getpartialTitle() {
        return  partialTitle;
    }

    public void setpartialTitle(String  partialTitle) {
        this. partialTitle =  partialTitle;
    }

    public String getPartialAuthor() {
        return partialAuthor;
    }

    public void setPartialAuthor(String partialAuthor) {
        this.partialAuthor = partialAuthor;
    }

    @Override
    public String toString() {
        return "GetProductsRequest{" +
                " partialTitle='" +  partialTitle + '\'' +
                ", partialAuthor=" + partialAuthor +
                '}';
    }

}
