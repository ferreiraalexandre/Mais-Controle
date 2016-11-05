package br.com.maisControle.exemploCriptografia;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ConvertMD5 {
public static String convertMD5(String senha) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("MD5");

  String salt= "123";
    
    BigInteger hash = new BigInteger(1, md.digest((salt+senha).getBytes()));
    String senhaMD5 = String.format("%32x", hash);
    return senhaMD5;
    
}

}