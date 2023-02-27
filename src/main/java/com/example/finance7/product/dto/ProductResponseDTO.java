package com.example.finance7.product.dto;

import com.example.finance7.product.entity.*;
import com.example.finance7.product.vo.ProductResponseVO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
@Getter
@Setter
public class ProductResponseDTO {

    private Long productId;
    private String category;
    private String productName;
    private String companyName;
    private String companyImage;
    private String productURL;
    private String thumbnail;

    public ProductResponseDTO toDTO(Product product) {

        if (product instanceof Card){
            return CardResponseDTO.builder()
                    .category("card")
                    .productId(product.getProductId())
                    .productName(product.getProductName())
                    .companyName(product.getCompanyName())
                    .companyImage(product.getCompanyImage())
                    .productURL(product.getProductURL())
                    .thumbnail(product.getThumbnail())
                    .benefits(((Card) product).getBenefits().split("\\\\n"))
                    .annualFee(((Card) product).getAnnualFee())
                    .build();

        } else if (product instanceof Loan) {
            return LoanResponseDTO.builder()
                    .category("loan")
                    .productId(product.getProductId())
                    .productName(product.getProductName())
                    .companyName(product.getCompanyName())
                    .companyImage(product.getCompanyImage())
                    .productURL(product.getProductURL())
                    .thumbnail(product.getThumbnail())
                    .lowRate(((Loan) product).getLowRate())
                    .highRate(((Loan) product).getHighRate())
                    .bound(((Loan) product).getBound().split("\\\\n"))
                    .qualification(((Loan) product).getQualification().split("\\\\n"))
                    .build();

        } else if (product instanceof Savings) {

            return SavingResponseDTO.builder()
                    .category("savings")
                    .productId(product.getProductId())
                    .productName(product.getProductName())
                    .companyName(product.getCompanyName())
                    .companyImage(product.getCompanyImage())
                    .productURL(product.getProductURL())
                    .thumbnail(product.getThumbnail())
                    .basicRate(((Savings) product).getBasicRate())
                    .primeRate(((Savings) product).getPrimeRate())
                    .qualification(((Savings) product).getQualification().split("\\\\n"))
                    .aboutPrimeRate(((Savings) product).getAboutPrimeRate().split("\\\\"))
                    .build();
        }else {
            Subscription subscription = (Subscription) product;
            return SubscriptionResponseDTO.builder()
                    .productId(product.getProductId())
                    .category("subscription")
                    .productName(product.getProductName())
                    .companyName(product.getCompanyName())
                    .companyImage(product.getCompanyImage())
                    .productURL(product.getProductURL())
                    .thumbnail(product.getThumbnail())
                    .highRate(subscription.getHighRate())
                    .aboutRate(subscription.getAboutRate().split("\\\\n"))
                    .bound(subscription.getBound())
                    .purchase(subscription.getPurchase().split("\\\\n"))
                    .qualification(subscription.getQualification().split("\\\\n"))
                    .build();
        }
    }

}
