import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public final class AESUtil {

    /** 固定密钥 */
    private static final String SECRET_KEY = "TEST_SECRET_STRING";

    /** 禁止实例化 */
    private AESUtil() {}

    /**
     * 加密
     *
     * @param plainText 明文
     * @return Base64(IV + CipherText)
     */
    public static String encryptData(String plainText) {
        try {
            // 1. 计算 32 byte 的 SHA-256 密钥
            byte[] keyBytes = sha256(SECRET_KEY);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

            // 2. 生成 16 byte 随机 IV
            byte[] iv = new byte[16];
            new SecureRandom().nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            // 3. AES/CBC/PKCS5Padding 加密
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] cipherText = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

            // 4. 组合 IV + CipherText
            byte[] ivAndCipher = new byte[iv.length + cipherText.length];
            System.arraycopy(iv, 0, ivAndCipher, 0, iv.length);
            System.arraycopy(cipherText, 0, ivAndCipher, iv.length, cipherText.length);

            // 5. Base64 编码返回
            return Base64.getEncoder().encodeToString(ivAndCipher);
        } catch (Exception e) {
            throw new RuntimeException("AES encrypt error", e);
        }
    }

    /**
     * 解密
     *
     * @param encrypted Base64(IV + CipherText)
     * @return 明文
     */
    public static String decryptData(String encrypted) {
        try {
            // 1. 计算 32 byte 的 SHA-256 密钥
            byte[] keyBytes = sha256(SECRET_KEY);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

            // 2. Base64 解码
            byte[] ivAndCipher = Base64.getDecoder().decode(encrypted);

            // 3. 提取 IV 与密文
            byte[] iv = new byte[16];
            byte[] cipherText = new byte[ivAndCipher.length - 16];
            System.arraycopy(ivAndCipher, 0, iv, 0, 16);
            System.arraycopy(ivAndCipher, 16, cipherText, 0, cipherText.length);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            // 4. 解密
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] plainBytes = cipher.doFinal(cipherText);

            return new String(plainBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("AES decrypt error", e);
        }
    }

    /** 计算 SHA-256 摘要 */
    private static byte[] sha256(String input) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static void main(String[] args) {
        String data = "18103049830";
        String enc = encryptData(data);

        System.out.println(enc);
        System.out.println(decryptData(enc));
    }

}
