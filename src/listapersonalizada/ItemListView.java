package listapersonalizada;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemListView implements Parcelable{
	  private String texto;
	    private int iconeRid;
	 
	    public ItemListView()
	    {
	    }
	 
	    
		@Override
		public int describeContents() {
			// TODO Auto-generated method stub
			return 0;
		}

		public ItemListView(Parcel in){
			texto=in.readString();
			iconeRid=in.readInt();
			
		}
		
		@Override
		public void writeToParcel(Parcel dest, int flags) {
			// TODO Auto-generated method stub
			
			dest.writeString(texto);
			dest.writeInt(iconeRid);
		}
		
		
		public static final Parcelable.Creator<ItemListView> CREATOR = new Parcelable.Creator<ItemListView>()
				{
				    public ItemListView createFromParcel(Parcel in)
				    {
				        return new ItemListView(in);
				    }
				    public ItemListView[] newArray(int size)
				    {
				        return new ItemListView[size];
				    }
				};
	    
	    public ItemListView(String texto, int iconeRid)
	    {
	        this.texto = texto;
	        this.iconeRid = iconeRid;
	    }
	 
	    public int getIconeRid()
	    {
	        return iconeRid;
	    }
	 
	    public void setIconeRid(int iconeRid)
	    {
	        this.iconeRid = iconeRid;
	    }
	 
	    public String getTexto()
	    {
	        return texto;
	    }
	 
	    public void setTexto(String texto)
	    {
	        this.texto = texto;
	    }


}
