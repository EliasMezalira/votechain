package br.org.votechain.participantes;

import java.util.Set;

import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.User;

public class Usuario implements User {

	protected String name;
	protected Set<String> roles;
	protected String account;
	protected String affiliation;
	protected Enrollment enrollment;
	protected String mspId;

	public String getName() {
		return getName();
	}

	public Set<String> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAffiliation() {
		// TODO Auto-generated method stub
		return null;
	}

	public Enrollment getEnrollment() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMspId() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public void setEnrollment(Enrollment enrollment) {
		this.enrollment = enrollment;
	}

	public void setMspId(String mspId) {
		this.mspId = mspId;
	}

}
