package testPlug.plug;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by namrata on 07/10/16.
 */
public class RSAEncrypt {
    KeyPairGenerator kpg;
    KeyPair kp;
    PublicKey publicKey;
    PrivateKey privateKey;
    byte [] encryptedBytes,decryptedBytes;
    Cipher cipher,cipher1;
    String encrypted,decrypted;
    String name;

    public String send_public,send_private;
    public RSAEncrypt(String name){
        this.name=name;

    }

        public Key RSAEncrypt(String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {


            kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024);
            kp = kpg.genKeyPair();
            publicKey = kp.getPublic();
            privateKey = kp.getPrivate();
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            encryptedBytes = cipher.doFinal(name.getBytes());
       //     System.out.println("Enecrypted?????" + encryptedBytes);
            cipher1 = Cipher.getInstance("RSA");
            cipher1.init(Cipher.DECRYPT_MODE, privateKey);
            decryptedBytes = cipher1.doFinal(encryptedBytes);
            decrypted = new String(decryptedBytes);
         //   System.out.println("DDecrypted?????" + decrypted);

            if (key.equals("Public_key")) {
                return publicKey;
            } else if (key.equals("Private_Key")) {
                return privateKey;
            } else {
                return null;
            }

           // return publicKey;

        }


}
