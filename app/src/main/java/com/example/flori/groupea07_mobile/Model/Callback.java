package com.example.flori.groupea07_mobile.Model;

public interface Callback {

        void onPreExecute(String text);

        void onPostExecute(String result);

        void onManagerLoaded(String manager);
   }

