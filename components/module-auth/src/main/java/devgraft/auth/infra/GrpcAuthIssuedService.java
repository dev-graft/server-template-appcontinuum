package devgraft.auth.infra;

import devgraft.auth.AuthIssueServiceProto;
import devgraft.auth.AuthIssueServiceProto.AuthIssueReply;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
public class GrpcAuthIssuedService extends devgraft.auth.AuthIssueGrpc.AuthIssueImplBase {

    @Override
    public void callMethod(final AuthIssueServiceProto.AuthIssueRequest request,
                           final StreamObserver<AuthIssueReply> responseObserver) {
        log.info("request IdentityToken: {}", request.getIdentityCode());
        final AuthIssueReply authIssueReply = AuthIssueReply.newBuilder()
                .setAccessToken("ACC-TEST")
                .setRefreshToken("REF-TEST")
                .build();

        responseObserver.onNext(authIssueReply);
        responseObserver.onCompleted();
    }
}
