package Services;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class ListaUsuariosWs extends AsyncTask<String,Integer,Boolean> {
	public String[] clientes = null; 
	
    protected Boolean doInBackground(String... params) {
 
        boolean resul = true;
 
        HttpClient httpClient = new DefaultHttpClient();
        
        HttpGet put = new HttpGet("http://10.0.2.2:8080/SERVER-MODULE-SERVICES/restws/usuarios/listarUsuarios");
        put.setHeader("content-type", "application/json");
         
        try
        {
                HttpResponse resp = httpClient.execute(put);
                String respStr = EntityUtils.toString(resp.getEntity());
         
                JSONArray respJSON = new JSONArray(respStr);
                //JSONObject respJSON = new JSONObject(respStr);
                //JSONArray valarray = new JSONArray(respStr);
                
                clientes = new String[respJSON.length()];
                
                for(int i=0; i<respJSON.length(); i++)
                {
                    JSONObject obj = respJSON.getJSONObject(i);
         
                    String usuario = obj.getString("usuario");
                    String contrasenia = obj.getString("contrasenia");
                    String nombres = obj.getString("nombres");
                    String apellidos = obj.getString("apellidos");
                    
                    
         
                    clientes[i] = "" + usuario + "-" + contrasenia + "-" + nombres + "-" + apellidos;
                }
                   		
                
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
    
    public String [] obtenerUsuarios()
    {
    	return clientes;
    }
    
 

}