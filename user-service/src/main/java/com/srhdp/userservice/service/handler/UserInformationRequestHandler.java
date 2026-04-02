package com.srhdp.userservice.service.handler;

import com.srhdp.user.UserInformation;
import com.srhdp.user.UserInformationRequest;
import com.srhdp.userservice.exception.UnknownUserException;
import com.srhdp.userservice.repository.PortfolioItemRepository;
import com.srhdp.userservice.repository.UserRepository;
import com.srhdp.userservice.util.EntityMessageMapper;
import org.springframework.stereotype.Service;

@Service
public class UserInformationRequestHandler {

    private final UserRepository userRepository;
    private final PortfolioItemRepository portfolioItemRepository;

    public UserInformationRequestHandler(UserRepository userRepository, PortfolioItemRepository portfolioItemRepository) {
        this.userRepository = userRepository;
        this.portfolioItemRepository = portfolioItemRepository;
    }

    public UserInformation getUserInformation(UserInformationRequest request) {
        var user = this.userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UnknownUserException(request.getUserId()));
        var portfolioItems = this.portfolioItemRepository.findAllByUserId(request.getUserId());
        return EntityMessageMapper.toUserInformation(user, portfolioItems);
    }


}
