package devgraft.member.infra;

import devgraft.auth.AuthIssueGrpc;
import devgraft.auth.AuthIssueServiceProto.AuthIssueReply;
import devgraft.auth.AuthIssueServiceProto.AuthIssueRequest;
import devgraft.member.domain.AuthIssueService;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class GrpcAuthIssueService implements AuthIssueService {
    @GrpcClient("auth")
    private AuthIssueGrpc.AuthIssueBlockingStub authIssueStub;

    @Override
    public AuthIssuedResponse issued(final String identityCode) {
        final AuthIssueRequest request = AuthIssueRequest.newBuilder()
                .setIdentityCode(identityCode).build();
        final AuthIssueReply authIssueReply = authIssueStub.callMethod(request);
        return AuthIssuedResponse.builder()
                .accessToken(authIssueReply.getAccessToken())
                .refreshToken(authIssueReply.getRefreshToken())
                .build();
    }
}
