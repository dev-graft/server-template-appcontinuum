package devgraft.auth.infra;

import devgraft.auth.AuthIssueServiceProto;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GrpcAuthIssuedService extends devgraft.auth.AuthIssueGrpc.AuthIssueImplBase {

    @Override
    public void callMethod(final AuthIssueServiceProto.AuthIssueRequest request,
                           final StreamObserver<AuthIssueServiceProto.AuthIssueReply> responseObserver) {

        // 인증 객체 생성
        // 인증 객체 저장
        // 인증 객체 반환

        super.callMethod(request, responseObserver);
    }
}
