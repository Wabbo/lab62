package wabbo.com.lab62;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/*** in    progress   out ***/
class MyAsyncTask extends AsyncTask < String , Void , Bitmap > {
    ImageView imageView ;
    Context context ;

    public MyAsyncTask(ImageView imageView ,Context context ) {
        this.imageView = imageView;
        this.context = context ;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap image = null ;

        image = downlod(strings[0]) ;

        return image;
    }

    private Bitmap downlod(String strURL) {
        Bitmap image = null ;
        URL url  ;
        HttpsURLConnection urlConnection ;
        InputStream inputStream = null ;
        //byte data [] = null ;

        try {
            url = new URL(strURL) ;
            urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.connect();
            if (urlConnection.getResponseCode()== HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream() ;
                image = BitmapFactory.decodeStream(inputStream) ;
                inputStream.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {

            /*if (inputStream != null){
                try {

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
        }
        return image ;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);
        //Toast.makeText(context, "Done ", Toast.LENGTH_SHORT).show();
    }
}
