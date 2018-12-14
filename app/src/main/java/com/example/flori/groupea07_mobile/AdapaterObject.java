package com.example.flori.groupea07_mobile;

        import android.content.Context;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;

        import com.example.flori.groupea07_mobile.Model.Auctioned_object;

public class AdapaterObject extends ArrayAdapter<Auctioned_object> {
  private int layoutResourceId;

  private static final String LOG_TAG = "MemoListAdapter";

  public AdapaterObject(Context context, int textViewResourceId){
      super(context, textViewResourceId);
      layoutResourceId = textViewResourceId;
  }

  @Override
    public View getView(int position, View convertView, ViewGroup parent){
      try{
          Auctioned_object item = getItem(position);
          View v = null;
          if(convertView == null){
              LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

              v = inflater.inflate(layoutResourceId, null);
          } else {
              v = convertView;
          }

          TextView nameObject = (TextView) v.findViewById(R.id._tv_name_object);
          TextView priceObject = (TextView) v.findViewById(R.id._tv_price_object);

          nameObject.setText(item.getNameObject());
          priceObject.setText(item.getPriceObject());

          return v;
      } catch (Exception ex){
          Log.e(LOG_TAG, "error", ex);
          return null;
      }
  }

}
