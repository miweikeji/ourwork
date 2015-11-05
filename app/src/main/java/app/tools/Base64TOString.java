package app.tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Base64TOString {

//	1.将Bitmap用Base64转换成字符串
	public static String Bitmap2Byte(Bitmap bitmap) {
		
		String result = null;  
	    ByteArrayOutputStream baos = null; 
	    try {  
	        if (bitmap != null) {  
	            baos = new ByteArrayOutputStream();  
	            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);  
	            baos.flush();  
	            baos.close();  
	            byte[] bitmapBytes = baos.toByteArray();  
	            result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);  
	        }  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        try {  
	            if (baos != null) {  
	                baos.flush();  
	                baos.close();  
	            }  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    }  
	    return result;  
	}
	
	/** 
	 * base64转为bitmap 
	 * @param base64Data 
	 * @return 
	 */  
	public static Bitmap base64ToBitmap(String base64Data) {  
	    byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);  
	    return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);  
	}
}
