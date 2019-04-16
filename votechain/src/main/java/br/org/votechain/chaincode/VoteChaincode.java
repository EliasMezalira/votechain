package br.org.votechain.chaincode;

import java.util.List;

import org.hyperledger.fabric.shim.ChaincodeBase;
import org.hyperledger.fabric.shim.ChaincodeStub;

public class VoteChaincode extends ChaincodeBase {
	private static final int ARGS_SIZE = 2;

	@Override
	public Response init(ChaincodeStub stub) {
		try {
			// Recupera os parametros
			List<String> args = stub.getStringArgs();
			if (args.size() != ARGS_SIZE) {
				newErrorResponse("Paramentros faltando");
			}
			stub.putStringState(args.get(0), args.get(1));

			return newSuccessResponse();
		} catch (Throwable e) {
			return newErrorResponse("Erro para definir asset");
		}
	}

	@Override
	public Response invoke(ChaincodeStub stub) {
		try {
			List<String> param = stub.getParameters();
			String metodo = stub.getFunction();
			if (metodo.equals("set")) {
				return newSuccessResponse(set(stub, param));
			} else if (metodo.equals("get")) {
				return newSuccessResponse(get(stub, param));
			}
			return newErrorResponse("Erro ao invocar a função. Expecting one of: [\"set\", \"get\"");
		} catch (Throwable e) {
			return newErrorResponse(e.getMessage());
		}
	}

	private String get(ChaincodeStub stub, List<String> param) {
		if (param.size() != ARGS_SIZE) {
			throw new RuntimeException("Argumentos invalidos");
		}
		String value = stub.getStringState(param.get(0));
		if (value == null || value.isEmpty()) {
			throw new RuntimeException("Asset not found: " + param.get(0));
		}
		return value;
	}

	private String set(ChaincodeStub stub, List<String> param) {
		if (param.size() != ARGS_SIZE) {
			throw new RuntimeException("Argumentos invalidos");
		}
		stub.putStringState(param.get(0), param.get(1));
		return param.get(1);
	}
	
	public static void main(String[] args) {
        new VoteChaincode().start(args);
    }

}
