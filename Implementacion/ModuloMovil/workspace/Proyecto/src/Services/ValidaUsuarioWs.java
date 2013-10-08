package Services;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class ValidaUsuarioWs extends AsyncTask<String,Integer,Boolean> {
	 
    protected Boolean doInBackground(String... params) {
 
        boolean resul = true;
 
        HttpClient httpClient = new DefaultHttpClient();
        
        HttpPut put = new HttpPut("http://10.0.2.2:2731/Api/Clientes/Cliente");
        put.setHeader("content-type", "application/json");
         
        try
        {
            //Construimos el objeto cliente en formato JSON
            JSONObject dato = new JSONObject();
         
            dato.put("NombreUsuario", "Andres");
            dato.put("Password", "Andres");
         
            StringEntity entity = new StringEntity(dato.toString());
            put.setEntity(entity);
         
                HttpResponse resp = httpClient.execute(put);
                String respStr = EntityUtils.toString(resp.getEntity());
         
               if(respStr.equals("true"))
                    resul = true;
               else
            	   resul = false;
        }
        catch(Exception ex)
        {
                Log.e("ServicioRest","Error!", ex);
        }
 
        return resul;
    }
    
    protected void onPostExecute(Boolean result) {
    	 
//        if (result)
//        {
//            lblResultado.setText("Insertado OK.");
//        }
    }
 

}