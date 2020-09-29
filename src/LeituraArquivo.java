import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class LeituraArquivo {

    public static void main(String[] args) {
        String linhas[] = new String[100000];
        int numLinhas = 0;

        Path path1 = Paths.get("acidentes.csv");

        try (BufferedReader reader = Files.newBufferedReader(path1, Charset.defaultCharset())) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                linhas[numLinhas] = line;
                numLinhas++;
            }
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
        }

        for (int i = 0; i < 5; i++) {
            String[] campos = linhas[i].split(";"); // divide a string pelo espaco em branco
           // System.out.println(campos[0]);
            String logradouro = campos[0].substring(0,campos[0].indexOf(" "));
            String nomeLog = campos[0].substring(campos[0].indexOf(" ")+1,campos[0].length());
            String tipoAcidente = campos[1];
            LocalDateTime data = LocalDateTime.of(
                    Integer.parseInt(campos[2].substring(0,4)), Integer.parseInt(campos[2].substring(4,6)), Integer.parseInt(campos[2].substring(6,8)), 
                    Integer.parseInt(campos[2].substring(9,11)), Integer.parseInt(campos[2].substring(12,14)));
            String diaSemana = campos[3];
            int feridos = Integer.parseInt(campos[4]);
            int fatais = Integer.parseInt(campos[5]);
            int auto = Integer.parseInt(campos[6]);
            int taxi = Integer.parseInt(campos[7]);
            int lotacao = Integer.parseInt(campos[8]);
            int onibusUrb = Integer.parseInt(campos[9]);
            int onibusInt = Integer.parseInt(campos[10]);
            int caminhao = Integer.parseInt(campos[11]);
            int moto = Integer.parseInt(campos[12]);
            int carroca = Integer.parseInt(campos[13]);
            int bicicleta = Integer.parseInt(campos[14]); 
            String tempo = campos[15];
            String turno = campos[16];
            String regiao = campos[17];
            System.out.println(logradouro+" "+nomeLog+"; "+tipoAcidente+"; "+data.toString()+"; "+tempo+"; "+turno+"; "+regiao); 
                    
        }
    }
}

