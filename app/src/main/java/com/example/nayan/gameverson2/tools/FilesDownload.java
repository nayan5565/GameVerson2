package com.example.nayan.gameverson2.tools;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Nayan on 3/23/2017.
 */

public class FilesDownload {
    private static FilesDownload instance;
    private Context context;
    private String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "testDownload";
    private long total = 0, downloaded;
    private ProgressDialog dialog;
    private ArrayList<String> urls;

    public FilesDownload() {
    }

    public static FilesDownload getInstance(Context context, String dir) {
        if (instance == null) instance = new FilesDownload();
        instance.dir = dir;
        instance.context = context;
        return instance;
    }

    public void start() {
        if (urls != null && urls.size() > 0)
            new MyDownload().execute();
    }

    public FilesDownload addUrl(String url) {
        if (urls == null) urls = new ArrayList<>();
        if (!urls.contains(url)&&!isFileExists(url))
            urls.add(url);
        return instance;
    }
    private boolean isFileExists(String _url){
        String fileName = _url.substring(_url.lastIndexOf("/") + 1);
        File file=new File(dir+File.separator+fileName);
        File fDir=new File(dir);
        if(!fDir.isDirectory())
            fDir.mkdirs();
        return file.exists();
    }

    private class MyDownload extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            dialog = ProgressDialog.show(context, null, "0%");
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... params) {

            for (int i = 0; i < urls.size(); i++)
                totalFileSize(urls.get(i));

            Log.e("TEST", "Total Size :" + total + ":" + urls.size());
            for (int i = 0; i < urls.size(); i++)
                download(urls.get(i));


            return false;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            dialog.setMessage(values[0] + "%");
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            dialog.dismiss();

            if (result) {
            }

        }

        private void download(String _url) {
            int count = 0;
            try {
                URL url = new URL(_url);
                // downlod the file
                InputStream input = new BufferedInputStream(url.openStream());

                String fileName = _url.substring(_url.lastIndexOf("/") + 1);
                OutputStream output = new FileOutputStream(dir + File.separator + fileName);

                byte data[] = new byte[1024];

                while ((count = input.read(data)) != -1) {
                    output.write(data, 0, count);

                    // publishing the progress....
                    downloaded += count;
                    publishProgress((int) (downloaded * 100 / total));

                }
                output.flush();
                output.close();
                input.close();
            } catch (Exception e) {
            }
        }

        private void totalFileSize(String _url) {
            try {
                URL url = new URL(_url);
                URLConnection conn = url.openConnection();
                conn.connect();
                total += conn.getContentLength();
            } catch (Exception ex) {

            }


        }

    }
}
