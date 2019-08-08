package de.cognicrypt.codegenerator.crysl.templates.digitalsigning;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

import de.cognicrypt.codegenerator.crysl.CrySLCodeGenerator;

public class Signer {
	
	public static KeyPair getKey() throws NoSuchAlgorithmException {
		java.security.KeyPair pair = null;
		CrySLCodeGenerator.getInstance().considerCrySLRule("java.security.KeyPairGenerator").addReturnObject(pair).generate();
		return pair;
	}

	public static byte[] sign(String msg, java.security.PrivateKey privKey)
			throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		byte[] msgBytes = msg.getBytes();
		byte[] res = null;
		CrySLCodeGenerator.getInstance().considerCrySLRule("java.security.Signature").addParameter(privKey, "initSign", 0).addParameter(msgBytes, "update", 0).addReturnObject(res).generate();
		return res;
	}

	public static boolean vfy(String msg, java.security.PublicKey pubKey)
			throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		boolean res = false;
		byte[] msgBytes = msg.getBytes();
		CrySLCodeGenerator.getInstance().considerCrySLRule("java.security.Signature").addParameter(pubKey, "initVerify", 0).addParameter(msgBytes, "verify", 0).addReturnObject(res).generate();
		return res;
	}

}
