package uf3_prac3;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Exercici1 {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        int numero;
        
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("numeros.dat"));
            System.out.println("Introduce numeros a guardar: ");
            numero = scan.nextInt();
            
            while(numero != 0){
                dos.writeInt(numero);
                numero = scan.nextInt();
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Exercici1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
