package testPlug.plug;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import testPlug.plug.RSAEncrypt;


/**
 * This class echoes a string called from JavaScript.
 */
public class firstPlug extends CordovaPlugin {

    Key public_key,private_key;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }
        return false;
    }

    private void coolMethod(String message, final CallbackContext callbackContext) {
        final JSONObject jsonObject=new JSONObject();


            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                       RSAEncrypt rsa=new RSAEncrypt("Public_key");
                        public_key=rsa.RSAEncrypt("Public_key");
                        jsonObject.put("publickey",public_key);

                        private_key=rsa.RSAEncrypt("Private_Key");
                        jsonObject.put("privatekey",private_key);

                        byte[] data = "test".getBytes("UTF8");
                        //UUID.randomUUID();

                        Signature instance = Signature.getInstance("SHA256withRSA");
                        instance.initSign((PrivateKey) private_key);
                        instance.update(data);
                        byte[] signature = instance.sign();

                        jsonObject.put("signature",signature);

                        String json=jsonObject.toString();

                        //          System.out.println("JSON :----->"+json);

                        callbackContext.success(json);

                    } catch (SignatureException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (BadPaddingException e) {
                        e.printStackTrace();
                    } catch (InvalidKeyException e) {
                        e.printStackTrace();
                    } catch (NoSuchPaddingException e) {
                        e.printStackTrace();
                    } catch (IllegalBlockSizeException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
    }
}
