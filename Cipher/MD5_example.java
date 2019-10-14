package mx.mycompany;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**

 * @author GMN
 */
public class MD5_example {
    static String PASS="The red Fox slow run cross";
    static Logger logger = Logger.getLogger(MD5_example.class.getName());

    public static void main(String[] argumentos) {
        try {
            //MessageDigest md = MessageDigest.getInstance("MD5");
            //MessageDigest md = MessageDigest.getInstance("SHA-1");
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] thedigest = md.digest( PASS.getBytes("UTF-8") );
            StringBuffer sb = new StringBuffer();
            //StringBuffer sb2 = new StringBuffer();
            for (int i = 0; i < thedigest.length; ++i) {
                sb.append(Integer.toHexString((thedigest[i] & 0xFF) | 0x100).substring(1, 3));
                //sb2.append(Integer.toHexString((thedigest[i] & 0xFF) | 0x100) );
            }
            logger.log(Level.INFO,"Algoritmo aplicado - "+md.getAlgorithm());
            logger.log(Level.INFO, "Cifrar: "+PASS+" - " + sb.toString());
            
        } catch (NoSuchAlgorithmException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
}
