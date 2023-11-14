import DataAcessObject.ComputadorDAO;
import Entidades.Computador;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class IdentificadorUnico {
    public static void GerarId(Computador computador, String arena){
        try {
            // Obtém o endereço MAC
            InetAddress localHost = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(localHost);

            byte[] macAddressBytes = network.getHardwareAddress();
            StringBuilder macAddress = new StringBuilder();
            for (int i = 0; i < macAddressBytes.length; i++) {
                macAddress.append(String.format("%02X%s", macAddressBytes[i], (i < macAddressBytes.length - 1) ? "-" : ""));
            }

            // Obtém o nome do usuário
            String userName = System.getProperty("user.name");

            // Combina as informações para criar um identificador único
            String uniqueIdentifier = macAddress.toString() + userName;

            // Imprime o identificador único
            ComputadorDAO.cadastrarComputador(computador, uniqueIdentifier, arena);
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
    }
}
