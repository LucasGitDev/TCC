package ga.appsmartwallet.smartwallet.Listas;


import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import ga.appsmartwallet.smartwallet.Adapters.AdapterItem;
import ga.appsmartwallet.smartwallet.Modelos.ModeloItem;
import ga.appsmartwallet.smartwallet.R;

public class BuyFragment extends Fragment {


    String codHex;

    RecyclerView recyclerView;
    ArrayList<ModeloItem> modeloItemArrayList = new ArrayList<>();
    AdapterItem adapterFeed;
    View view;
    ModeloItem modeloItem;


//    conexao bt

    static TextView statusMessage;
    static TextView counterMessage;
    ConnectionThread connect;

//    webservice

    public static final int CONNECTION_TIMEOUT = 30000;
    public static final int READ_TIMEOUT = 15000;

    public BuyFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_buy, container, false);



        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewLista);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapterFeed = new AdapterItem(getContext(), modeloItemArrayList);
        recyclerView.setAdapter(adapterFeed);



        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (btAdapter == null) {
//            statusMessage.setText("Que pena! Hardware Bluetooth não está funcionando :(");
            Toast.makeText(getContext(), "Que pena! Hardware Bluetooth não está funcionando :(", Toast.LENGTH_SHORT).show();
        } else {
//            statusMessage.setText("Ótimo! Hardware Bluetooth está funcionando :)");
            Toast.makeText(getContext(), "Ótimo! Hardware Bluetooth está funcionando :)", Toast.LENGTH_SHORT).show();
        }

        btAdapter.enable();

        connect = new ConnectionThread("20:16:04:25:64:78");
        connect.start();

        /* Um descanso rápido, para evitar bugs esquisitos.
         */
        try {
            Thread.sleep(1000);
        } catch (Exception E) {
            E.printStackTrace();
        }


//        populateRecyclerView();


//        new AsyncFetch("76 8d f2 08").execute();

        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
//    public void retira(){
//
//        modeloItemArrayList.remove(modeloItem);
//        adapterFeed.notifyDataSetChanged();
//    }

//    public void populateRecyclerView() {
//
//        double a = 22222222.5555;
//
//         modeloItem= new ModeloItem(2,"Detergente Ype", "Detegente", "", a, R.drawable.ypeneutro500ml);
//        modeloItemArrayList.add(modeloItem);
//
//        modeloItem= new ModeloItem(1,"Papel Higienico Neve", "Papel higienico de gente rica", "", a, R.drawable.pneve);
//        modeloItemArrayList.add(modeloItem);
//
//
//
//
//        adapterFeed.notifyDataSetChanged();
//
//    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("String", "Resume");
//        retira();
    }

    @Override
    public void onPause() {
        super.onPause();
        modeloItemArrayList.clear();
//        adapterFeed.notifyDataSetChanged();
        Log.i("String", "Pause");
//        retira();
    }

    public  final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            /* Esse método é invocado na Activity principal
                sempre que a thread de conexão Bluetooth recebe
                uma mensagem.
             */
            Bundle bundle = msg.getData();
            byte[] data = bundle.getByteArray("data");
            String dataString = new String(data);

            /* Aqui ocorre a decisão de ação, baseada na string
                recebida. Caso a string corresponda à uma das
                mensagens de status de conexão (iniciadas com --),
                atualizamos o status da conexão conforme o código.
             */
            if (dataString.equals("---N")){
//                statusMessage.setText("Ocorreu um erro durante a conexão D:");
                Toast.makeText(getContext(), "Ocorreu um erro durante a conexão D:", Toast.LENGTH_LONG).show();

            }else if (dataString.equals("---S")) {
//                statusMessage.setText("Conectado :D");
                Toast.makeText(getContext(), "Conectado :D", Toast.LENGTH_LONG).show();
                Log.i("String", "bt conect");
            } else {

                /* Se a mensagem não for um código de status,
                    então ela deve ser tratada pelo aplicativo
                    como uma mensagem vinda diretamente do outro
                    lado da conexão. Nesse caso, simplesmente
                    atualizamos o valor contido no TextView do
                    contador.
                 */
//                counterMessage.setText(dataString);
                Toast.makeText(getContext(), dataString, Toast.LENGTH_SHORT).show();
                Log.i("Tag", dataString);

            // invés de setar, enviar dataString pelo searchQuery
                //
                //
                //
                new AsyncFetch(dataString).execute();

            }

        }
    };

    /* Esse método é invocado sempre que o usuário clicar na TextView
        que contem o contador. O app Android transmite a string "restart",
        seguido de uma quebra de linha, que é o indicador de fim de mensagem.
     */
    public void restartCounter(View view) {
        connect.write("restart\n".getBytes());
    }



//    requerir info da base https


    private class AsyncFetch extends AsyncTask<String, String, String> {

        ProgressDialog pdLoading = new ProgressDialog(getContext());
        HttpURLConnection conn;
        URL url = null;
        String searchQuery;

        public AsyncFetch(String searchQuery){
            this.searchQuery=searchQuery;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tCarregando...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides

//                url = new URL("https://api.github.com/users/luiss1569");
                url = new URL("http://192.168.43.179/Projetos/SmartWalletWeb/");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput to true as we send and recieve data
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // add parameter to our above url
                Uri.Builder builder = new Uri.Builder().appendQueryParameter("searchQuery", searchQuery);
                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {
                    return("Connection error");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread
            pdLoading.dismiss();
//            List<DataFish> data=new ArrayList<>();
            Toast.makeText(getContext(), result, Toast.LENGTH_LONG).show();

            pdLoading.dismiss();
            if(result.equals("no rows")) {
                Toast.makeText(getContext(), "No Results found for entered query", Toast.LENGTH_LONG).show();
            }else{

//                Toast.makeText(getContext(), "Else", Toast.LENGTH_LONG).show();
                try {

                    JSONArray jArray = new JSONArray(result);

                    // Extract data from json and store into ArrayList as class objects
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        ModeloItem modeloI = new ModeloItem();
                        modeloI.setNomeProd(json_data.getString("nome"));
                        modeloI.setId(json_data.getInt("idProd"));

                    }


                } catch (JSONException e) {
                    // You to understand what actually error is and handle it appropriately
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
                    Toast.makeText(getContext(), result, Toast.LENGTH_LONG).show();
                }

            }

        }

    }


    public class ConnectionThread extends Thread {

        BluetoothSocket btSocket = null;
        BluetoothServerSocket btServerSocket = null;
        InputStream input = null;
        OutputStream output = null;
        String btDevAddress = null;
        String myUUID = "00001101-0000-1000-8000-00805F9B34FB";
        boolean server;
        boolean running = false;
        boolean isConnected = false;

        /*  Este construtor prepara o dispositivo para atuar como servidor.
         */
        public ConnectionThread() {

            this.server = true;
        }


        /*  Este construtor prepara o dispositivo para atuar como cliente.
            Tem como argumento uma string contendo o endereço MAC do dispositivo
        Bluetooth para o qual deve ser solicitada uma conexão.
         */
        public ConnectionThread(String btDevAddress) {

            this.server = false;
            this.btDevAddress = btDevAddress;
        }

        /*  O método run() contem as instruções que serão efetivamente realizadas
        em uma nova thread.
         */
        public void run() {

        /*  Anuncia que a thread está sendo executada.
            Pega uma referência para o adaptador Bluetooth padrão.
         */
            this.running = true;
            BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

        /*  Determina que ações executar dependendo se a thread está configurada
        para atuar como servidor ou cliente.
         */
            if (this.server) {

                /*  Servidor.
                 */
                try {

                /*  Cria um socket de servidor Bluetooth.
                    O socket servidor será usado apenas para iniciar a conexão.
                    Permanece em estado de espera até que algum cliente
                estabeleça uma conexão.
                 */
                    btServerSocket = btAdapter.listenUsingRfcommWithServiceRecord("Super Counter", UUID.fromString(myUUID));
                    btSocket = btServerSocket.accept();

                /*  Se a conexão foi estabelecida corretamente, o socket
                servidor pode ser liberado.
                 */
                    if (btSocket != null) {

                        btServerSocket.close();
                    }

                } catch (IOException e) {

                /*  Caso ocorra alguma exceção, exibe o stack trace para debug.
                    Envia um código para a Activity principal, informando que
                a conexão falhou.
                 */
                    e.printStackTrace();
                    toMainActivity("---N".getBytes());
                }


            } else {

                /*  Cliente.
                 */
                try {

                /*  Obtem uma representação do dispositivo Bluetooth com
                endereço btDevAddress.
                    Cria um socket Bluetooth.
                 */
                    BluetoothDevice btDevice = btAdapter.getRemoteDevice(btDevAddress);
                    btSocket = btDevice.createRfcommSocketToServiceRecord(UUID.fromString(myUUID));

                /*  Envia ao sistema um comando para cancelar qualquer processo
                de descoberta em execução.
                 */
                    btAdapter.cancelDiscovery();

                /*  Solicita uma conexão ao dispositivo cujo endereço é
                btDevAddress.
                    Permanece em estado de espera até que a conexão seja
                estabelecida.
                 */
                    if (btSocket != null) {
                        btSocket.connect();
                    }

                } catch (IOException e) {

                /*  Caso ocorra alguma exceção, exibe o stack trace para debug.
                    Envia um código para a Activity principal, informando que
                a conexão falhou.
                 */
                    e.printStackTrace();
                    toMainActivity("---N".getBytes());
                }

            }

        /*  Pronto, estamos conectados! Agora, só precisamos gerenciar a conexão.
            ...
         */

            if (btSocket != null) {

            /*  Envia um código para a Activity principal informando que a
            a conexão ocorreu com sucesso.
             */
                this.isConnected = true;
                toMainActivity("---S".getBytes());

                try {

                /*  Obtem referências para os fluxos de entrada e saída do
                socket Bluetooth.
                 */
                    input = btSocket.getInputStream();
                    output = btSocket.getOutputStream();

                /*  Permanece em estado de espera até que uma mensagem seja
                recebida.
                    Armazena a mensagem recebida no buffer.
                    Envia a mensagem recebida para a Activity principal, do
                primeiro ao último byte lido.
                    Esta thread permanecerá em estado de escuta até que
                a variável running assuma o valor false.
                 */
                    while (running) {

                    /*  Cria um byte array para armazenar temporariamente uma
                    mensagem recebida.
                        O inteiro bytes representará o número de bytes lidos na
                    última transmissão recebida.
                        O inteiro bytesRead representa o número total de bytes
                    lidos antes de uma quebra de linha. A quebra de linha
                    representa o fim da mensagem.
                     */
                        byte[] buffer = new byte[1024];
                        int bytes;
                        int bytesRead = -1;

                    /*  Lê os bytes recebidos e os armazena no buffer até que
                    uma quebra de linha seja identificada. Nesse ponto, assumimos
                    que a mensagem foi transmitida por completo.
                     */
                        do {
                            bytes = input.read(buffer, bytesRead + 1, 1);
                            bytesRead += bytes;
                        } while (buffer[bytesRead] != '\n');

                        if (bytesRead >= 1) {
                            /*  A mensagem recebida é enviada para a Activity principal.
                             */
                            toMainActivity(Arrays.copyOfRange(buffer, 0, bytesRead - 1));
                        }
                    }

                } catch (IOException e) {

                /*  Caso ocorra alguma exceção, exibe o stack trace para debug.
                    Envia um código para a Activity principal, informando que
                a conexão falhou.
                 */
                    e.printStackTrace();
                    toMainActivity("---N".getBytes());
                    this.isConnected = false;
                }
            }

        }

        /*  Utiliza um handler para enviar um byte array à Activity principal.
            O byte array é encapsulado em um Bundle e posteriormente em uma Message
        antes de ser enviado.
         */
        private void toMainActivity(byte[] data) {

            Message message = new Message();
            Bundle bundle = new Bundle();
            bundle.putByteArray("data", data);
            message.setData(bundle);
            handler.sendMessage(message);
        }

        /*  Método utilizado pela Activity principal para transmitir uma mensagem ao
         outro lado da conexão.
            A mensagem deve ser representada por um byte array.
         */
        public void write(byte[] data) {

            if (output != null) {
                try {

                    /*  Transmite a mensagem.
                     */
                    output.write(data);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {

                /*  Envia à Activity principal um código de erro durante a conexão.
                 */
                toMainActivity("---N".getBytes());
            }
        }

        /*  Método utilizado pela Activity principal para encerrar a conexão
         */
        public void cancel() {

            try {

                running = false;
                this.isConnected = false;
                btServerSocket.close();
                btSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            running = false;
            this.isConnected = false;
        }

        public boolean isConnected() {
            return this.isConnected;
        }
    }



}
