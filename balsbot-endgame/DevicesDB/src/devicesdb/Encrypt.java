/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devicesdb;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Eduardo_Cruz
 */
public class Encrypt {
    private static final String ALGO = "AES";
    private byte[] keyValue;
    
    public Encrypt(String key){
        keyValue = key.getBytes();
    }
    
    public String encrypt(String data){
        String encryptedValue = null;
        try{
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = c.doFinal(data.getBytes());
            encryptedValue = new BASE64Encoder().encode(encVal);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
                return encryptedValue;
    }
    
    public String decryptData(String data){
        String decVal = null;
        
        try{
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decordedValue = new BASE64Decoder().decodeBuffer(data);
            byte[] decryptedValue = c.doFinal(decordedValue);
            decVal = new String(decryptedValue);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return decVal;
    }
    
    private Key generateKey() throws Exception{
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }
    
//    public static void main(String args[]){
//        
//    }
}
