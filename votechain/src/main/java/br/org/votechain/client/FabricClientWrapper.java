package br.org.votechain.client;

import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.security.CryptoSuite;

import br.org.votechain.network.CarregadorPerfilConeccao;
import br.org.votechain.participantes.Usuario;

public class FabricClientWrapper {
	private String org;
	private String userName;
	private HFClient hfClient;

	private FabricClientWrapper(String userName, String org) {
		this.org = org;
		this.userName = userName;
		init();
	}

	/**
	 * Return instance of FabricClientWrapper
	 *
	 * @param userName
	 * @param org
	 * @return FabricClientWrapper
	 * @throws Exception
	 */
	public static FabricClientWrapper getFabricClient(String userName, String org) {
		return new FabricClientWrapper(userName, org);
	}

	/**
	 *
	 */
	void init() {
		try {
			this.hfClient = HFClient.createNewInstance();
			CryptoSuite cryptoSuite = CryptoSuite.Factory.getCryptoSuite();
			this.hfClient.setCryptoSuite(cryptoSuite);
			Usuario userContext = CAClientWrapper.getUserContext(userName, org);
			this.hfClient.setUserContext(userContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Return an instance of Channel. The channel client provide various transaction
	 * functions
	 *
	 * @param channelName
	 * @return Channel
	 * @throws Exception
	 */
	public Channel getChannelClient(String channelName) throws Exception {
		Channel channel = hfClient.loadChannelFromConfig(channelName, CarregadorPerfilConeccao.getConfig());
		channel.initialize();
		return channel;
	}

	/**
	 * Return HFClient object
	 *
	 * @return HFClient
	 */
	public HFClient getHfClient() {
		return hfClient;
	}
}
