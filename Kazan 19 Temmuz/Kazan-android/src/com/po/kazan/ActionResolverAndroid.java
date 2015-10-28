package com.po.kazan;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Locale;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class ActionResolverAndroid implements ActionResolver {
	Handler uiThread;
	public Context appContext;
	public PDFTools pdf;
	public String value;
	public String conMessage, driveMessage, driveNeg, drivePos, gpsMessage, gpsNeg, gpsPos;
	public CharSequence progressMessage;
	Location location;
	double lat, lng;
	
	String conNeg, conPos;
	public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;
    
    
	public ActionResolverAndroid(Context appContext) {
		uiThread = new Handler();
		this.appContext = appContext;
		pdf = new PDFTools();
	}



	/* 
	 * K˝sa s¸reli Toast gˆsterir, iÁine mesaj˝ yazman yeterli. 
	 * 
	 * */
	public void showShortToast(final CharSequence toastMessage) {
		uiThread.post(new Runnable() {
			public void run() {
				Toast.makeText(appContext, toastMessage, Toast.LENGTH_SHORT)
				.show();
			}
		});
	}

	/* 
	 * Uzun s¸reli Toast gˆsterir, iÁine mesaj˝ yazman yeterli. 
	 * 
	 * */
	public void showLongToast(final CharSequence toastMessage) {
		uiThread.post(new Runnable() {
			public void run() {
				Toast.makeText(appContext, toastMessage, Toast.LENGTH_LONG)
				.show();
			}
		});
	}

	/*
	 * Alert Box gˆsterir. Buna da ihtiyac˝m˝z yok ama dursun burda. 
	 * 
	 * */
	@Override
	public void showAlertBox(final String alertBoxTitle,
			final String alertBoxMessage, final String alertBoxButtonText) {
		uiThread.post(new Runnable() {
			public void run() {
				new AlertDialog.Builder(appContext)
				.setTitle(alertBoxTitle)
				.setMessage(alertBoxMessage)
				.setNeutralButton(alertBoxButtonText,
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int whichButton) {
					}
				}).create().show();
			}
		});
	}

	public String createAlanDialog() {

		uiThread.post(new Runnable() {
			public void run() {
				final AlertDialog.Builder builder = new AlertDialog.Builder(appContext);
				builder.setTitle("ARIKAZAN");
				
				String posit = "Tamam";
				String neg = "‹ptal";
				
				if(getDisplayLanguage().equals("Türkçe")){
					builder.setMessage("Her kat›n toplam alan›: ");
					
				}
				else{
					builder.setMessage("Total area of each floor: ");
					posit = "OK";
					neg = "Cancel";
				}
				// Use an EditText view to get user input.
				final EditText input = new EditText(appContext);
				input.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
				input.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_NUMBER_FLAG_SIGNED);
				input.setId(1);
				builder.setView(input);
				
				input.setOnEditorActionListener(new OnEditorActionListener() {
			        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
							value = input.getText().toString();

			            	try {
								writeAlanToFile(value);

							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			            }    
			            return false;
			        }
			    });
				
				builder.setPositiveButton(posit, new DialogInterface.OnClickListener() {
						
					@Override
					public void onClick(DialogInterface dialog, int whichButton) {
						value = input.getText().toString();
						try {
							writeAlanToFile(value);
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return;
					}
				});

				builder.setNegativeButton(neg, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						return;
					}
				});

				builder.create().show();

			}
		});       
		return value;

	}

	private void writeAlanToFile(String data) throws IOException {

		String path = "/data/data/com.po.kazan/files/alan.txt";

		OutputStream myOutput;
		try {
			myOutput = new BufferedOutputStream(new FileOutputStream(path,false));

			myOutput.write(data.getBytes()); 
			myOutput.flush();
			myOutput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String createSaveDialog() {

		uiThread.post(new Runnable() {
			public void run() {
				AlertDialog.Builder builder = new AlertDialog.Builder(appContext);
				builder.setTitle("ARIKAZAN");
				
				String posit = "Tamam";
				String neg = "‹ptal";
				
				if(getDisplayLanguage().equals("Türkçe")){
					builder.setMessage("Kay›t için isim giriniz: ");
					
				}else{
					builder.setMessage("Enter name for the record: ");
					posit = "OK";
					neg = "Cancel";
				}
					
				// Use an EditText view to get user input.
				final EditText input = new EditText(appContext);
				builder.setView(input);

				builder.setPositiveButton(posit, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int whichButton) {
						value = input.getText().toString();

						writeSaveToFile("#" + value + "\n");
						return;
					}
				});

				builder.setNegativeButton(neg, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						return;
					}
				});

				builder.create().show();

			}
		});       
		return value;

	}

	private void writeSaveToFile(String data) {


		String path = "/data/data/com.po.kazan/files/saveFinal.txt";

		OutputStream myOutput;
		try {
			myOutput = new BufferedOutputStream(new FileOutputStream(path,true));

			String tempData = data;

			myOutput.write(tempData.getBytes()); 
			myOutput.flush();
			myOutput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		FileInputStream fstream = null;
		try {
			fstream = new FileInputStream("/data/data/com.po.kazan/files/saveFinal.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Get the object of DataInputStream
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		try {
			while (br.readLine() != null)
				System.out.println("yazd›ktan sonra oku: "+br.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String createUnitPriceDialog(final String message, final String fileName) {

		uiThread.post(new Runnable() {
			public void run() {
				AlertDialog.Builder builder = new AlertDialog.Builder(appContext);
				builder.setTitle("ARIKAZAN");
				
				String posit = "Tamam";
				String neg = "‹ptal";
				
				if(getDisplayLanguage().equals("Türkçe")){

					builder.setMessage(message);
				}else {
				
					builder.setMessage(message);
					posit = "OK";
					neg = "Cancel";
				}
				// Use an EditText view to get user input.
				final EditText input = new EditText(appContext);
				input.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_NUMBER_FLAG_SIGNED);
				input.setId(1);
				builder.setView(input);
				
				

				builder.setPositiveButton(posit, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int whichButton) {
						value = input.getText().toString();
						try {
							writeUnitPriceToFile(value, fileName);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return;
					}
				});

				builder.setNegativeButton(neg, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						return;
					}
				});

				builder.create().show();

			}
		});       
		return value;

	}

	private void writeUnitPriceToFile(String data, String fileName) throws IOException {

		String path = "/data/data/com.po.kazan/files/"+fileName+".txt";

		OutputStream myOutput;
		try {
			myOutput = new BufferedOutputStream(new FileOutputStream(path,false));

			String tempData = data;

			myOutput.write(tempData.getBytes());
			myOutput.flush();
			myOutput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String createStoreyDialog() {

		uiThread.post(new Runnable() {
			public void run() {
				final AlertDialog.Builder builder = new AlertDialog.Builder(appContext);
				builder.setTitle("ARIKAZAN");
				
				String posit = "Tamam";
				String neg = "‹ptal";
				
				if(getDisplayLanguage().equals("Türkçe")){
					builder.setMessage("Lütfen kat say›s›n› giriniz: ");
					
				}
				else{
					builder.setMessage("Please enter number of floors: ");
					posit = "OK";
					neg = "Cancel";
				}
				// Use an EditText view to get user input.
				final EditText input = new EditText(appContext);
				input.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
				input.setInputType(InputType.TYPE_CLASS_NUMBER);
				input.setId(1);
				builder.setView(input);
				
				input.setOnEditorActionListener(new OnEditorActionListener() {
			        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
							value = input.getText().toString();

			            	try {
								writeStoreyToFile(value);

							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			            }    
			            return false;
			        }
			    });
				
				builder.setPositiveButton(posit, new DialogInterface.OnClickListener() {
						
					@Override
					public void onClick(DialogInterface dialog, int whichButton) {
						value = input.getText().toString();
						try {
							writeStoreyToFile(value);
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return;
					}
				});

				builder.setNegativeButton(neg, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						return;
					}
				});

				builder.create().show();

			}
		});       
		return value;

	}
	
	public void writeStoreyToFile(String data) throws IOException {

		String path = "/data/data/com.po.kazan/files/storey.txt";

		OutputStream myOutput;
		try {
			myOutput = new BufferedOutputStream(new FileOutputStream(path,false));
			myOutput.write(data.getBytes()); 
			myOutput.flush();
			myOutput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	

	public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
 
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;
             
            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        } 
        return TYPE_NOT_CONNECTED;
    }

	public String getValue(){
		return value;
	}


	/*
	 * Bu ihtiyac˝m˝z olursa dursun dediim bir metod. ›Áine ald˝˝ String'i browser'da aÁar. 
	 * 
	 * */
	@Override
	public void openUri(String uri) {
		Uri myUri = Uri.parse(uri);
		Intent intent = new Intent(Intent.ACTION_VIEW, myUri);
		appContext.startActivity(intent);
	}

	/*
	 * Ad˝ndan da anla˛˝laca˝ gibi PDF gˆsteren metodumuz. Sen sadece PDF url'si ver iÁine String olarak geri kalan˝ o halleder, indirir ve gˆsterir. 
	 * 
	 * */
	@Override
	public void displayPDF(String localUri) {
		pdf.showPDFUrl(appContext, localUri);
	}

	/*
	 * Bu fonksiyon son bilinen lokasyonu gˆsterir, kullanaca˝m˝z GPS'le Áal˝˛an bu deil ama. 
	 *  
	 * */
	public void getLoc(){

		LocationManager locationManager = (LocationManager) appContext.getSystemService(appContext.LOCATION_SERVICE);
		String locationProvider = LocationManager.GPS_PROVIDER;
		Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
		showLongToast(lastKnownLocation.getLatitude() + " " + lastKnownLocation.getLongitude());

	}

	/*
	 * ›Áine verdiin lat ve long deerleriyle haritay˝ ortalayarak gˆsterir. Title ve description parametreleriyle de bir marker olu˛turur. 
	 * 
	 * */

	public void openMap(double lat, double lng, String title, String description){
		Intent intent = new Intent(appContext, Map.class);
		intent.putExtra("latitude", lat);
		intent.putExtra("longtitude", lng);
		intent.putExtra("title", title);
		intent.putExtra("description", description);
		appContext.startActivity(intent);
	}

	/*
	 * Telefon dili T¸rkÁe ise "T¸rkÁe" dˆnd¸r¸r. 
	 * */
	public String getDisplayLanguage(){

		return Locale.getDefault().getDisplayLanguage();
	}

	/*
	 * Bunla ilgili problem var. Activity'de al˝yoruz lat ve long deerlerini ama tekrar buraya pass'leyemedim. 
	 * 
	 * */

	public String getLocationFromMap(){
		lat = 0;
		lng = 0;

		Intent intent = new Intent(appContext, MapLoc.class);
		intent.putExtra("lat", lat);
		intent.putExtra("long", lng);
		appContext.startActivity(intent);
		return lat + " " + lng;
	}

	/*
	 * GPS aÁ˝ksa GPS'ten, yoksa ˛ebekeden kullan˝c˝ lokasyonunu buluyoruz. Burada da aynen haritada ald˝˝m˝z
	 * lat lng'u tekrar buraya pass'leyememe sorunu ya˛ad˝m; ama kendi activity'si iÁinde gayet g¸zel Áal˝˛˝yor.
	 * Yine game objesi gˆndererek lat lng deerlerini alabilirsin diye d¸˛¸n¸yorum. 
	 * 
	 * */
	public String getUserLocationFromHardware() {

		LocationManager manager = (LocationManager) appContext.getSystemService(appContext.LOCATION_SERVICE );
		boolean statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);

		if(!statusOfGPS){
			displayGPSAlert();
			
		}

		Intent locationIntent = new Intent(appContext, AndroidGPSTrackingActivity.class);
		appContext.startActivity(locationIntent);
		return "";
	}

	/*
	 * 
	 * PDF'e dair her ˛ey bu inner class'˝n iÁinde. Senin bir ˛ey dei˛tirmene gerek yok. Sadece displayPdf metodunu Áa˝rman yeterli. 
	 * 
	 * 
	 * */

	private class PDFTools {

		private static final String GOOGLE_DRIVE_PDF_READER_PREFIX = "http://drive.google.com/viewer?url=";
		private static final String PDF_MIME_TYPE = "application/pdf";
		private static final String HTML_MIME_TYPE = "text/html";
		
		ConnectivityManager conMgr = (ConnectivityManager)appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();

		/**
		 * If a PDF reader is installed, download the PDF file and open it in a reader. 
		 * Otherwise ask the user if he/she wants to view it in the Google Drive online PDF reader.<br />
		 * <br />
		 * <b>BEWARE:</b> This method
		 * @param context
		 * @param pdfUrl
		 * @return
		 */
		public void showPDFUrl( final Context context, final String pdfUrl ) {
			if ( isPDFSupported( context ) ) {
				downloadAndOpenPDF(context, pdfUrl);
			} else {
				askToOpenPDFThroughGoogleDrive( context, pdfUrl );
			}
		}

		/**
		 * Downloads a PDF with the Android DownloadManager and opens it with an installed PDF reader app.
		 * @param context
		 * @param pdfUrl
		 */
		@TargetApi(Build.VERSION_CODES.GINGERBREAD)
		public void downloadAndOpenPDF(final Context context, final String pdfUrl) {
			// Get filename
			final String filename = pdfUrl.substring( pdfUrl.lastIndexOf( "/" ) + 1 );
			// The place where the downloaded PDF file will be put
			final File tempFile = new File( context.getExternalFilesDir( Environment.DIRECTORY_DOWNLOADS), filename );
			if ( tempFile.exists() ) {
				// If we have downloaded the file before, just go ahead and show it.
				openPDF( context, Uri.fromFile( tempFile ) );
				return;
			}
			
			int status = getConnectivityStatus(appContext);
			
			if (getDisplayLanguage().equals("Türkçe")){
				progressMessage = "Katalog indiriliyor... Lütfen bekleyiniz...";
			}else
				progressMessage = "Downloading, please wait...";

			if(status != TYPE_NOT_CONNECTED){
				// Show progress dialog while downloading
				uiThread.post(new Runnable() {
					public void run() {
						final ProgressDialog progress = ProgressDialog.show(context, "Ar›Kazan", progressMessage, true, true);
						// Create the download request
						DownloadManager.Request r = new DownloadManager.Request( Uri.parse( pdfUrl ) );
	
						r.setDestinationInExternalFilesDir( context, Environment.DIRECTORY_DOWNLOADS, filename );
						final DownloadManager dm = (DownloadManager) context.getSystemService( Context.DOWNLOAD_SERVICE );
	
						BroadcastReceiver onComplete = new BroadcastReceiver() {
							@Override
							public void onReceive(Context context, Intent intent) {
								if ( !progress.isShowing() ) {
									return;
								}
	
								context.unregisterReceiver( this );
	
								progress.dismiss();
								long downloadId = intent.getLongExtra( DownloadManager.EXTRA_DOWNLOAD_ID, -1 );
								Cursor c = dm.query( new DownloadManager.Query().setFilterById( downloadId ) );
	
								if ( c.moveToFirst() ) {
									int status = c.getInt( c.getColumnIndex( DownloadManager.COLUMN_STATUS ) );
									if ( status == DownloadManager.STATUS_SUCCESSFUL ) {
										openPDF( context, Uri.fromFile( tempFile ) );
									}
								}
								c.close();
							}
						};
	
	
						context.registerReceiver( onComplete, new IntentFilter( DownloadManager.ACTION_DOWNLOAD_COMPLETE ) );
	
						// Enqueue the request
						dm.enqueue( r );
					}
				});
			}else{
				conNeg = "‹ptal";
				conPos = "Ayarlara Git. ";
				
				conMessage = "Kataloglar› indirebilmek için internet ba€lant›s› gereklidir."
						+ "Lütfen internet ayarlar›n›z kontrol ediniz. ";
				
				if(!getDisplayLanguage().equals("Türkçe")){
					conMessage = "Internet connection is required in order to download the catalogues. "
							+ "Please check your connection.";
					conNeg = "Cancel";
					conPos = "Go to Settings";
				}
				uiThread.post(new Runnable() {
					public void run() {
						new AlertDialog.Builder( appContext )
						.setTitle( "Ar›Kazan")
						.setMessage( conMessage)
						.setNegativeButton( conNeg, null )
						.setPositiveButton( conPos, new OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {

								appContext.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
							}
						})
						.show();
					}
				});
			}
		}
		/**
		 * Show a dialog asking the user if he wants to open the PDF through Google Drive
		 * @param context
		 * @param pdfUrl
		 */
		public void askToOpenPDFThroughGoogleDrive( final Context context, final String pdfUrl ) {
			if (getDisplayLanguage().equals("Türkçe")){
				driveMessage = "Katalo€u Google Drive ile açmak ister misiniz?";
				driveNeg = "Hay›r";
				drivePos = "Evet";
			}else{
				driveMessage = "Do you want to open the catalogue with Google Drive?";
				driveNeg = "No";
				drivePos = "Yes";
				
			}
			uiThread.post(new Runnable() {
				public void run() {
					new AlertDialog.Builder( context )
					.setTitle( "Google Drive")
					.setMessage( driveMessage)
					.setNegativeButton( driveNeg, null )
					.setPositiveButton( drivePos, new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							openPDFThroughGoogleDrive(context, pdfUrl); 
						}
					})
					.show();
				}
			});
		}

		/**
		 * Launches a browser to view the PDF through Google Drive
		 * @param context
		 * @param pdfUrl
		 */
		public void openPDFThroughGoogleDrive(final Context context, final String pdfUrl) {
			Intent i = new Intent( Intent.ACTION_VIEW );
			i.setDataAndType(Uri.parse(GOOGLE_DRIVE_PDF_READER_PREFIX + pdfUrl ), HTML_MIME_TYPE );
			context.startActivity( i );
		}
		/**
		 * Open a local PDF file with an installed reader
		 * @param context
		 * @param localUri
		 */
		public final void openPDF(Context context, Uri localUri ) {
			Intent i = new Intent( Intent.ACTION_VIEW );
			i.setDataAndType( localUri, PDF_MIME_TYPE );
			context.startActivity( i );
		}
		/**
		 * Checks if any apps are installed that supports reading of PDF files.
		 * @param context
		 * @return
		 */
		public boolean isPDFSupported( Context context ) {
			Intent i = new Intent( Intent.ACTION_VIEW );
			final File tempFile = new File( context.getExternalFilesDir( Environment.DIRECTORY_DOWNLOADS ), "test.pdf" );
			i.setDataAndType( Uri.fromFile( tempFile ), PDF_MIME_TYPE );
			return context.getPackageManager().queryIntentActivities( i, PackageManager.MATCH_DEFAULT_ONLY ).size() > 0;
		}

	}

	@Override
	public void displayDisclaimer(final String disclaimer, final String positive, final String negative) {


		uiThread.post(new Runnable() {
			public void run() {
				AlertDialog.Builder builder = new AlertDialog.Builder(appContext);
				builder.setTitle("ARIKAZAN");
				builder.setMessage(disclaimer);

				builder.setPositiveButton(positive, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int whichButton) {

						return;
					}
				});

				builder.setNegativeButton(negative, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						return;
					}
				});

				builder.create().show();

			}
		});   

	}

	@Override
	public void displayGPSAlert() {
		if (getDisplayLanguage().equals("Türkçe")){
			gpsMessage =  "Uygulaman›n düzgün çal›ﬂabilmesi için konum servislerinden modu \"Yüksek Do€ruluk\""
					+ " konumuna getiriniz.";
			gpsNeg = "‹ptal";
			gpsPos = "Ayarlara git";
			
		}else{
			
			gpsMessage = "Please turn on your location services  to use the application properly.";
			gpsNeg = "Cancel";
			gpsPos = "Go to Settings";
		
		}
		uiThread.post(new Runnable() {
			public void run() {
				new AlertDialog.Builder( appContext )
				.setTitle( "Ar›Kazan")
				.setMessage(gpsMessage)
				.setNegativeButton( gpsNeg, null )
				.setPositiveButton( gpsPos, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

						final Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
						appContext.startActivity(intent);
					}
				})
				.show();
			}
		});
	}
}
