/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestApi;

import CapaNegocio.Detalle;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author dark-uriel
 */
public class ConsumeRestApi {
    String UrlPlanilla = "http://192.168.43.10:44366/api/Planilla/";
    String UrlDetalle = "http://192.168.43.10:44366/api/Detalle";
    PlanillaJson pls;
    public ConsumeRestApi() {
        pls = new PlanillaJson();
    }
    public boolean EnviarPlanilla(PlanillaPost planilla, DetalleJson[] detalle) throws UnsupportedEncodingException, IOException{
        HttpClient httpClient = new DefaultHttpClient();
        Gson gson = new Gson();
        HttpPost post = new HttpPost(UrlPlanilla);
        StringEntity postingString = new StringEntity(gson.toJson(planilla));
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        HttpResponse response = httpClient.execute(post);
        //////////////// Planilla Enviada //////////////////
        
        String responsestring = EntityUtils.toString( response.getEntity());
        
        //////Finalizar Conexion/////////
        EntityUtils.consume(response.getEntity());
        
        ////////////////// Convirtiendo ////
        Gson respuesta = new Gson();
        PlanillaJson pl = respuesta.fromJson(responsestring, PlanillaJson.class);
        int Id_Planilla = pl.getId_Planilla();
        return EnviarDetalle(Id_Planilla, detalle);
    }
    
    public boolean EnviarDetalle(int Id_Planilla, DetalleJson[] detalle) throws UnsupportedEncodingException, IOException{
        for (int i = 0; i < detalle.length; i++) {
            detalle[i].setId_Planilla(Id_Planilla);
        }
        boolean estado = false;
        for (int i = 0; i < detalle.length; i++) {
            HttpClient httpClient = new DefaultHttpClient();
            Gson gson = new Gson();
            HttpPost post = new HttpPost(UrlDetalle);
            StringEntity postingString = new StringEntity(gson.toJson(detalle[i]));
            post.setEntity(postingString);
            post.setHeader("Content-type", "application/json");
            HttpResponse response = httpClient.execute(post);
            if(response.getStatusLine().getStatusCode() == 200){
                estado = true;
            }else{
                estado = false;
            }
        }
        return estado;
    }
}
