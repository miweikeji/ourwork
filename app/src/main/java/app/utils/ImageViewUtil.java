/*
 * Copyright 2013, Edmodo, Inc. 
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License.
 * You may obtain a copy of the License in the LICENSE file, or at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" 
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language 
 * governing permissions and limitations under the License. 
 */

package app.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;


public class ImageViewUtil {


	public static void writeBitmap(final Activity activity,
								   ICallbackUri<Uri> callback, final Bitmap bitmap) {
		new MyAsyncTask<Uri>(activity, callback) {
			Uri uri = Uihelper.getOutputMediaFileUri();
			@Override
			public Uri handler() {
				String tempFile = uri.toString();
				if (tempFile.startsWith("file://")) {
					tempFile = tempFile.substring("file://".length());
				}
				writeBitmap( tempFile, bitmap);
				return uri;
			}

			@Override
			public void doSuccessAfter() {
				if (activity == null
						|| (activity != null && !activity.isFinishing())) {
					activity.sendBroadcast(new Intent(
							Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
				}
			};
		}.execute();
	}
	public static void writeBitmap(String uri, Bitmap bitmap) {
		if (uri == null || bitmap == null) {
			throw new NullPointerException(
					"type == null || uri == null || bitmap == null");
		}

		try {
			FileOutputStream fos = null;
				boolean exist = isExistSDCard();
				if (!exist) {
					throw new RuntimeException("SD卡不存在");
				}
				fos = new FileOutputStream(createFile(uri));

			bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	private static File createFile(String uri) {
		File file = new File(uri);
		try {
			file.createNewFile();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return file;
	}
	public static boolean isExistSDCard() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}
	
	public static Bitmap readLocalBitmap(String uri) {
		try {
			if (uri == null) {
				throw new NullPointerException("uri == null");
			}
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(uri);
				bis = new BufferedInputStream(fis);
				BitmapFactory.Options options = setBitmapOption(uri);
				Bitmap bitmap = BitmapFactory.decodeStream(bis, null, options);
				if (bitmap == null) {
					throw new NullPointerException("bitmap == null");
				}
				return bitmap;
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				try {
					bis.close();
					fis.close();
				} catch (Exception e) {
				}
			}
		} catch (Error e) {
			throw new RuntimeException();
		}
	}

	public static Bitmap readNetworkBitmap(String imgUrl) {
		try {
			if (imgUrl == null) {
				throw new NullPointerException("uri == null");
			}
			InputStream fis = null;
			BufferedInputStream bis = null;
			try {
				URL url = new URL(imgUrl);

				fis = url.openConnection().getInputStream();
				bis = new BufferedInputStream(fis);
				BitmapFactory.Options options = setBitmapOption(imgUrl);
				Bitmap bitmap = BitmapFactory.decodeStream(bis, null, options);
				if (bitmap == null) {
					throw new NullPointerException("bitmap == null");
				}
				return bitmap;
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				try {
					bis.close();
					fis.close();
				} catch (Exception e) {
				}
			}
		} catch (Error e) {
			throw new RuntimeException();
		}
	}

	private static BitmapFactory.Options setBitmapOption(String filePath) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, opt);
		int outWidth = opt.outWidth;
		int outHeight = opt.outHeight;
		opt.inDither = false;
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inSampleSize = 1;
		int width = 500;
		int height = 500;
		if (outWidth != 0 && outHeight != 0) {
			int sampleSize = (outWidth / width + outHeight / height) / 2;
			opt.inSampleSize = sampleSize;
		}
		opt.inJustDecodeBounds = false;
		return opt;
	}

	public static Bitmap adjustOritation(String imgPath, Bitmap bm) {
		int digree = 0;
		ExifInterface exif = null;
		try {
			exif = new ExifInterface(imgPath);
		} catch (IOException e) {
			e.printStackTrace();
			exif = null;
		}
		if (exif != null) {
			// 读取图片中相机方向信息
			int ori = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
			// 计算旋转角度
			switch (ori) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				digree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				digree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				digree = 270;
				break;
			default:
				digree = 0;
				break;
			}
		}
		if (digree != 0) {
			// 旋转图片
			Matrix m = new Matrix();
			m.postRotate(digree);
			bm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), m, true);
		}
		return bm;
	}


	 public static byte[] readStream(InputStream inStream) throws Exception { 
	        byte[] buffer = new byte[1024]; 
	        int len = -1; 
	        ByteArrayOutputStream outStream = new ByteArrayOutputStream(); 
	        while ((len = inStream.read(buffer)) != -1) { 
	                 outStream.write(buffer, 0, len); 
	        } 
	        byte[] data = outStream.toByteArray(); 
	        outStream.close(); 
	        inStream.close(); 
	        return data; 

	   } 
	 
	    public static Bitmap getPicFromBytes(byte[] bytes, BitmapFactory.Options opts) { 
	        if (bytes != null) 
	            if (opts != null) 
	                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,opts); 
	            else 
	                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length); 
	        return null; 
	    } 
}
