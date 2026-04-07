package com.srhdp.aggregatorservice.service;

import com.srhdp.user.UserInformation;
import com.srhdp.user.UserInformationRequest;
import com.srhdp.user.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userClient;

    public UserInformation getUserInformation(int userId) {
        var request = UserInformationRequest.newBuilder()
                .setUserId(userId)
                .build();
        return this.userClient.getUserInformation(request);
    }

}