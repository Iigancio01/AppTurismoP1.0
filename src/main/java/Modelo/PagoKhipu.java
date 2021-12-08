/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


import com.khipu.ApiClient;
import com.khipu.api.client.PaymentsApi;
import com.khipu.api.model.PaymentsCreateResponse;
import java.util.HashMap;
import java.util.Map;
import com.khipu.ApiException;


/**
 *
 * @author avata
 */
public class PagoKhipu {
   
    UrlKhipu url = new UrlKhipu();
    
    String pure; 
    
    String prueba;

    public  PaymentsCreateResponse Pagar(String NombreT, double monto) throws ApiException{
       
            int receiverId = 405842;
            String secretKey ="2c06ba672675be06d446635096be12d08873c3e6";
            Double montoD = Double.valueOf(monto);

            ApiClient apiClient = new ApiClient();
            apiClient.setKhipuCredentials(receiverId, secretKey);
            apiClient.setPlatform("demo-client", "2.0");
            // apiClient.setDebugging(true);
            PaymentsApi paymentsApi = new PaymentsApi();
            paymentsApi.setApiClient(apiClient);

            Map<String, Object> options = new HashMap<>();
            options.put("transactionId", "MTI-100");
            options.put("returnUrl", "http://localhost:8080/AppTurismoP/ControladorVistaCliente?accion=DepartamentoSesion");
            options.put("cancelUrl", "http://localhost:8080/AppTurismoP/ControladorVistaCliente?accion=DepartamentoSesion");
            options.put("pictureUrl", "");
            options.put("notifyUrl", "http://mi-ecomerce.com/backend/notify");
            options.put("notifyApiVersion", "1.3");

            PaymentsCreateResponse responses;

                responses = paymentsApi.paymentsPost(NombreT //Motivo de la compra
                        , "CLP" //Monedas disponibles CLP, USD, ARS, BOB
                        , monto //Monto
                        , options //campos opcionales
                );

                System.out.println(responses);
                
                pure=responses.getPaymentUrl();
                System.out.println("Variable: "+pure);
                
                url.setUrlPago(pure);
                
                prueba=url.getUrlPago();
                 
                System.out.println("prueba get"+prueba);
       
               return responses;
    }

   
        
                                
}
