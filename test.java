import javax.crypto.*;
import javax.crypto.spec.*;

public class test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		byte[] key = { 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01 };
		byte[] in  = { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f };
		
		byte[] key_cbc = { 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01 };
		byte[] in_cbc  = { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
							0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
							0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
							0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f };
		byte[] iv_cbc  = { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f };
		
		short[] skey = { 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01 };
		short[] sin  = { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f };
		
		short[] skey_cbc = { 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01 };
		short[] sin_cbc  = { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
							0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
							0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
							0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f };
		short[] siv_cbc  = { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f };
		
		long[] time = new long[6];
		long start = 0, end = 0;
		System.out.println("ECB plaintext:");
		printHex(in);
		
		start = System.currentTimeMillis();
		custom_aes_ecb(sin, skey);
		end = System.currentTimeMillis();
		time[0] = end-start;
		start = System.currentTimeMillis();
		lib_aes_ecb(in, key);
		end = System.currentTimeMillis();
		time[1] = end-start;
		
		
		System.out.println("CBC plaintext:");
		printHex(in_cbc);
		start = System.currentTimeMillis();
		custom_aes_cbc(sin_cbc, skey_cbc, siv_cbc);
		end = System.currentTimeMillis();
		time[2] = end-start;
		start = System.currentTimeMillis();
		lib_aes_cbc(in_cbc, key_cbc, iv_cbc);
		end = System.currentTimeMillis();
		time[3] = end-start;
		
		System.out.println("CTR plaintext:");
		printHex(in_cbc);
		start = System.currentTimeMillis();
		custom_aes_ctr(sin_cbc, skey_cbc, siv_cbc);
		end = System.currentTimeMillis();
		time[4] = end-start;
		start = System.currentTimeMillis();
		lib_aes_ctr(in_cbc, key_cbc, iv_cbc);
		end = System.currentTimeMillis();
		time[5] = end-start;
		
		System.out.println("CUSTOM ECB COST TIME:  " + String.valueOf(time[0]));
		System.out.println("JAVA LIB ECB COST TIME:" + String.valueOf(time[1]));
		System.out.println("CUSTOM CBC COST TIME:  " + String.valueOf(time[2]));
		System.out.println("JAVA LIB CBC COST TIME:" + String.valueOf(time[3]));
		System.out.println("CUSTOM CTR COST TIME:  " + String.valueOf(time[4]));
		System.out.println("JAVA LIB CTR COST TIME:" + String.valueOf(time[5]));
	}
	
	public static void custom_aes_ecb(short[] in, short[] key)
	{
		aes a = new aes();
		
		System.out.println("CUSTOM AES_ECB START:");
		a.AES_init(key);
		short[] out_ecb = a.AES_ECB_encrypt(in);
		System.out.println("ECB encrypt:");
		printHex(out_ecb);
		
		short[] de_out_ecb = a.AES_ECB_decrypt(out_ecb);
		System.out.println("ECB decrypt:");
		printHex(de_out_ecb);
		System.out.println("=======================================");
	}
	
	public static void custom_aes_cbc(short[] in, short[] key, short[] iv)
	{
		aes a = new aes();
		
		System.out.println("CUSTOM AES_CBC START:");
		a.AES_init(key, iv);
		short[] out_cbc = a.AES_CBC_encrypt(in);
		System.out.println("CBC encrypt:");
		printHex(out_cbc);
		
		a.AES_init(key, iv);
		short[] de_in_cbc = a.AES_CBC_decrypt(out_cbc);
		System.out.println("CBC decrypt:");
		printHex(de_in_cbc);
		System.out.println("=======================================");
	}
	
	public static void custom_aes_ctr(short[] in, short[] key, short[] iv)
	{
		aes a = new aes();
		
		System.out.println("CUSTOM AES_CTR START:");
		a.AES_init(key, iv);
		short[] out_ctr = a.AES_CTR(in);
		System.out.println("CTR encrypt:");
		printHex(out_ctr);
		
		a.AES_init(key, iv);
		short[] de_out_ctr = a.AES_CTR(out_ctr);
		System.out.println("CTR decrypt:");
		printHex(de_out_ctr);
		System.out.println("=======================================");
	}
	
	public static void lib_aes_ecb(byte[] in, byte[] key) throws Exception
	{
		System.out.println("JAVA LIBRARY AES_ECB START:");
		SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(in);
		System.out.println("ECB encrypt:");
		printHex(encrypted);
		
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		byte[] decrypted = cipher.doFinal(encrypted);
		System.out.println("ECB decrypt:");
		printHex(decrypted);
		System.out.println("=======================================");
	}
	
	public static void lib_aes_cbc(byte[] in, byte[] key, byte[] iv) throws Exception
	{
		System.out.println("JAVA LIBRARY AES_CBC START:");
		SecretKeySpec skeySpec_cbc = new SecretKeySpec(key, "AES");
		Cipher cipher_cbc = Cipher.getInstance("AES/CBC/NoPadding");
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		cipher_cbc.init(Cipher.ENCRYPT_MODE, skeySpec_cbc, ivSpec);
		byte[] encrypted_cbc = cipher_cbc.doFinal(in);
		System.out.println("CBC encrypt:");
		printHex(encrypted_cbc);
		
		cipher_cbc.init(Cipher.DECRYPT_MODE, skeySpec_cbc, ivSpec);
		byte[] decrypted = cipher_cbc.doFinal(encrypted_cbc);
		System.out.println("CBC decrypt:");
		printHex(decrypted);
		System.out.println("=======================================");
	}
	
	public static void lib_aes_ctr(byte[] in, byte[] key, byte[] iv) throws Exception
	{
		System.out.println("JAVA LIBRARY AES_CTR START:");
		SecretKeySpec skeySpec_ctr = new SecretKeySpec(key, "AES");
		Cipher cipher_ctr = Cipher.getInstance("AES/CTR/NoPadding");
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		cipher_ctr.init(Cipher.ENCRYPT_MODE, skeySpec_ctr, ivSpec);
		byte[] encrypted_ctr = cipher_ctr.doFinal(in);
		System.out.println("CTR encrypt:");
		printHex(encrypted_ctr);
		
		cipher_ctr.init(Cipher.DECRYPT_MODE, skeySpec_ctr, ivSpec);
		byte[] decrypted = cipher_ctr.doFinal(encrypted_ctr);
		System.out.println("CTR decrypt:");
		printHex(decrypted);
		System.out.println("=======================================");
	}
	
	public static void printHex(short[] array)
	{
		for(int i = 0; i < array.length; i++)
			System.out.print(Integer.toHexString(array[i]));
		System.out.println();
	}
	
	public static void printHex(byte[] array)
	{
		for(int i = 0; i < array.length; i++)
			System.out.print(Integer.toHexString(mod(array[i],256)));
		System.out.println();
	}
	
	static int mod(int x, int y)
	{
	    int result = x % y;
	    if (result < 0)
	    {
	        result += y;
	    }
	    return result;
	}

}
