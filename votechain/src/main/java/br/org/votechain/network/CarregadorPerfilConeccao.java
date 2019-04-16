package br.org.votechain.network;

import java.io.File;

import org.hyperledger.fabric.sdk.NetworkConfig;

public class CarregadorPerfilConeccao {
	private static final Integer lock = 0;
	private static CarregadorPerfilConeccao loadConnectionProfile = null;
	private static NetworkConfig config;

	/**
     * Constructor
     *
     * @throws Exception
     */
    private CarregadorPerfilConeccao() throws Exception {

        config = NetworkConfig.fromJsonFile(new File("/network/rede_conf.json"));

    }

	/**
	 * Get Certificate Authority config for an organization
	 *
	 * @param org
	 * @return CAInfo
	 * @throws Exception
	 */
	public static NetworkConfig.CAInfo getCaInfo(String org) throws Exception {
		if (config == null) {
			getInstance();
		}
		// assuming there is only one ca per organisation
		return config.getOrganizationInfo(org).getCertificateAuthorities().get(0);
	}

	/**
	 * Get organization config
	 *
	 * @param org
	 * @return OrgInfo
	 * @throws Exception
	 */
	public static NetworkConfig.OrgInfo getOrgInfo(String org) throws Exception {
		if (config == null) {
			getInstance();
		}
		return config.getOrganizationInfo(org);
	}

	/**
	 * Return class instance
	 *
	 * @return LoadConnectionProfile
	 * @throws Exception
	 */
	public static CarregadorPerfilConeccao getInstance() throws Exception {

		synchronized (lock) {
			if (loadConnectionProfile == null) {
				loadConnectionProfile = new CarregadorPerfilConeccao();
			}
		}
		return loadConnectionProfile;
	}

	/**
	 * Return the complete configuartion info
	 *
	 * @return NetworkConfig
	 * @throws Exception
	 */
	public static NetworkConfig getConfig() throws Exception {
		if (config == null) {
			getInstance();
		}
		return config;
	}
}
