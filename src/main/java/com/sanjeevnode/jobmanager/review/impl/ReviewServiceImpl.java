package com.sanjeevnode.jobmanager.review.impl;

import com.sanjeevnode.jobmanager.company.Company;
import com.sanjeevnode.jobmanager.company.CompanyService;
import com.sanjeevnode.jobmanager.review.Review;
import com.sanjeevnode.jobmanager.review.ReviewRepository;
import com.sanjeevnode.jobmanager.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        return reviewRepository.findByCompanyId(companyId).stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReviewById(Long companyId, Long reviewId, Review review) {
        return reviewRepository.findByCompanyId(companyId).stream()
                .filter(r -> r.getId().equals(reviewId))
                .findFirst()
                .map(r -> {
                    r.setTitle(review.getTitle());
                    r.setDescription(review.getDescription());
                    r.setRating(review.getRating());
                    reviewRepository.save(r);
                    return true;
                }).orElse(false);
    }

    @Override
    public boolean deleteReviewById(Long companyId, Long reviewId) {
        return  reviewRepository.findByCompanyId(companyId).stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .map(review -> {
                    reviewRepository.delete(review);
                    return true;
                }).orElse(false);
    }
}
